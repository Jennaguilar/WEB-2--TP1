## 4. Puertos y adaptadores

### Qué cambió

Para migrar la persistencia de memoria a PostgreSQL mediante JPA se agregaron o reemplazaron estas clases:

- `FavoritoEntity`: representa la tabla `favoritos` en PostgreSQL.
- `FavoritoJpaRepository`: repositorio de Spring Data JPA.
- `FavoritoRepositoryAdapter`: adapta las operaciones JPA al puerto `FavoritoRepository`.
- `FavoritoRepositoryMemoria`: fue eliminado porque ya no se utiliza.
- `ListaEntity`, `ListaJpaRepository` y `ListaRepositoryAdapter`: se agregaron para persistir las listas.

### Qué se mantuvo

La interfaz `FavoritoRepository` se mantuvo como contrato. También se conservó la lógica principal de `FavoritoController` y `FavoritoService` durante el reemplazo del almacenamiento en memoria por JPA.

Los DTOs y el dominio se ampliaron posteriormente con `listaId` para representar la relación entre favoritos y listas.

Esto fue posible porque `FavoritoRepository` funciona como un puerto: define las operaciones que necesita la aplicación, pero no indica cómo se implementan. Tanto `FavoritoRepositoryMemoria` del TP1 como `FavoritoRepositoryAdapter` del TP2 son adapters intercambiables detrás de esa misma interfaz.

El Service no necesita conocer si los datos se guardan en un `Map` o en PostgreSQL. Solo utiliza el contrato definido por `FavoritoRepository`.