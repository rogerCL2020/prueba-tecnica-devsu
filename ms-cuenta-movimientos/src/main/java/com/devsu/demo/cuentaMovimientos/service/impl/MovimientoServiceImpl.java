package com.devsu.demo.cuentaMovimientos.service.impl;

import com.devsu.demo.common.CustomException;
import com.devsu.demo.cuentaMovimientos.repository.CuentaRepository;
import com.devsu.demo.cuentaMovimientos.repository.MovimientoRepository;
import com.devsu.demo.cuentaMovimientos.repository.MovimientoRepositoryCustom;
import com.devsu.demo.cuentaMovimientos.service.MovimientoService;
import com.devsu.demo.cuentaMovimientos.service.dto.DetalleMovimientoDto;
import com.devsu.demo.cuentaMovimientos.service.dto.MovimientoDto;
import com.devsu.demo.cuentaMovimientos.service.mapper.MovimientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepositoryCustom movimientoRepositoryCustom;

    private final MovimientoRepository movimientoRepository;

    @Autowired
    MovimientoMapper movimientoMapper;


    @Autowired
    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,CuentaRepository cuentaRepository,MovimientoRepositoryCustom movimientoRepositoryCustom) {

        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepositoryCustom = movimientoRepositoryCustom;
    }

    @Override
    public Mono<MovimientoDto> guardarMovimiento(MovimientoDto dto) {

        var numeroCuenta = dto.getNumeroCuenta();

        return  cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .map(existeCuenta -> {

                    var movimientoEntity = movimientoMapper.movimientoDtoToMovimientoEntity(dto);
                    var saldo = existeCuenta.getSaldo().add(movimientoEntity.getValor());
                    if (saldo.compareTo(BigDecimal.ZERO) >= 0){
                        movimientoEntity.setCuenta(existeCuenta);
                        movimientoEntity.setFecha(LocalDateTime.now());
                        movimientoEntity.setSaldoInicial(existeCuenta.getSaldo());
                        movimientoEntity.setSaldo(existeCuenta.getSaldo().add(movimientoEntity.getValor()));

                        existeCuenta.setSaldo(movimientoEntity.getSaldo());
                        cuentaRepository.saveAndFlush(existeCuenta);

                        return Mono.fromCallable(() -> movimientoRepository.save(movimientoEntity))
                                .map(movimientoMapper::movimientoEntityToMovimientoDto);
                    }else{
                        throw new CustomException("SALDO_INSUFICIENTE", HttpStatus.CONFLICT);
                    }

                })
                .orElseThrow(() -> new CustomException("NUMERO_CUENTA_NO_EXISTE", HttpStatus.CONFLICT));

    }

    @Override
    public Flux<DetalleMovimientoDto> reporte(LocalDate fechaDesde, LocalDate fechaHasta, Long clienteId){

        return Flux.fromIterable(movimientoRepositoryCustom.obtenerMovimientosFiltro(fechaDesde,fechaHasta,clienteId))
                .map(movimientoMapper::movimientoEntityToDetalleMovimientoDto);

    }

}
