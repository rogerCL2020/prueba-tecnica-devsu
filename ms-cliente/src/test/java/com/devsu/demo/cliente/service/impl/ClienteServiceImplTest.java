package com.devsu.demo.cliente.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.devsu.demo.cliente.entity.ClienteEntity;
import com.devsu.demo.cliente.enumerado.Genero;
import com.devsu.demo.cliente.repository.ClienteRepository;
import com.devsu.demo.cliente.service.dto.ClienteDto;
import com.devsu.demo.cliente.service.mapper.ClienteMapper;
import com.devsu.demo.common.CustomException;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ClienteServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ClienteServiceImplTest {
  @MockBean
  private ClienteMapper clienteMapper;

  @MockBean
  private ClienteRepository clienteRepository;

  @Autowired
  private ClienteServiceImpl clienteServiceImpl;

  /**
   * Method under test: {@link ClienteServiceImpl#guardarCliente(ClienteDto)}
   */
  @Test
  void testGuardarCliente() {

    // Arrange and Act
    clienteServiceImpl.guardarCliente(new ClienteDto("42", "Nombre", Genero.MASCULINO, 1, "Identificacion", "Direccion",
            "Telefono", "Contrasena", true));
  }

  /**
   * Method under test: {@link ClienteServiceImpl#obtenerTodosClientes()}
   */
  @Test
  void testObtenerTodosClientes() {
    // Arrange
    when(clienteRepository.findAll()).thenReturn(new ArrayList<>());

    // Act
    clienteServiceImpl.obtenerTodosClientes();

    // Assert
    verify(clienteRepository).findAll();
  }

  /**
   * Method under test: {@link ClienteServiceImpl#obtenerTodosClientes()}
   */
  @Test
  void testObtenerTodosClientes2() {
    // Arrange
    when(clienteRepository.findAll()).thenThrow(new CustomException("An error occurred", HttpStatus.CONTINUE));

    // Act and Assert
    assertThrows(CustomException.class, () -> clienteServiceImpl.obtenerTodosClientes());
    verify(clienteRepository).findAll();
  }

  /**
   * Method under test: {@link ClienteServiceImpl#obtenerClientePorId(Long)}
   */
  @Test
  void testObtenerClientePorId() {

    // Arrange and Act
    clienteServiceImpl.obtenerClientePorId(1L);
  }

  /**
   * Method under test:
   * {@link ClienteServiceImpl#actualizarCliente(Long, ClienteDto)}
   */
  @Test
  void testActualizarCliente() {
    // Arrange
    ClienteEntity clienteEntity = new ClienteEntity();
    clienteEntity.setClienteId(1L);
    clienteEntity.setContrasena("Contrasena");
    clienteEntity.setDireccion("Direccion");
    clienteEntity.setEdad(1);
    clienteEntity.setEstado(true);
    clienteEntity.setGenero(Genero.MASCULINO);
    clienteEntity.setIdentificacion("Identificacion");
    clienteEntity.setNombre("Nombre");
    clienteEntity.setTelefono("Telefono");
    when(clienteMapper.clienteDtoToClienteEntity(Mockito.<ClienteDto>any())).thenReturn(clienteEntity);

    ClienteEntity clienteEntity2 = new ClienteEntity();
    clienteEntity2.setClienteId(1L);
    clienteEntity2.setContrasena("Contrasena");
    clienteEntity2.setDireccion("Direccion");
    clienteEntity2.setEdad(1);
    clienteEntity2.setEstado(true);
    clienteEntity2.setGenero(Genero.MASCULINO);
    clienteEntity2.setIdentificacion("Identificacion");
    clienteEntity2.setNombre("Nombre");
    clienteEntity2.setTelefono("Telefono");
    Optional<ClienteEntity> ofResult = Optional.of(clienteEntity2);
    when(clienteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    clienteServiceImpl.actualizarCliente(1L, new ClienteDto("42", "Nombre", Genero.MASCULINO, 1, "Identificacion",
            "Direccion", "Telefono", "Contrasena", true));

    // Assert
    verify(clienteMapper).clienteDtoToClienteEntity(Mockito.<ClienteDto>any());
    verify(clienteRepository).findById(Mockito.<Long>any());
  }

  /**
   * Method under test:
   * {@link ClienteServiceImpl#actualizarCliente(Long, ClienteDto)}
   */
  @Test
  void testActualizarCliente2() {
    // Arrange
    when(clienteMapper.clienteDtoToClienteEntity(Mockito.<ClienteDto>any()))
            .thenThrow(new CustomException("An error occurred", HttpStatus.CONTINUE));

    ClienteEntity clienteEntity = new ClienteEntity();
    clienteEntity.setClienteId(1L);
    clienteEntity.setContrasena("Contrasena");
    clienteEntity.setDireccion("Direccion");
    clienteEntity.setEdad(1);
    clienteEntity.setEstado(true);
    clienteEntity.setGenero(Genero.MASCULINO);
    clienteEntity.setIdentificacion("Identificacion");
    clienteEntity.setNombre("Nombre");
    clienteEntity.setTelefono("Telefono");
    Optional<ClienteEntity> ofResult = Optional.of(clienteEntity);
    when(clienteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(CustomException.class, () -> clienteServiceImpl.actualizarCliente(1L, new ClienteDto("42", "Nombre",
            Genero.MASCULINO, 1, "Identificacion", "Direccion", "Telefono", "Contrasena", true)));
    verify(clienteMapper).clienteDtoToClienteEntity(Mockito.<ClienteDto>any());
    verify(clienteRepository).findById(Mockito.<Long>any());
  }

  /**
   * Method under test:
   * {@link ClienteServiceImpl#actualizarCliente(Long, ClienteDto)}
   */
  @Test
  void testActualizarCliente3() {
    // Arrange
    ClienteEntity clienteEntity = new ClienteEntity();
    clienteEntity.setClienteId(1L);
    clienteEntity.setContrasena("Contrasena");
    clienteEntity.setDireccion("Direccion");
    clienteEntity.setEdad(1);
    clienteEntity.setEstado(true);
    clienteEntity.setGenero(Genero.MASCULINO);
    clienteEntity.setIdentificacion("Identificacion");
    clienteEntity.setNombre("Nombre");
    clienteEntity.setTelefono("Telefono");
    when(clienteMapper.clienteDtoToClienteEntity(Mockito.<ClienteDto>any())).thenReturn(clienteEntity);
    ClienteEntity clienteEntity2 = mock(ClienteEntity.class);
    when(clienteEntity2.getClienteId()).thenThrow(new CustomException("An error occurred", HttpStatus.CONTINUE));
    when(clienteEntity2.getIdentificacion()).thenReturn("Identificacion");
    doNothing().when(clienteEntity2).setClienteId(Mockito.<Long>any());
    doNothing().when(clienteEntity2).setContrasena(Mockito.<String>any());
    doNothing().when(clienteEntity2).setEstado(anyBoolean());
    doNothing().when(clienteEntity2).setDireccion(Mockito.<String>any());
    doNothing().when(clienteEntity2).setEdad(anyInt());
    doNothing().when(clienteEntity2).setGenero(Mockito.<Genero>any());
    doNothing().when(clienteEntity2).setIdentificacion(Mockito.<String>any());
    doNothing().when(clienteEntity2).setNombre(Mockito.<String>any());
    doNothing().when(clienteEntity2).setTelefono(Mockito.<String>any());
    clienteEntity2.setClienteId(1L);
    clienteEntity2.setContrasena("Contrasena");
    clienteEntity2.setDireccion("Direccion");
    clienteEntity2.setEdad(1);
    clienteEntity2.setEstado(true);
    clienteEntity2.setGenero(Genero.MASCULINO);
    clienteEntity2.setIdentificacion("Identificacion");
    clienteEntity2.setNombre("Nombre");
    clienteEntity2.setTelefono("Telefono");
    Optional<ClienteEntity> ofResult = Optional.of(clienteEntity2);
    when(clienteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(CustomException.class, () -> clienteServiceImpl.actualizarCliente(1L, new ClienteDto("42", "Nombre",
            Genero.MASCULINO, 1, "Identificacion", "Direccion", "Telefono", "Contrasena", true)));
    verify(clienteEntity2).getClienteId();
    verify(clienteEntity2).setClienteId(Mockito.<Long>any());
    verify(clienteEntity2).setContrasena(eq("Contrasena"));
    verify(clienteEntity2).setEstado(eq(true));
    verify(clienteEntity2).getIdentificacion();
    verify(clienteEntity2).setDireccion(eq("Direccion"));
    verify(clienteEntity2).setEdad(eq(1));
    verify(clienteEntity2).setGenero(eq(Genero.MASCULINO));
    verify(clienteEntity2).setIdentificacion(eq("Identificacion"));
    verify(clienteEntity2).setNombre(eq("Nombre"));
    verify(clienteEntity2).setTelefono(eq("Telefono"));
    verify(clienteMapper).clienteDtoToClienteEntity(Mockito.<ClienteDto>any());
    verify(clienteRepository).findById(Mockito.<Long>any());
  }

  /**
   * Method under test:
   * {@link ClienteServiceImpl#actualizarCliente(Long, ClienteDto)}
   */
  @Test
  void testActualizarCliente4() {
    // Arrange
    ClienteEntity clienteEntity = mock(ClienteEntity.class);
    when(clienteEntity.getIdentificacion()).thenReturn("foo");
    doNothing().when(clienteEntity).setClienteId(Mockito.<Long>any());
    doNothing().when(clienteEntity).setContrasena(Mockito.<String>any());
    doNothing().when(clienteEntity).setEstado(anyBoolean());
    doNothing().when(clienteEntity).setDireccion(Mockito.<String>any());
    doNothing().when(clienteEntity).setEdad(anyInt());
    doNothing().when(clienteEntity).setGenero(Mockito.<Genero>any());
    doNothing().when(clienteEntity).setIdentificacion(Mockito.<String>any());
    doNothing().when(clienteEntity).setNombre(Mockito.<String>any());
    doNothing().when(clienteEntity).setTelefono(Mockito.<String>any());
    clienteEntity.setClienteId(1L);
    clienteEntity.setContrasena("Contrasena");
    clienteEntity.setDireccion("Direccion");
    clienteEntity.setEdad(1);
    clienteEntity.setEstado(true);
    clienteEntity.setGenero(Genero.MASCULINO);
    clienteEntity.setIdentificacion("Identificacion");
    clienteEntity.setNombre("Nombre");
    clienteEntity.setTelefono("Telefono");
    Optional<ClienteEntity> ofResult = Optional.of(clienteEntity);
    when(clienteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(CustomException.class, () -> clienteServiceImpl.actualizarCliente(1L, new ClienteDto("42", "Nombre",
            Genero.MASCULINO, 1, "Identificacion", "Direccion", "Telefono", "Contrasena", true)));
    verify(clienteEntity).setClienteId(Mockito.<Long>any());
    verify(clienteEntity).setContrasena(eq("Contrasena"));
    verify(clienteEntity).setEstado(eq(true));
    verify(clienteEntity).getIdentificacion();
    verify(clienteEntity).setDireccion(eq("Direccion"));
    verify(clienteEntity).setEdad(eq(1));
    verify(clienteEntity).setGenero(eq(Genero.MASCULINO));
    verify(clienteEntity).setIdentificacion(eq("Identificacion"));
    verify(clienteEntity).setNombre(eq("Nombre"));
    verify(clienteEntity).setTelefono(eq("Telefono"));
    verify(clienteRepository).findById(Mockito.<Long>any());
  }

  /**
   * Method under test:
   * {@link ClienteServiceImpl#actualizarCliente(Long, ClienteDto)}
   */
  @Test
  void testActualizarCliente5() {
    // Arrange
    Optional<ClienteEntity> emptyResult = Optional.empty();
    when(clienteRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
    new CustomException("error", HttpStatus.CONTINUE);

    // Act
    clienteServiceImpl.actualizarCliente(1L, new ClienteDto("42", "Nombre", Genero.MASCULINO, 1, "Identificacion",
            "Direccion", "Telefono", "Contrasena", true));

    // Assert
    verify(clienteRepository).findById(Mockito.<Long>any());
  }

  /**
   * Method under test: {@link ClienteServiceImpl#borrarCliente(Long)}
   */
  @Test
  void testBorrarCliente() {

    // Arrange and Act
    clienteServiceImpl.borrarCliente(1L);
  }

}
