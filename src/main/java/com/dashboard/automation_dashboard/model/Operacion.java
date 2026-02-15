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
    private String estado;
    private double porcentaje;

    @ManyToOne
    @JoinColumn(name="funcionalidad_id")
    @JsonIgnore
    private Funcionalidad funcionalidad;

    @PrePersist
    @PreUpdate
    public void calcularEstado(){
        if (this.totalEscenarios > 0) {
            double calculo = ((double) this.automatizados / this.totalEscenarios) * 100;
            this.porcentaje = Math.round(calculo * 100.0) / 100.0;
        } else {
            this.porcentaje = 0;
        }
        if("No automatizable".equals(this.estado) || "Analisis a automatizar".equals(this.estado)){
            return;
        }

        if (this.automatizados == 0){
            this.estado = "Manual";
        } else if (this.automatizados >= this.totalEscenarios) {
            this.estado = "Completado";
            this.automatizados = this.totalEscenarios;
        } else {
            this.estado = "Parcial";
        }
    }
}
