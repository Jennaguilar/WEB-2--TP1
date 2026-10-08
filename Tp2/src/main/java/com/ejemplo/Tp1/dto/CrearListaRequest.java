package com.ejemplo.Tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrearListaRequest(

        @NotBlank
        @Size(max = 100)
        String nombre
) {
}