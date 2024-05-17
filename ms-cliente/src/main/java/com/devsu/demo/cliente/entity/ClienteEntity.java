package com.devsu.demo.cliente.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity extends PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado" ,columnDefinition = "BOOLEAN DEFAULT true")
    private boolean estado;

}
