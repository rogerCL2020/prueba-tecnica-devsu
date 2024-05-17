package com.devsu.demo.cuentaMovimientos.service.dto;

import com.devsu.demo.cliente.dto.ClienteDto;
import com.devsu.demo.cuentaMovimientos.enumerado.TipoCuenta;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDto {

    private String cuentaId;

    private String numeroCuenta;

    private TipoCuenta tipoCuenta;

    private BigDecimal saldo;

    private boolean estado;

    private ClienteDto cliente;
}
