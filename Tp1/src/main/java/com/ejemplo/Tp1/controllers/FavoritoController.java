package com.ejemplo.Tp1.controllers;

import com.ejemplo.Tp1.dto.CrearFavoritoRequest;
import com.ejemplo.Tp1.dto.FavoritoResponse;
import com.ejemplo.Tp1.service.FavoritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/favoritos") //Define la ruta base para todos los metodos
public class FavoritoController {

    private final FavoritoService service;

    public FavoritoController(FavoritoService service) {
        this.service = service;
    }


    @GetMapping
    //Listar
    public List<FavoritoResponse> listar() {
        return service.listarTodos(); //Spring responde 200 OK por defecto
    }



    //GET - exito: 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<FavoritoResponse> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                //devuelve 404
                .orElse(ResponseEntity.notFound().build());
    }



    //Crear: POST - 201 Created
    @PostMapping
    public ResponseEntity<FavoritoResponse> crear(@RequestBody CrearFavoritoRequest request) {
        FavoritoResponse creado = service.crear(request);
        // Usamos ResponseEntity para forzar el código 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }



    //Actualizar: PUT - 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<FavoritoResponse> actualizar(
            @PathVariable Long id, 
            @RequestBody CrearFavoritoRequest request) {
        
        return service.actualizar(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




    //Eliminar: DELETE | 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        //04 No Content indicando que se borró y no hay cuerpo en la respuesta
        return ResponseEntity.noContent().build();
    }
}
