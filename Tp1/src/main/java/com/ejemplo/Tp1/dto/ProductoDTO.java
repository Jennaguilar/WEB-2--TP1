package com.ejemplo.Tp1.dto;

import java.math.BigDecimal;

public record ProductoDTO(
    Long id,
    String title,
    String description,
    BigDecimal price,
    int stock
) {
    
}
