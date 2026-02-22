package com.dashboard.automation_dashboard.service;

import com.dashboard.automation_dashboard.dto.DashboardEstadisticasDto;
import com.dashboard.automation_dashboard.exception.RecursoNoEncontradoException;
import com.dashboard.automation_dashboard.model.Funcionalidad;
import com.dashboard.automation_dashboard.model.Operacion;
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

    public Operacion guardarOperacion(Operacion operacion, Long funcionalidadId) throws RecursoNoEncontradoException {
        Funcionalidad funcionalidad = funcionalidadRepository.findById(funcionalidadId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Funcionalidad con ID " + funcionalidadId + " no encontrada"));
        operacion.setFuncionalidad(funcionalidad);
        return operacionRepository.save(operacion);
    }

    public List<Operacion> listarPorFuncionalidad(Long funcionalidadId) {
        return operacionRepository.findByFuncionalidadId(funcionalidadId);
    }

    public Operacion actualizarOperacion(Long id, Operacion operacionActualizada) throws RecursoNoEncontradoException {
        Operacion existente = operacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Operacion con ID " + id + " no encontrada"));
        if (operacionActualizada.getNombre() != null) {
            existente.setNombre(operacionActualizada.getNombre());
        }
        existente.setAutomatizados(operacionActualizada.getAutomatizados());
        if (operacionActualizada.getEstado() != null) {
            existente.setEstado(operacionActualizada.getEstado());
        }
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
