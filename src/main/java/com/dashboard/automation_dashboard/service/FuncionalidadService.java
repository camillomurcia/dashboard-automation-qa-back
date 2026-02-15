package com.dashboard.automation_dashboard.service;

import com.dashboard.automation_dashboard.model.Funcionalidad;
import com.dashboard.automation_dashboard.repository.FuncionalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionalidadService {

    @Autowired
    private FuncionalidadRepository funcionalidadRepository;

    public List<Funcionalidad> listarTodas() {
        return funcionalidadRepository.findAll();
    }

    public Funcionalidad guardar(Funcionalidad funcionalidad) {
        return funcionalidadRepository.save(funcionalidad);
    }

}
