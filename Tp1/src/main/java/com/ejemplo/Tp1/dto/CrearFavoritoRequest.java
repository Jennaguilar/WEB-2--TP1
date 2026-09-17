package com.ejemplo.Tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CrearFavoritoRequest(

    @NotNull
    Long productoId,

    @NotBlank@Size(max = 200)
    String notaPersonal
) {

}
