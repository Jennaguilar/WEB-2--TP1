package com.ejemplo.Tp1.controllers;

import com.ejemplo.Tp1.dto.CrearListaRequest;
import com.ejemplo.Tp1.dto.FavoritoResponse;
import com.ejemplo.Tp1.dto.ListaResponse;
import com.ejemplo.Tp1.dto.MoverFavoritosRequest;
import com.ejemplo.Tp1.service.ListaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/listas")
public class ListaController {

    private final ListaService service;

    public ListaController(ListaService service) {
        this.service = service;
    }

    @Operation(
        summary = "Crear una lista",
        description = "Crea una nueva lista de favoritos"
)


    @PostMapping
    public ResponseEntity<ListaResponse> crear(
            @Valid @RequestBody CrearListaRequest request
    ) {
        ListaResponse creada = service.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creada);
    }
    @Operation(
            summary = "Listar listas",
            description = "Devuelve todas las listas de favoritos"
    )
    @GetMapping
    public List<ListaResponse> listar() {
        return service.listar();
    }

    @Operation(
            summary = "Obtener una lista",
            description = "Devuelve una lista por su identificador"
    )
    @GetMapping("/{id}")
    public ListaResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @Operation(
            summary = "Eliminar una lista",
            description = "Elimina una lista solamente si no tiene favoritos"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Listar favoritos de una lista",
            description = "Devuelve los favoritos asociados a una lista"
    )
    @GetMapping("/{id}/favoritos")
    public List<FavoritoResponse> listarFavoritos(@PathVariable Long id) {
        return service.listarFavoritos(id);
    }

    @Operation(
            summary = "Mover favoritos",
            description = "Mueve los favoritos de una lista a otra y elimina la lista de origen"
    )
    @PostMapping("/{origenId}/mover-favoritos")
    public ResponseEntity<Void> moverFavoritos(
            @PathVariable Long origenId,
            @Valid @RequestBody MoverFavoritosRequest request
    ) {
        service.moverFavoritos(origenId, request);
        return ResponseEntity.noContent().build();
    }


}
