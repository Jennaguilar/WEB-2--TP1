package com.ejemplo.Tp1.dto;

import jakarta.validation.constraints.NotNull;

public record MoverFavoritosRequest(

        @NotNull
        Long listaDestinoId
) {
}