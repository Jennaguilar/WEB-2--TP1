package com.ejemplo.Tp1.repository;

import com.ejemplo.Tp1.entity.FavoritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoJpaRepository extends JpaRepository<FavoritoEntity, Long> {
    List<FavoritoEntity> findByListaId(Long listaId);
}
