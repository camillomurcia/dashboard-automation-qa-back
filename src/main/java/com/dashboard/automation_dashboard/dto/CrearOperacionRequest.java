package com.dashboard.automation_dashboard.dto;

import lombok.Data;

@Data
public class CrearOperacionRequest {
    private String nombre;
    private int totalEscenarios;
    private Long funcionalidadId;
}
