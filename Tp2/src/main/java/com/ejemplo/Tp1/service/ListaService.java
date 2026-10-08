package com.ejemplo.Tp1.service;

import com.ejemplo.Tp1.dto.CrearListaRequest;
import com.ejemplo.Tp1.dto.FavoritoResponse;
import com.ejemplo.Tp1.dto.ListaResponse;
import com.ejemplo.Tp1.dto.MoverFavoritosRequest;
import com.ejemplo.Tp1.exception.ListaNoVaciaException;
import com.ejemplo.Tp1.exception.RecursoNoEncontradoException;
import com.ejemplo.Tp1.model.Favorito;
import com.ejemplo.Tp1.model.Lista;
import com.ejemplo.Tp1.repository.FavoritoRepository;
import com.ejemplo.Tp1.repository.ListaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ListaService {

    private final ListaRepository repository;
    private final FavoritoRepository favoritoRepository;
    

    public ListaService(
        ListaRepository repository,
        FavoritoRepository favoritoRepository
) {
    this.repository = repository;
    this.favoritoRepository = favoritoRepository;
}

    public List<ListaResponse> listar() {
        return repository.buscarTodos()
                .stream()
                .map(this::aResponse)
                .toList();
    }

    public ListaResponse buscar(Long id) {
        return repository.buscarPorId(id)
                .map(this::aResponse)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la lista " + id
                ));
    }

    public ListaResponse crear(CrearListaRequest request) {
        Lista nueva = new Lista(
                null,
                request.nombre()
        );

        return aResponse(repository.guardar(nueva));
    }

    public void eliminar(Long id) {
    buscar(id);

    if (!favoritoRepository.buscarPorListaId(id).isEmpty()) {
        throw new ListaNoVaciaException(
                "La lista " + id + " todavía tiene favoritos"
        );
    }

    repository.eliminar(id);
}

    private ListaResponse aResponse(Lista lista) {
        return new ListaResponse(
                lista.id(),
                lista.nombre()
        );
    }

    public List<FavoritoResponse> listarFavoritos(Long listaId) {
    buscar(listaId);

    return favoritoRepository.buscarPorListaId(listaId)
            .stream()
            .map(favorito -> new FavoritoResponse(
                    favorito.id(),
                    favorito.productoId(),
                    favorito.notaPersonal(),
                    favorito.fechaAgregado(),
                    favorito.listaId()
            ))
            .toList();
}

    @Transactional
    public void moverFavoritos(Long origenId, MoverFavoritosRequest request) {
        buscar(origenId);
        buscar(request.listaDestinoId());

        List<Favorito> favoritos = favoritoRepository.buscarPorListaId(origenId);

        for (Favorito favorito : favoritos) {
            Favorito movido = new Favorito(
                    favorito.id(),
                    favorito.productoId(),
                    favorito.notaPersonal(),
                    favorito.fechaAgregado(),
                    request.listaDestinoId()
            );

            favoritoRepository.guardar(movido);
        }

        repository.eliminar(origenId);
    }

}
