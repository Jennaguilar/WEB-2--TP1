package com.ejemplo.Tp1.repository;

import com.ejemplo.Tp1.model.Favorito;
import org.springframework.stereotype.Repository;
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
    public List<Favorito> buscarTodos() {
        return new ArrayList<>(datos.values());
    }

    @Override
    public Optional<Favorito> buscarPorId(Long id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public Favorito guardar(Favorito Favorito) {
        Long id = Favorito.id() == null
            ? secuencia.incrementAndGet()
            : Favorito.id();

        Favorito guardado = new Favorito(
            id, Favorito.productoId(), Favorito.notaPersonal(), Favorito.fechaAgregado()
        );
        datos.put(id, guardado);
        return guardado;
    }

    @Override
    public void eliminar(Long id) {
        datos.remove(id);
    }
}