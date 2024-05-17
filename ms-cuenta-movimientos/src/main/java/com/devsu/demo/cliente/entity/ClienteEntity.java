package com.devsu.demo.cliente.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteEntity extends PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado",columnDefinition = "BOOLEAN DEFAULT true")
    private boolean estado;
}
