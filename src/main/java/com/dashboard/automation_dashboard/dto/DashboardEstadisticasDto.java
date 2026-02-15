package com.dashboard.automation_dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardEstadisticasDto {
    private int totalEscenarios;
    private int totalAutomatizados;
    private int totalManuales;
    private double porcentajeCobertura;
}
