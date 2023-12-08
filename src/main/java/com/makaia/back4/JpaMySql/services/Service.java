package com.makaia.back4.JpaMySql.services;

import com.makaia.back4.JpaMySql.dtos.CrearDTO;
import com.makaia.back4.JpaMySql.entities.Publicacion;
import com.makaia.back4.JpaMySql.entities.Usuario;
import com.makaia.back4.JpaMySql.entities.Comentario;
import com.makaia.back4.JpaMySql.exceptions.RedSocialApiException;
import com.makaia.back4.JpaMySql.repositories.ComentarioRepository;
import com.makaia.back4.JpaMySql.repositories.PublicacionRepository;
import com.makaia.back4.JpaMySql.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
public class Service {
    PublicacionRepository repository;
    UsuarioRepository usuarioRepository;
    ComentarioRepository comentarioRepository;

    @Autowired
    public Service(PublicacionRepository repository, UsuarioRepository usuarioRepository,
            ComentarioRepository comentariosRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.comentarioRepository = comentarioRepository;
    }

    public Publicacion crear(CrearDTO dto) {
        Usuario usuario = this.usuarioRepository
                .findById(dto.getUsuarioId())
                .orElseThrow(
                        () -> new RedSocialApiException("El usuario no existe y no puede crear publicaciones"));
        Publicacion nuevoUsuario = new Publicacion(dto.getTitulo(), dto.getContenido(), usuario);
        return this.repository.save(nuevoUsuario);
    }

    public List<Publicacion> listar() {
        return StreamSupport
                .stream(this.repository.findAll().spliterator(), false)
                .toList();
    }

    public Publicacion asociarComentario(Long idPublicacion, Comentario comentario) {
        Publicacion publicacion = this.repository
                .findById(idPublicacion)
                .orElseThrow(() -> new RedSocialApiException("La publicación no existe"));

        comentario.setPublicacion(publicacion);
        publicacion.getComentarios().add(comentario);

        this.repository.save(publicacion);
        this.comentarioRepository.save(comentario);

        return publicacion;
    }
}
