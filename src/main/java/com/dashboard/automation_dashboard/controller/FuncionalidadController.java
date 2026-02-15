package com.dashboard.automation_dashboard.controller;

import com.dashboard.automation_dashboard.model.Funcionalidad;
import com.dashboard.automation_dashboard.service.FuncionalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionalidad")
@CrossOrigin(origins = "*")
public class FuncionalidadController {

    @Autowired
    private FuncionalidadService funcionalidadService;

    @GetMapping
    public List<Funcionalidad> listarTodas() {
        return funcionalidadService.listarTodas();
    }

    @PostMapping("/crearFuncionalidad")
    public Funcionalidad crear(@RequestBody Funcionalidad funcionalidad) {
        return funcionalidadService.guardar(funcionalidad);
    }
}
