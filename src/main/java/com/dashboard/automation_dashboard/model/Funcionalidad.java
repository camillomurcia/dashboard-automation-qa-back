package com.dashboard.automation_dashboard.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Funcionalidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "funcionalidad", cascade = CascadeType.ALL)
    private List<Operacion> operaciones;
}
