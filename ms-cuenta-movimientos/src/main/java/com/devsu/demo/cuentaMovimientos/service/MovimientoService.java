package com.devsu.demo.cuentaMovimientos.service;

import com.devsu.demo.cuentaMovimientos.service.dto.DetalleMovimientoDto;
import com.devsu.demo.cuentaMovimientos.service.dto.MovimientoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface MovimientoService {

    Mono<MovimientoDto> guardarMovimiento(MovimientoDto dto);

    Flux<DetalleMovimientoDto> reporte(LocalDate fechaDesde, LocalDate fechaHasta, Long clienteId);

}
