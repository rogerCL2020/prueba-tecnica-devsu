package com.devsu.demo.cliente.dto;

import com.devsu.demo.cliente.enumerado.Genero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private String clienteId;
    private String nombre;
    private Genero genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String contrasena;
    private boolean estado;
}