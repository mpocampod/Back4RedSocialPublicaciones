package com.makaia.back4.JpaMySql.dtos;

public class ComentarioDTO {

    private Long id;
    private String contenido;
    private Long usuario;
    private Long publicacion;

    public ComentarioDTO() {
    }

    public ComentarioDTO(Long id, String contenido, Long usuario, long publicacion) {
        this.id = id;
        this.contenido = contenido;
        this.usuario = usuario;
        this.publicacion = publicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Long getUsuarioId() {
        return usuario;
    }

    public void setUsuarioId(Long usuario) {
        this.usuario = usuario;
    }

    public Long getIdPublicacion() {
        return publicacion;
    }

    public void setIdPublicacion(Long publicacion) {
        this.publicacion = publicacion;
    }
}