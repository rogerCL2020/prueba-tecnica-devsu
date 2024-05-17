package com.devsu.demo.cuentaMovimientos.service.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDto {

    private String movimientoId;
    private LocalDateTime fecha;
    private BigDecimal saldoInicial;
    private BigDecimal valor;
    private BigDecimal saldo;
    private String numeroCuenta;

}
