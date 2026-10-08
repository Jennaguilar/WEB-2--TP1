package com.ejemplo.Tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CrearFavoritoRequest(

    @NotNull
    Long productoId,

    @NotNull
    Long listaId,

    @NotBlank@Size(max = 200)
    String notaPersonal
) {

}
