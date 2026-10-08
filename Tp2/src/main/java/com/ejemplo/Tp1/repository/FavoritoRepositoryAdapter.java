package com.ejemplo.Tp1.repository;


import com.ejemplo.Tp1.entity.FavoritoEntity;
import com.ejemplo.Tp1.model.Favorito;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FavoritoRepositoryAdapter implements FavoritoRepository {
    private final FavoritoJpaRepository jpaRepository;
    private final ListaJpaRepository listaJpaRepository;

    public FavoritoRepositoryAdapter(
            FavoritoJpaRepository jpaRepository,
            ListaJpaRepository listaJpaRepository
    ) {
        this.jpaRepository = jpaRepository;
        this.listaJpaRepository = listaJpaRepository;
    }

    @Override
public Favorito guardar(Favorito favorito) {
    FavoritoEntity entity = new FavoritoEntity(
            favorito.id(),
            favorito.productoId(),
            favorito.notaPersonal(),
            favorito.fechaAgregado(),
            listaJpaRepository.getReferenceById(favorito.listaId())
    );

    return aDominio(jpaRepository.save(entity));
}
    @Override
    public List<Favorito> buscarTodos() {
        return jpaRepository.findAll()
                .stream()
                .map(this::aDominio)
                .toList();
    }

    @Override
    public Optional<Favorito> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(this::aDominio);
    }

    @Override
public List<Favorito> buscarPorListaId(Long listaId) {
    return jpaRepository.findByListaId(listaId)
            .stream()
            .map(this::aDominio)
            .toList();
}

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }

    private Favorito aDominio(FavoritoEntity entity) {
        return new Favorito(
                entity.getId(),
                entity.getProductoId(),
                entity.getNotaPersonal(),
                entity.getFechaAgregado(),
                entity.getLista() == null ? null : entity.getLista().getId()
        );
    }
}
