# Prueva Técnica JAVA

## Microservicio para registrar un usuario + token JWT

Este proyecto se desarrollaron dos microservicios con el objetivo de demostrar  conocimiento técnico.

**Funcionalidades del API**
- [x] F1 : Generación CRUD´s para 
    - /clientes
    - /cuentas
    - /movimientos
- [x] F2: Registro de movimientos
    - movimientos con valores + y -
    - actualizar saldo
    - registrar los movimientos
- [x] F3: Alertar cuando se un movimiento provoca "Saldo no disponible"
- [x] F4: Un servicio que permita ver un Reporte de los movimientos de un cliente.
- [x] F5: Pruebas unitarias 

**Requisitos opcionales**

- [x] F6: Pruebas de integración
- [x] F7: Despliegue en contendores.

## Se usa JAVA 17, Spring Boot 3, Spring Security JWT , JPA + BD-PostgreSql

Se crearon dos microservicios:

```
- ms-cliente
- ms-cuenta-movimientos
```

- Con el modelo de programación reactiva, que permite operaciones asincronas y sin bloqueo
Usamos **Spring Webflux**
- Spring JPA, para el mapeo de la entidades y sus relaciones
- Lombok para agilizar el desarrollo
- MapStruct para mapear los datos de entrada y salida entre Dto/Entity
- Sprind-Docs para la documentacion swagger con openApi
- JUnit y Mockito para las Pruebas Unitarias
- DockerFile y DockerCompose para el despliegue en contenedores
- SonaLint para calidad del codigo
- Se Maneja las excepciones con @RestControllerAdvice
- Se desarrollo con el patron de diseño MVC
- Se adjunta el backup de la BD para su interacción
- Se adjunta la colección de postman para su pruebas funcionales




## Comando para clonar y ejecutar la aplicacion :
Antes de clonar el repositorio verifique que tenga instalado [JAVA 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) y MAVEN para el build con un ambiente local

Link del repositorio github: https://github.com/rogerCL2020/prueba-tecnica-devsu

Comando para clonar:
```
 git clone https://github.com/rogerCL2020/prueba-tecnica-devsu.git
```

Ubiquese en la raiz del proyecto clonado y abra su shell o cmd.

Ejecute el sgt comando:

```shell
docker compose up -d
```

## Demostracion y Pruebas con POSTMAN


**Caso Prueba 1:**
```
Creacion de usuarios ,
Response: status 201 Created
```
Curl:
```
curl --location 'localhost:8081/api/demo/v1/clientes' \
--header 'Content-Type: application/json' \
--data '{
  "nombre": "Juan Osorio",
  "genero": "MASCULINO",
  "edad": 58,
  "identificacion": "1597",
  "direccion": "13 de junio y Equinoccial",
  "telefono": "098874587",
  "password": "1597", 
  "estado": true
}'

```


![img.png](postman%2Fpruebas%2Fimg.png)

**Caso Prueba 2 y 3:**
```
Creacion de cuentas de usuario:
Response: status 201 Created
```
Curl:
```
curl --location 'localhost:8080/api/demo/v1/cuentas' \
--header 'Content-Type: application/json' \
--data '{
  "numeroCuenta": "585545",
  "tipoCuenta": "CORRIENTE",
  "saldo": 1000,
  "estado": true,
  "cliente": {
    "clienteId": 1,
    "nombre": "Jose Lema"
  }
}'
```

![img_1.png](postman%2Fpruebas%2Fimg_1.png)

**Caso Prueba 4:**
```
Registrar movimientos
Response: status 201 Created
```
Curl:
```
curl --location 'localhost:8080/api/demo/v1/movimientos' \
--data '{
"numeroCuenta": "496825",
"valor": -540
}'
```
![img_2.png](postman%2Fpruebas%2Fimg_2.png)

**Caso Prueba 5:**
```
Listado movimientos por fechas x usuario
Response: status 200 OK
```
Curl:
```
curl --location 'localhost:8080/api/demo/v1/movimientos/reporte?fechaDesde=10%2F05%2F2024&fechaHasta=20%2F05%2F2024&clienteId=2'

```
![img_3.png](postman%2Fpruebas%2Fimg_3.png)

**Caso Prueba 6:**
```
Saldo no disponible
Response: status 409 Conflict
mensaje:SALDO_INSUFICIENTE
```

Curl:
```
curl --location 'localhost:8080/api/demo/v1/movimientos' \
--header 'Content-Type: application/json' \
--data '{
    "numeroCuenta": "496825",
    "valor": -100
}'
```
![img_4.png](postman%2Fpruebas%2Fimg_4.png)