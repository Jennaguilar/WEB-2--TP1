package com.ejemplo.Tp1.model;

import java.time.LocalDateTime;

public record Favorito(
    Long id,
    Long productoId,
    String notaPersonal,
    LocalDateTime fechaAgregado
) {

}
