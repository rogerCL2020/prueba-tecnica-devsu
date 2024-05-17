package com.devsu.demo.cuentaMovimientos.service.mapper;

import com.devsu.demo.cuentaMovimientos.entity.MovimientoEntity;
import com.devsu.demo.cuentaMovimientos.service.dto.DetalleMovimientoDto;
import com.devsu.demo.cuentaMovimientos.service.dto.MovimientoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    @Mapping(target = "numeroCuenta", source = "cuenta.numeroCuenta")
    MovimientoDto movimientoEntityToMovimientoDto(MovimientoEntity entity);

    MovimientoEntity movimientoDtoToMovimientoEntity(MovimientoDto dto);

    @Mapping(target = "fecha", source = "fecha")
    @Mapping(target = "cliente", source = "cuenta.cliente.nombre")
    @Mapping(target = "numeroCuenta", source = "cuenta.numeroCuenta")
    @Mapping(target = "tipoCuenta", source = "cuenta.tipoCuenta")
    @Mapping(target = "saldoInicial", source = "saldoInicial")
    @Mapping(target = "estado", source = "cuenta.estado")
    @Mapping(target = "movimiento", source = "valor")
    @Mapping(target = "saldoDisponible", source = "saldo")
    DetalleMovimientoDto movimientoEntityToDetalleMovimientoDto(MovimientoEntity entity);

}
