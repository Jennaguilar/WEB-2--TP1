package com.ejemplo.Tp1.client;
import java.util.List;

public record DummyJsonProductoResponse(
        List<DummyJsonProducto> products,
        int total,
        int skip,
        int limit
) {
}