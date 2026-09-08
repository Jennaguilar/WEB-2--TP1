# TP1 · Spring Boot, API REST y arquitectura en capas

Aplicación backend desarrollada con **Spring Boot** como introducción al desarrollo de APIs REST y a la arquitectura en capas.

El proyecto implementa una API propia con **productos y favoritos**, incorporando DTOs, validación de datos, manejo centralizado de errores y documentación mediante Swagger/OpenAPI.

> **Nota:** Este práctico no utiliza una base de datos ni JPA. Los datos se almacenan en memoria. La persistencia con PostgreSQL, JPA/Hibernate y Flyway se aborda en el TP2.

## 🛠️ Tecnologías

* Java 25
* Spring Boot 4.1.x
* Maven
* Spring Web MVC
* Bean Validation
* Springdoc OpenAPI / Swagger
* RestClient
* API externa DummyJSON

## 🏗️ Arquitectura

El proyecto utiliza una arquitectura en capas:

```text
Controller
    ↓
Service
    ↓
Repository
```

Los **DTOs** permiten desacoplar los datos expuestos por la API de los modelos internos de la aplicación.

## 📌 Funcionalidades

### Productos

El backend consume la API pública de **DummyJSON** y transforma sus datos para exponer un contrato propio.

Endpoints principales:

```text
GET /api/productos
GET /api/productos/{id}
```

### Favoritos

Se implementa un recurso propio de favoritos con almacenamiento en memoria y operaciones CRUD completas.

```text
POST   /api/favoritos
GET    /api/favoritos
GET    /api/favoritos/{id}
PUT    /api/favoritos/{id}
DELETE /api/favoritos/{id}
```

## ✅ Validación y manejo de errores

La API incorpora:

* Validación de datos mediante Bean Validation.
* Respuestas `400 Bad Request` ante datos inválidos.
* `404 Not Found` cuando no existe un favorito.
* Manejo centralizado de excepciones mediante `@ControllerAdvice`.
* Tratamiento de errores al consumir el servicio externo.

## 📚 Documentación

La API está documentada utilizando **Swagger/OpenAPI**, permitiendo consultar y probar los endpoints disponibles desde Swagger UI.

## 🚀 Ejecución

Clonar el repositorio y ejecutar el proyecto mediante Maven:

```bash
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

Una vez iniciada la aplicación, se pueden consultar los endpoints y la documentación de la API desde Swagger UI.

## 🧪 Evidencia

El proyecto incluye evidencia del funcionamiento de los endpoints mediante casos de éxito y error, utilizando capturas, Postman, Insomnia o archivos `.http`.

## 📖 Objetivos del TP

* Configurar un proyecto Spring Boot con Maven.
* Aplicar arquitectura en capas.
* Consumir un servicio web externo.
* Diseñar una API REST propia.
* Trabajar con DTOs.
* Implementar operaciones CRUD.
* Aplicar validación de datos.
* Implementar manejo uniforme de errores.
* Documentar una API con Swagger/OpenAPI.
