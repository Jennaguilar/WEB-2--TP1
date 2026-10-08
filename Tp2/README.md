### Evolución de migraciones

La columna `lista_id` se vuelve obligatoria mediante una nueva migración V4,
porque Flyway guarda un checksum de cada migración aplicada y rechaza cambios
posteriores en V1, V2 o V3.
## Levantar PostgreSQL

Docker Desktop debe estar iniciado. Desde la raiz del proyecto ejecutar:

```powershell
docker compose up -d postgres
```

La aplicacion se conecta al puerto `5433` del equipo, que corresponde al
puerto `5432` dentro del contenedor. Flyway aplica automaticamente las
migraciones `V1` a `V4` al iniciar Spring Boot.

## Transacciones

Mover favoritos modifica varias filas y elimina la lista de origen, por eso
se ejecuta con `@Transactional`. Si una escritura falla, la transaccion se
revierte completa y evita que la base quede en un estado parcialmente modificado.

Sin `@Transactional`, podrian moverse algunos favoritos y fallar luego la
eliminacion de la lista, dejando datos inconsistentes.
