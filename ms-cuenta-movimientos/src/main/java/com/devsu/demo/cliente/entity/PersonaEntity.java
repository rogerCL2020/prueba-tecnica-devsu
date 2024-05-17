package com.devsu.demo.cliente.entity;

import com.devsu.demo.cliente.enumerado.Genero;
import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonaEntity {

    @Column(name = "nombre")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Genero genero;

    @Column(name = "edad")
    private int edad;

    @Column(name = "identificacion", unique = true)
    private String identificacion;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

}
