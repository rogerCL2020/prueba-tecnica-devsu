package com.devsu.demo.cliente.service.mapper;

import com.devsu.demo.cliente.entity.ClienteEntity;
import com.devsu.demo.cliente.service.dto.ClienteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDto clienteEntityToClienteDto(ClienteEntity entity);

    ClienteEntity clienteDtoToClienteEntity(ClienteDto dto);

}
