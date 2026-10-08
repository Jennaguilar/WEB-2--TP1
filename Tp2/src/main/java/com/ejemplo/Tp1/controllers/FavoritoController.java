package com.ejemplo.Tp1.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid; //
import com.ejemplo.Tp1.dto.CrearFavoritoRequest;
import com.ejemplo.Tp1.dto.FavoritoResponse;
import com.ejemplo.Tp1.service.FavoritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/favoritos") //Define la ruta base para todos los metodos
@Tag(name="Favoritos", description = "Operaciones CRUD sobre la lista de favoritos en memoria")
public class FavoritoController {

    private final FavoritoService service;

    public FavoritoController(FavoritoService service) {
        this.service = service;
    }


   @GetMapping
    @Operation(summary="Listar favoritos", description = "Lista todos los favoritos que se guardaron")
    public List<FavoritoResponse> listar(
            @RequestParam(defaultValue = "") String q) {
        return service.listar(q);
    }

    @GetMapping("/{id}")
    @Operation(summary="Buscar por Id", description = "Muestra el favorito específico segun el id ingresado")
    public FavoritoResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @Operation(summary="Agregar a favoritos", description = "Agrega un producto de la api externa a favoritos y se le asocia una nota personal")
    public ResponseEntity<FavoritoResponse> crear(
            @Valid @RequestBody CrearFavoritoRequest request) {
        FavoritoResponse creado = service.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary="Modifica un favorito", description = "Permite actualizar los datos de un producto en el listado de favoritos")
    public FavoritoResponse actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CrearFavoritoRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar favorito", description = "permite eliminar un favorito del listado en memoria")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}