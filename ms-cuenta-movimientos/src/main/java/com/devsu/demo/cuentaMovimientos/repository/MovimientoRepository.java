package com.devsu.demo.cuentaMovimientos.repository;

import com.devsu.demo.cuentaMovimientos.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {
}