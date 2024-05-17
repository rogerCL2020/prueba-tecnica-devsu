package com.devsu.demo.cuentaMovimientos.service.impl;

import com.devsu.demo.cliente.repository.ClienteRepository;
import com.devsu.demo.common.CustomException;
import com.devsu.demo.cuentaMovimientos.repository.CuentaRepository;
import com.devsu.demo.cuentaMovimientos.service.CuentaService;
import com.devsu.demo.cuentaMovimientos.service.dto.CuentaDto;
import com.devsu.demo.cuentaMovimientos.service.mapper.CuentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CuentaServiceImpl implements CuentaService {


    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;
    @Autowired
    CuentaMapper cuentaMapper;


    @Autowired
    public CuentaServiceImpl(CuentaRepository cuentaRepository,ClienteRepository clienteRepository) {

        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Mono<CuentaDto> guardarCuenta(CuentaDto dto) {

        var clienteId = Long.valueOf(dto.getCliente().getClienteId());

       return clienteRepository.findById(clienteId)
                .map(existeCliente->{

                    if(cuentaRepository.findByNumeroCuenta(dto.getNumeroCuenta()).isPresent())
                        throw new CustomException("NUMERO_CUENTA_YA_EXISTE",HttpStatus.CONFLICT);

                    var cuentaEntity = cuentaMapper.cuentaDtoToCuentaEntity(dto);
                    cuentaEntity.setCliente(existeCliente);

                    return Mono.fromCallable(() -> cuentaRepository.save(cuentaEntity))
                            .map(cuentaMapper::cuentaEntityToCuentaDto);
                })
                .orElseThrow(() -> new CustomException("CLIENTE_NO_EXISTE", HttpStatus.CONFLICT));

    }

    @Override
    public Mono<CuentaDto> obtenerPorNumeroCuenta(String numeroCuenta) {
        return Mono.fromCallable(() -> cuentaRepository.findByNumeroCuenta(numeroCuenta))
                .flatMap(optionalCuenta -> optionalCuenta.map(Mono::just).orElseGet(Mono::empty))
                .map(cuentaMapper::cuentaEntityToCuentaDto);
    }

    @Override
    public  Mono<Boolean> borrarCuenta(Long id){
        return Mono.fromCallable(() -> cuentaRepository.findById(id))
                .flatMap(optionalCuenta -> optionalCuenta.map(Mono::just).orElseGet(Mono::empty))
                .flatMap(existeCuenta -> {
                    existeCuenta.setEstado(Boolean.FALSE);
                    cuentaRepository.save(existeCuenta);
                    return Mono.just(Boolean.TRUE);
                });

    }

    @Override
    public Flux<CuentaDto> obtenerTodos() {
        return Flux.fromIterable(cuentaRepository.findAll())
                .map(cuentaMapper::cuentaEntityToCuentaDto);
    }
}
