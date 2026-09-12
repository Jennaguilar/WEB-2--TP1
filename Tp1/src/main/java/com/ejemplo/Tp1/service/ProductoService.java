package com.ejemplo.Tp1.service;
//importaciones
import com.ejemplo.Tp1.client.DummyJsonProducto;
import com.ejemplo.Tp1.client.DummyJsonProductosResponse;
import com.ejemplo.Tp1.dto.ProductoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoService {
    private final RestClient restClient;
    public ProductoService() {
        this.restClient = RestClient.builder().baseUrl("https://dummyjson.com").build();
    }

    // OBTENER TODS
    public List<ProductoDTO> obtenerTodos() {
        DummyJsonProductosResponse respuesta = restClient.get()
                .uri("/products")
                .retrieve()
                .body(DummyJsonProductosResponse.class);

        if (respuesta == null || respuesta.products() == null) {
            return List.of();
        }



        // Mapeamos cada producto externo a tu ProductoDTO
        return respuesta.products().stream()
                .map(this::mapearADto)
                .toList();
    }


    //OBTENER POR ID
    public ProductoDTO obtenerPorId(Long id) {
        DummyJsonProducto externo = restClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .body(DummyJsonProducto.class);
                
        return mapearADto(externo);
    }

    // MAPEAR A DTO
    private ProductoDTO mapearADto(DummyJsonProducto externo) {
        if (externo == null) return null;
        return new ProductoDTO(
            externo.id(),
            externo.title(),
            externo.description(),
            BigDecimal.valueOf(externo.price()),
            externo.stock()
        );
    }
}