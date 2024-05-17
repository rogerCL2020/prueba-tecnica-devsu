package com.devsu.demo.cuentaMovimientos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimiento")
public class MovimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id")
    private Long movimientoId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha",nullable = false)
    private LocalDateTime fecha;

    @Column(name = "saldo_inicial")
    private BigDecimal saldoInicial;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cuenta_id", nullable = false, referencedColumnName = "cuenta_id", foreignKey = @ForeignKey(name = "fk_movimiento_cuenta"))
    private CuentaEntity cuenta;

}
