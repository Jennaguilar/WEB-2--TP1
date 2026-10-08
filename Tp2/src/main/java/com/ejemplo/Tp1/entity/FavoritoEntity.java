package com.ejemplo.Tp1.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favoritos")
public class FavoritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    @Column(name = "nota", nullable = false, length = 500)
    private String notaPersonal;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAgregado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lista_id")
    private ListaEntity lista;

    protected FavoritoEntity() {
    }

    public FavoritoEntity(
            Long productoId,
            String notaPersonal,
            LocalDateTime fechaAgregado
    ) {
        this.productoId = productoId;
        this.notaPersonal = notaPersonal;
        this.fechaAgregado = fechaAgregado;
    }

    public FavoritoEntity(
            Long id,
            Long productoId,
            String notaPersonal,
            LocalDateTime fechaAgregado,
            ListaEntity lista
    ) {
        this.id = id;
        this.productoId = productoId;
        this.notaPersonal = notaPersonal;
        this.fechaAgregado = fechaAgregado;
        this.lista = lista;
    }

    public Long getId() {
        return id;
    }

    public Long getProductoId() {
        return productoId;
    }

    public String getNotaPersonal() {
        return notaPersonal;
    }

    public LocalDateTime getFechaAgregado() {
        return fechaAgregado;
    }

    public ListaEntity getLista() {
        return lista;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public void setNotaPersonal(String notaPersonal) {
        this.notaPersonal = notaPersonal;
    }

    public void setFechaAgregado(LocalDateTime fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }

    public void setLista(ListaEntity lista) {
        this.lista = lista;
    }
}
