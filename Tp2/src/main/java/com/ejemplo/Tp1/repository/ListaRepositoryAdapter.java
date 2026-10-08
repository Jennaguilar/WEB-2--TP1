package com.ejemplo.Tp1.repository;

import com.ejemplo.Tp1.entity.ListaEntity;
import com.ejemplo.Tp1.model.Lista;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ListaRepositoryAdapter implements ListaRepository {

    private final ListaJpaRepository jpaRepository;

    public ListaRepositoryAdapter(ListaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Lista> buscarTodos() {
        return jpaRepository.findAll()
                .stream()
                .map(this::aDominio)
                .toList();
    }

    @Override
    public Optional<Lista> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(this::aDominio);
    }

    @Override
    public Lista guardar(Lista lista) {
        ListaEntity entity = new ListaEntity(
                lista.id(),
                lista.nombre()
        );

        return aDominio(jpaRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }

    private Lista aDominio(ListaEntity entity) {
        return new Lista(
                entity.getId(),
                entity.getNombre()
        );
    }
}