package com.devsu.demo.cuentaMovimientos.entity;

import com.devsu.demo.cliente.entity.ClienteEntity;
import com.devsu.demo.cuentaMovimientos.enumerado.TipoCuenta;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuenta", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"numero_cuenta"})
})
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id")
    private Long cuentaId;

    @Column(name = "numero_cuenta",unique = true, length = 10, nullable = false)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta",nullable = false)
    private TipoCuenta tipoCuenta;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "estado",columnDefinition = "BOOLEAN DEFAULT true")
    private boolean estado;

    @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
    private Set<MovimientoEntity> movimientos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id",nullable = false, referencedColumnName = "cliente_id", foreignKey = @ForeignKey(name = "fk_cuenta_cliente"))
    private ClienteEntity cliente;
}
