package com.ejemplo.Tp1.repository;

import com.ejemplo.Tp1.entity.ListaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ListaJpaRepository extends JpaRepository<ListaEntity, Long> {

}
