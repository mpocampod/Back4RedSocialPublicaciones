package com.makaia.back4.JpaMySql.consumer;

import com.makaia.back4.JpaMySql.dtos.CrearDTO;
import com.makaia.back4.JpaMySql.entities.Comentario;
import com.makaia.back4.JpaMySql.dtos.ComentarioDTO;
import com.makaia.back4.JpaMySql.services.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Consumer {

    @Autowired
    Service publicacionService;
    private final RestTemplate restTemplate;
    private static final String COMENTARIOS_BASE_URL = "http://localhost:8083/api/v1/comentarios";

    public Consumer(Service publicacionService, RestTemplate restTemplate) {
        this.publicacionService = publicacionService;
        this.restTemplate = restTemplate;
    }

    @RabbitListener(queues = { "user_created" }) // user_created: Nombre de la cola que se quiere escuchar
    public void receive(@Payload Long id) {
        System.out.println("Crear una publicación para el usuario " + id);
        CrearDTO defaultP = new CrearDTO("Mi Primera Publicacion", "Contenido", id);

        this.publicacionService.crear(defaultP);
    }

    @RabbitListener(queues = { "comment_created" })
    public void recibirComentario(@Payload ComentarioDTO comentarioDTO) {
        System.out.println("Crear un comentario para la publicación " + comentarioDTO.getIdPublicacion());

        // Crear un comentario
        Comentario comentario = new Comentario(comentarioDTO.getContenido());

        // Asociar el comentario a la publicación
        this.publicacionService.asociarComentario(comentarioDTO.getIdPublicacion(), comentario);
    }

}
