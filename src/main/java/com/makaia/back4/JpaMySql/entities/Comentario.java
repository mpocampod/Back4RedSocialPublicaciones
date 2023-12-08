package com.makaia.back4.JpaMySql.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    private String contenido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario", nullable = false)
    Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publicacion", nullable = false)
    Publicacion publicacion;

    public Comentario(String contenido) {
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

}