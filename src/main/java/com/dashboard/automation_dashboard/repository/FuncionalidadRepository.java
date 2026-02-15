package com.dashboard.automation_dashboard.repository;

import com.dashboard.automation_dashboard.model.Funcionalidad;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionalidadRepository extends JpaRepository<Funcionalidad, Long> {

}
