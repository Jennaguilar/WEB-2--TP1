## Transacciones

La operación de mover favoritos modifica varias filas y elimina una lista.
Por eso utiliza `@Transactional`. Si una escritura falla, la transacción se
revierte completa y evita que queden favoritos parcialmente movidos.

Sin `@Transactional`, podrían moverse algunos favoritos y luego fallar la
eliminación de la lista de origen, dejando la base en un estado inconsistente.