package com.ejemplo.Tp1.controllers;

//IMPORT
import com.ejemplo.Tp1.dto.ProductoDTO;
import com.ejemplo.Tp1.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service){
        this.service = service;
    }

    @GetMapping
    public List<ProductoDTO> listar() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ProductoDTO buscar(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
}
