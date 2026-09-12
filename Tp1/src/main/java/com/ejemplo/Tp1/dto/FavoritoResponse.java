package com.ejemplo.Tp1.dto;

import java.time.LocalDateTime;

//Implementación temporal basada en una colección, sin usar JPA.

public record FavoritoResponse(
    Long id,
    Long productoId,
    String notaPersonal,
    LocalDateTime fechaAgregado
) {}