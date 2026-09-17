package com.ejemplo.Tp1.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
//IMPORT
import com.ejemplo.Tp1.dto.ProductoDTO;
import com.ejemplo.Tp1.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name ="Productos", description = "Catálogo de productos (obtenidos de la API DummyJSON)")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service){
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar catálogo", description = "Devuelve la lista completa de productos mapeados desde la API externa DummyJSON.")
    public List<ProductoDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Busca y devuelve el detalle de un producto específico del catálogo.")
    public ProductoDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
    //Recibe las peticiones web y expone el catalogo al usuario.
}
