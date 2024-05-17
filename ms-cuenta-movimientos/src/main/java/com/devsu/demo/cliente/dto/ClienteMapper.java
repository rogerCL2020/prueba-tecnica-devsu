package com.devsu.demo.cliente.dto;

import com.devsu.demo.cliente.entity.ClienteEntity;
import com.devsu.demo.cliente.dto.ClienteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDto clienteEntityToClienteDto(ClienteEntity entity);

    ClienteEntity clienteDtoToClienteEntity(ClienteDto dto);

}
