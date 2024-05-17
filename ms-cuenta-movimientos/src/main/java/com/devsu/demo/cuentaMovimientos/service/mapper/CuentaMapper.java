package com.devsu.demo.cuentaMovimientos.service.mapper;

import com.devsu.demo.cuentaMovimientos.entity.CuentaEntity;
import com.devsu.demo.cuentaMovimientos.service.dto.CuentaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    CuentaDto cuentaEntityToCuentaDto(CuentaEntity entity);

    @Mapping(target = "cliente", ignore = true)
    CuentaEntity cuentaDtoToCuentaEntity(CuentaDto dto);

}
