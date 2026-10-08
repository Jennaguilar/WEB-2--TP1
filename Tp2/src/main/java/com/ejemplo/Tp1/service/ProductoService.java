package com.ejemplo.Tp1.service;
//importaciones
import com.ejemplo.Tp1.client.DummyJsonProducto;
import com.ejemplo.Tp1.client.DummyJsonClient;
import com.ejemplo.Tp1.dto.ProductoDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/*
*
* Consume la API externa DummyJSON y mapea la información obtenida.
*
*/
@Service
public class ProductoService {
    private final DummyJsonClient client;

    public ProductoService(DummyJsonClient client) {
        this.client = client;
    }

    
    private ProductoDTO mapearADto(DummyJsonProducto externo) {
        return new ProductoDTO(
                externo.id(),
                externo.title(),                           
                externo.description(),              
                BigDecimal.valueOf(externo.price()),    
                externo.category()                     
        );
    }

    public List<ProductoDTO> listarTodos() {
        return client.obtenerTodos()
                .stream()
                .map(this::mapearADto) 
                .toList();
    }

    public ProductoDTO buscarPorId(Long id) {
        DummyJsonProducto productoExterno = client.obtenerPorId(id);
        return mapearADto(productoExterno);
    }

}