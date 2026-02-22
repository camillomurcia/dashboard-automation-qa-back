package com.dashboard.automation_dashboard.repository;

import com.dashboard.automation_dashboard.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findByEsPorDefectoTrue();
    Optional<Estado> findByEsEnProgresoTrue();
    Optional<Estado> findByEsCompletadoTrue();
}
