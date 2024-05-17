package com.devsu.demo.cuentaMovimientos.service;

import com.devsu.demo.cuentaMovimientos.service.dto.CuentaDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface  CuentaService {

    Mono<CuentaDto> guardarCuenta(CuentaDto dto);

    Flux<CuentaDto> obtenerTodos();

    Mono<CuentaDto> obtenerPorNumeroCuenta(String nroCuenta);

    Mono<Boolean> borrarCuenta(Long id);

}
