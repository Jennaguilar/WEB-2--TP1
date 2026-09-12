package com.ejemplo.Tp1.client;

public record DummyJsonProducto(

    Long id,
    String title,
    String description,
    String category,
    double price,
    double discountPercentage,
    int stock,
    double rating,
    String thumbnail
) {

}
