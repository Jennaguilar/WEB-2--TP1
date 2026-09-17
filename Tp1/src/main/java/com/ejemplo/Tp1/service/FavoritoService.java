package com.ejemplo.Tp1.service;

import com.ejemplo.Tp1.dto.CrearFavoritoRequest;
import com.ejemplo.Tp1.dto.FavoritoResponse;

import com.ejemplo.Tp1.exception.RecursoNoEncontradoException;
import com.ejemplo.Tp1.model.Favorito;
import com.ejemplo.Tp1.repository.FavoritoRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


/*
*
* Traduce manualmente los datos entre la base de datos y tus DTOs.
*
 */

@Service
public class FavoritoService {
    private final FavoritoRepository repository;

    public FavoritoService(FavoritoRepository repository) {
        this.repository = repository;
    }

    public List<FavoritoResponse> listar(String texto) {
        String filtro = texto == null ? "" : texto.trim().toLowerCase();

        return repository.buscarTodos().stream()
            .filter(favorito -> favorito.notaPersonal().toLowerCase().contains(filtro))
            .map(this::aResponse)
            .toList();
    }

    public FavoritoResponse buscar(Long id) {
        return repository.buscarPorId(id)
            .map(this::aResponse)
            .orElseThrow(() -> new RecursoNoEncontradoException(
                "No existe el Favorito " + id
            ));
    }

   public FavoritoResponse crear(CrearFavoritoRequest request) {
        Favorito nuevo = new Favorito(
            null,
            request.productoId(),
            request.notaPersonal(),
            LocalDateTime.now()
        );
        return aResponse(repository.guardar(nuevo));
    }

    public FavoritoResponse actualizar(Long id, CrearFavoritoRequest request) {
        buscar(id);
        Favorito actualizado = new Favorito(
            id, 
            request.productoId(),
            request.notaPersonal(),
            LocalDateTime.now()
        );
        return aResponse(repository.guardar(actualizado));
    }
    public void eliminar(Long id) {
        buscar(id);
        repository.eliminar(id);
    }

    private FavoritoResponse aResponse(Favorito favorito) {
        return new FavoritoResponse(
            favorito.id(),
            favorito.productoId(),
            favorito.notaPersonal(),
            favorito.fechaAgregado()
        );
    }
}