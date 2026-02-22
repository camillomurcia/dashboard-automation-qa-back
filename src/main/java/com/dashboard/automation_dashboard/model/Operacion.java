package com.dashboard.automation_dashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int totalEscenarios;
    private int automatizados;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
    private double porcentaje;

    @ManyToOne
    @JoinColumn(name="funcionalidad_id")
    @JsonIgnore
    private Funcionalidad funcionalidad;
}
