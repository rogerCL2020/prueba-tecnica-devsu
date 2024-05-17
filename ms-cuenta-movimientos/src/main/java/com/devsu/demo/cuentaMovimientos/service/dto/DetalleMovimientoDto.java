package com.devsu.demo.cuentaMovimientos.service.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleMovimientoDto {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;

    private String cliente;

    private String numeroCuenta;

    private String tipoCuenta;

    private BigDecimal saldoInicial;

    private String estado;

    private BigDecimal movimiento;

    private BigDecimal saldoDisponible;
}
