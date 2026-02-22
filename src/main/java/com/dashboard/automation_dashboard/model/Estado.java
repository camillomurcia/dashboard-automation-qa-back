package com.dashboard.automation_dashboard.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estados")
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(name = "es_por_defecto")
    private boolean esPorDefecto;

    @Column(name = "es_en_progreso")
    private boolean esEnProgreso;

    @Column(name = "es_completado")
    private boolean esCompletado;

    @Column(name = "requiere_accion_humana")
    private boolean requiereAccionHumana;
}
