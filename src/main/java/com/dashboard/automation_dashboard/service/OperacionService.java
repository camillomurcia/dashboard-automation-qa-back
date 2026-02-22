package com.dashboard.automation_dashboard.service;

import com.dashboard.automation_dashboard.dto.DashboardEstadisticasDto;
import com.dashboard.automation_dashboard.exception.RecursoNoEncontradoException;
import com.dashboard.automation_dashboard.model.Estado;
import com.dashboard.automation_dashboard.model.Funcionalidad;
import com.dashboard.automation_dashboard.model.Operacion;
import com.dashboard.automation_dashboard.repository.EstadoRepository;
import com.dashboard.automation_dashboard.repository.FuncionalidadRepository;
import com.dashboard.automation_dashboard.repository.OperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacionService {

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private FuncionalidadRepository funcionalidadRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    private void procesarMatematicasYEstado(Operacion op) {
        // 1. Calcular Porcentaje (se mantiene igual)
        if (op.getTotalEscenarios() > 0) {
            double calculo = ((double) op.getAutomatizados() / op.getTotalEscenarios()) * 100;
            op.setPorcentaje(Math.round(calculo * 100.0) / 100.0);
        } else {
            op.setPorcentaje(0.0);
        }

        // 2. ¿El Frontend nos envió un ID? (El humano interactuó)
        if (op.getEstado() != null && op.getEstado().getId() != null) {
            // Buscamos el estado completo en la base de datos
            Estado estadoSeleccionado = estadoRepository.findById(op.getEstado().getId())
                    .orElseThrow(() -> new RuntimeException("Estado no encontrado: ID " + op.getEstado().getId()));

            op.setEstado(estadoSeleccionado);

            // LOGICA DINÁMICA: Si la base de datos dice que este estado requiere acción humana, bloqueamos a 0.
            if (estadoSeleccionado.isRequiereAccionHumana()) {
                op.setAutomatizados(0);
            }
        }

        // 3. El Front no envió ID (Modo Automático puro)
        else {
            if (op.getAutomatizados() == 0) {
                // "Tráeme el estado que está configurado como 'por defecto'"
                op.setEstado(estadoRepository.findByEsPorDefectoTrue().orElseThrow());
            } else if (op.getAutomatizados() >= op.getTotalEscenarios()) {
                // "Tráeme el estado que significa 'completado'"
                op.setEstado(estadoRepository.findByEsCompletadoTrue().orElseThrow());
                op.setAutomatizados(op.getTotalEscenarios());
            } else {
                // "Tráeme el estado que significa 'en progreso'"
                op.setEstado(estadoRepository.findByEsEnProgresoTrue().orElseThrow());
            }
        }
    }


    public Operacion guardarOperacion(Operacion operacion, Long funcionalidadId) throws RecursoNoEncontradoException {
        Funcionalidad funcionalidad = funcionalidadRepository.findById(funcionalidadId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Funcionalidad con ID " + funcionalidadId + " no encontrada"));

        // 1. Relacionamos la operación con su funcionalidad padre
        operacion.setFuncionalidad(funcionalidad);

        // 🚨 2. LA MAGIA: Calculamos el estado ANTES de guardar 🚨
        // (Asegúrate de tener el método procesarMatematicasYEstado en esta misma clase)
        procesarMatematicasYEstado(operacion);

        // 3. Ahora sí, guardamos en la base de datos con el estado lleno
        return operacionRepository.save(operacion);
    }

    public List<Operacion> listarPorFuncionalidad(Long funcionalidadId) {
        return operacionRepository.findByFuncionalidadId(funcionalidadId);
    }

    public Operacion actualizarOperacion(Long id, Operacion operacionActualizada) throws RecursoNoEncontradoException {
        Operacion existente = operacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Operacion con ID " + id + " no encontrada"));
        existente.setNombre(operacionActualizada.getNombre());
        existente.setTotalEscenarios(operacionActualizada.getTotalEscenarios());
        existente.setAutomatizados(operacionActualizada.getAutomatizados());
        existente.setEstado(operacionActualizada.getEstado());
        procesarMatematicasYEstado(existente);
        return operacionRepository.save(existente);
    }

    public DashboardEstadisticasDto calcularEstadisticas(Long funcionalidadId) {
        List<Operacion> operaciones = operacionRepository.findByFuncionalidadId(funcionalidadId);
        int totalEscenarios = 0;
        int totalAutomatizados = 0;
        for (Operacion op : operaciones) {
            totalEscenarios += op.getTotalEscenarios();
            totalAutomatizados += op.getAutomatizados();
        }
        int totalManuales = totalEscenarios - totalAutomatizados;
        double porcentaje = 0;
        if (totalEscenarios > 0) {
            porcentaje = ((double) totalAutomatizados / totalEscenarios) * 100;
        }
        porcentaje = Math.round(porcentaje * 100.0) / 100.0;

        return new DashboardEstadisticasDto(totalEscenarios, totalAutomatizados, totalManuales, porcentaje);
    }
}
