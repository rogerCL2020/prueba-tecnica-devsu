package com.devsu.demo.cuentaMovimientos.repository;

import com.devsu.demo.cliente.entity.ClienteEntity;
import com.devsu.demo.cuentaMovimientos.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);

    List<CuentaEntity> findByCliente(ClienteEntity cliente);

}