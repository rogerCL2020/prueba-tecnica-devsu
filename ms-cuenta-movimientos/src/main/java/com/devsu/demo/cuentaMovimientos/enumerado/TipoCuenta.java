package com.devsu.demo.cuentaMovimientos.enumerado;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCuenta {

    CORRIENTE("Corriente"),
    AHORRO("Ahorro");

    private final String descripcion;

}
