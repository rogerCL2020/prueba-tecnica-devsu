package com.devsu.demo.cuentaMovimientos.repository.impl;

import com.devsu.demo.cliente.entity.QClienteEntity;
import com.devsu.demo.cuentaMovimientos.entity.MovimientoEntity;
import com.devsu.demo.cuentaMovimientos.entity.QCuentaEntity;
import com.devsu.demo.cuentaMovimientos.entity.QMovimientoEntity;
import com.devsu.demo.cuentaMovimientos.repository.MovimientoRepositoryCustom;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MovimientoRespositoryCustomImpl implements MovimientoRepositoryCustom {

    @Autowired
    private JPAQueryFactory factory;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<MovimientoEntity> obtenerMovimientosFiltro(LocalDate fechaDesde, LocalDate fechaHasta, Long clienteId){
        QMovimientoEntity movimiento = QMovimientoEntity.movimientoEntity;
        QCuentaEntity cuenta = QCuentaEntity.cuentaEntity;
        QClienteEntity cliente = QClienteEntity.clienteEntity;

        var query = factory
                .select(movimiento)
                .distinct()
                .from(movimiento)
                .join(cuenta)
                .on(movimiento.cuenta.eq(cuenta))
                .join(cliente)
                .on(cuenta.cliente.eq(cliente))
                .where(cuenta.estado.eq(Boolean.TRUE));

        query.where(movimiento.fecha.between(fechaDesde.atStartOfDay(), fechaHasta.atTime(23, 59)));
        query.where(cliente.clienteId.eq(clienteId));

        var querydsl = new Querydsl(entityManager, (new PathBuilderFactory()).create(MovimientoEntity.class));

        return querydsl.applySorting(Sort.by(Sort.Direction.DESC, "fecha"), query).fetch();

    }

}
