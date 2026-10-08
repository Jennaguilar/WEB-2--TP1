package com.ejemplo.Tp1.repository;

import com.ejemplo.Tp1.model.Lista;

import java.util.List;
import java.util.Optional;

public interface ListaRepository {

    List<Lista> buscarTodos();

    Optional<Lista> buscarPorId(Long id);

    Lista guardar(Lista lista);

    void eliminar(Long id);
}