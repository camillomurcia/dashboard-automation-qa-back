package com.dashboard.automation_dashboard.repository;

import com.dashboard.automation_dashboard.model.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacionRepository extends JpaRepository<Operacion, Long> {

    List<Operacion> findByFuncionalidadId(Long funcionalidadId);

}