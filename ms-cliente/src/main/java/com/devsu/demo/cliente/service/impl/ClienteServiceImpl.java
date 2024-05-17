package com.devsu.demo.cliente.service.impl;

import com.devsu.demo.cliente.entity.ClienteEntity;
import com.devsu.demo.cliente.repository.ClienteRepository;
import com.devsu.demo.cliente.service.ClienteService;
import com.devsu.demo.cliente.service.dto.ClienteDto;
import com.devsu.demo.cliente.service.mapper.ClienteMapper;
import com.devsu.demo.common.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    ClienteMapper clienteMapper;
    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Mono<ClienteDto> guardarCliente(ClienteDto dto) {
        return Mono.defer(() -> {
            var existeCliente = clienteRepository.findByIdentificacion(dto.getIdentificacion());
            if (existeCliente.isPresent()) {
                return Mono.error(new CustomException("CLIENTE_CON_ESE_IDENTIFICADOR_YA_EXISTE", HttpStatus.CONFLICT));
            } else {
                var clienteEntity = clienteMapper.clienteDtoToClienteEntity(dto);
                return Mono.fromCallable(() -> clienteRepository.save(clienteEntity))
                        .map(clienteMapper::clienteEntityToClienteDto);
            }
        });
    }


    @Override
    public Flux<ClienteDto> obtenerTodosClientes() {
        return Flux.fromIterable(clienteRepository.findAll())
                .map(clienteMapper::clienteEntityToClienteDto);
    }

    @Override
    public Mono<ClienteDto> obtenerClientePorId(Long id) {
        return Mono.fromCallable(() -> clienteRepository.findById(id))
                .flatMap(optionalCliente -> optionalCliente.map(Mono::just).orElseGet(Mono::empty))
                .map(clienteMapper::clienteEntityToClienteDto);
    }

    @Override
    public Mono<ClienteDto> actualizarCliente(Long id, ClienteDto dto) {

        return clienteRepository.findById(id)
                .map(existeCliente -> {

                    if(!existeCliente.getIdentificacion().equals(dto.getIdentificacion()))
                        throw new CustomException("NO_SE_PUEDE_ACTUALIZAR_IDENTIFICACION",HttpStatus.CONFLICT);

                    var clienteEntity = clienteMapper.clienteDtoToClienteEntity(dto);
                    clienteEntity.setClienteId(existeCliente.getClienteId());

                    return Mono.fromCallable(() -> clienteRepository.save(clienteEntity))
                            .map(clienteMapper::clienteEntityToClienteDto);
                })
                .orElseGet(Mono::empty);

    }

    @Override
    public  Mono<Boolean> borrarCliente(Long id){
        return Mono.fromCallable(() -> clienteRepository.findById(id))
                .flatMap(optionalCliente -> optionalCliente.map(Mono::just).orElseGet(Mono::empty))
                .flatMap(existeCliente -> {
                    existeCliente.setEstado(Boolean.FALSE);
                    clienteRepository.save(existeCliente);
                    return Mono.just(Boolean.TRUE);
                });

    }
}
