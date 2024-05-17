package com.devsu.demo.cuentaMovimientos.rest;

import com.devsu.demo.cuentaMovimientos.service.CuentaService;
import com.devsu.demo.cuentaMovimientos.service.dto.CuentaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/demo/v1/cuentas")
@Slf4j
public class CuentaRest {

    private final CuentaService cuentaService;

    @Autowired
    public CuentaRest(CuentaService cuentaService) {

        this.cuentaService = cuentaService;
    }

    @PostMapping("")
    public Mono<ResponseEntity<CuentaDto>> crearCuenta(@RequestBody CuentaDto dto) {
        return cuentaService.guardarCuenta(dto)
                .map(cuenta -> ResponseEntity.status(HttpStatus.CREATED).body(cuenta));
    }

    @GetMapping("/{numeroCuenta}/detalle")
    public Mono<ResponseEntity<CuentaDto>> obtenerPorNumeroCuenta(@PathVariable String numeroCuenta) {
        return cuentaService.obtenerPorNumeroCuenta(numeroCuenta)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> borrarCuenta(@PathVariable Long id) {
        return cuentaService.borrarCuenta(id)
                .map(success -> ResponseEntity.ok().build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("all")
    public Flux<CuentaDto> obtenerTodos() {
        return cuentaService.obtenerTodos();

    }
}
