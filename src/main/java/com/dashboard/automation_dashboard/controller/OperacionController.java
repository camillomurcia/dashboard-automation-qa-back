package com.dashboard.automation_dashboard.controller;

import com.dashboard.automation_dashboard.dto.CrearOperacionRequest;
import com.dashboard.automation_dashboard.dto.DashboardEstadisticasDto;
import com.dashboard.automation_dashboard.exception.RecursoNoEncontradoException;
import com.dashboard.automation_dashboard.model.Operacion;
import com.dashboard.automation_dashboard.service.OperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operacion")
@CrossOrigin(origins = "*")
public class OperacionController {

    @Autowired
    private OperacionService operacionService;

    @GetMapping("/{id}")
    public List<Operacion> listarPorFuncionalidad(@PathVariable Long id) {
        return operacionService.listarPorFuncionalidad(id);
    }

    @PostMapping("/crearOperacion")
    public Operacion crear(@RequestBody CrearOperacionRequest request) throws RecursoNoEncontradoException {
        Operacion nuevaOperacion = new Operacion();
        nuevaOperacion.setNombre(request.getNombre());
        nuevaOperacion.setTotalEscenarios(request.getTotalEscenarios());
        return operacionService.guardarOperacion(nuevaOperacion, request.getFuncionalidadId());
    }

    @PutMapping("/{id}")
    public Operacion actualizar(@PathVariable Long id, @RequestBody Operacion operacion) throws RecursoNoEncontradoException {
        return operacionService.actualizarOperacion(id, operacion);
    }

    @PostMapping("/estadisticas/{id}")
    public DashboardEstadisticasDto obtenerEstadisticas(@PathVariable Long id) {
        return operacionService.calcularEstadisticas(id);
    }
}
