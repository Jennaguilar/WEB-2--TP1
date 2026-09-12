package com.ejemplo.Tp1.service;

import com.ejemplo.Tp1.dto.CrearFavoritoRequest;
import com.ejemplo.Tp1.dto.FavoritoResponse;
import com.ejemplo.Tp1.model.Favorito;
import com.ejemplo.Tp1.repository.FavoritoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    //MAPEO  DE ENTIDAD A DTO
    private FavoritoResponse mapearAResponse(Favorito favorito) {
        return new FavoritoResponse(
            favorito.id(),
            favorito.productoId(),
            favorito.notaPersonal(),
            favorito.fechaAgregado()
        );
    }

    //METODOS DE EL MAPEO
    public List<FavoritoResponse> listarTodos() {
        return repository.listarTodos().stream()
            .map(this::mapearAResponse)
            .toList();
    }
    
    public Optional<FavoritoResponse> buscarPorId(Long id) {
        return repository.buscarPorId(id)
            .map(this::mapearAResponse);
    }

    public FavoritoResponse crear(CrearFavoritoRequest request) {
        Favorito nuevoFavorito = new Favorito(
            null, // El repositorio se encarga de generar el ID
            request.productoId(),
            request.notaPersonal(),
            LocalDateTime.now()
        );
        
        Favorito guardado = repository.guardar(nuevoFavorito);
        return mapearAResponse(guardado);
    }

}
