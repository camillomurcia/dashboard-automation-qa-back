package com.dashboard.automation_dashboard.controller;

import com.dashboard.automation_dashboard.model.Estado;
import com.dashboard.automation_dashboard.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> obtenerTodos() {
        return estadoRepository.findAll();
    }
}
