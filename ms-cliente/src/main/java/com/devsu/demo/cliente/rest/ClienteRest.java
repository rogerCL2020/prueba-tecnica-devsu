package com.devsu.demo.cliente.rest;

import com.devsu.demo.cliente.service.ClienteService;
import com.devsu.demo.cliente.service.dto.ClienteDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/demo/v1/clientes")
@Slf4j
public class ClienteRest {
    private final ClienteService clienteService;

    @Autowired
    public ClienteRest(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/all")
    public Flux<ClienteDto> obtenerTodosClientes() {
        return clienteService.obtenerTodosClientes();
    }

    @GetMapping("/{id}/detalle")
    public Mono<ResponseEntity<ClienteDto>> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Mono<ResponseEntity<ClienteDto>> crearCliente(@RequestBody ClienteDto clienteDTO) {
        return clienteService.guardarCliente(clienteDTO)
                .map(cliente -> ResponseEntity.status(HttpStatus.CREATED).body(cliente));
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<ClienteDto>> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDTO) {
        return clienteService.actualizarCliente(id, clienteDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> borrarCliente(@PathVariable Long id) {
        return clienteService.borrarCliente(id)
                .map(success -> ResponseEntity.ok().build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
