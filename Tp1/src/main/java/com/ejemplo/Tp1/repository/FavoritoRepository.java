package com.ejemplo.Tp1.repository;

import com.ejemplo.Tp1.model.Favorito;
import java.util.List;
import java.util.Optional;

public interface FavoritoRepository {

    List<Favorito> listarTodos();
    Optional<Favorito> buscarPorId(Long id);
    Favorito guardar(Favorito favorito);
    void eliminar(Long id);

}
