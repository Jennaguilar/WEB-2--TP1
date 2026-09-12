package com.ejemplo.Tp1.repository;

import com.ejemplo.Tp1.model.Favorito;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/*
Con este repositorio, tu backend ya es capaz de almacenar 
el estado de la aplicación mientras el servidor esté encendido. 
*/
@Repository
public class FavoritoRepositoryMemoria implements FavoritoRepository {
    
    private final Map<Long, Favorito> datos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong();

    @Override
    public List<Favorito> listarTodos() {
        return new ArrayList<>(datos.values());
    }

    @Override
    public Optional<Favorito> buscarPorId(Long id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public Favorito guardar(Favorito favorito) {
        // Simulamos el autoincremento de una base de datos
        Long id = (favorito.id() == null) ? secuencia.incrementAndGet() : favorito.id();
        LocalDateTime fecha = (favorito.fechaAgregado() == null) ? LocalDateTime.now() : favorito.fechaAgregado();
        
        Favorito nuevo = new Favorito(id, favorito.productoId(), favorito.notaPersonal(), fecha);
        datos.put(id, nuevo);
        
        return nuevo;
    }

    @Override
    public void eliminar(Long id) {
        datos.remove(id);
    }
}