package com.devsu.demo.cliente.service;

import com.devsu.demo.cliente.entity.ClienteEntity;
import com.devsu.demo.cliente.service.dto.ClienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Mono<ClienteDto> guardarCliente(ClienteDto dto);

    Flux<ClienteDto> obtenerTodosClientes();

    Mono<ClienteDto> obtenerClientePorId(Long id);

    Mono<ClienteDto> actualizarCliente(Long id,ClienteDto dto);

    Mono<Boolean> borrarCliente(@PathVariable Long id);

}
