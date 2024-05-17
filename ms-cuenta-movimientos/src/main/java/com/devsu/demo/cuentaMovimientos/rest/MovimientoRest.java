package com.devsu.demo.cuentaMovimientos.rest;

import com.devsu.demo.cuentaMovimientos.service.MovimientoService;
import com.devsu.demo.cuentaMovimientos.service.dto.DetalleMovimientoDto;
import com.devsu.demo.cuentaMovimientos.service.dto.MovimientoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/demo/v1/movimientos")
@Slf4j
public class MovimientoRest {

    private final MovimientoService movimientoService;

    @Autowired
    public MovimientoRest(MovimientoService movimientoService) {

        this.movimientoService = movimientoService;
    }

    @PostMapping("")
    public Mono<ResponseEntity<MovimientoDto>> crearMovimiento(@RequestBody MovimientoDto dto) {
        return movimientoService.guardarMovimiento(dto)
                .map(movimiento -> ResponseEntity.status(HttpStatus.CREATED).body(movimiento));
    }

    @GetMapping(value="/reporte")
    public Flux<DetalleMovimientoDto> reporte(
                                       @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaDesde,
                                       @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaHasta,
                                       @RequestParam Long clienteId)
    {
        return movimientoService.reporte(fechaDesde,fechaHasta,clienteId);

    }
}
