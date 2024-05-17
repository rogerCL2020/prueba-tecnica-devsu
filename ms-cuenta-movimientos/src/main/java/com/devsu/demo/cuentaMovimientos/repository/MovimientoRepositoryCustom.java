package com.devsu.demo.cuentaMovimientos.repository;

import com.devsu.demo.cuentaMovimientos.entity.MovimientoEntity;

import java.time.LocalDate;
import java.util.List;


public interface MovimientoRepositoryCustom {

    List<MovimientoEntity> obtenerMovimientosFiltro(LocalDate FechaDesde,LocalDate fechaHasta,Long clienteID);

}
