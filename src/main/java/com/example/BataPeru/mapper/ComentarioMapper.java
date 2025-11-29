package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.ComentarioDTO;
import com.example.BataPeru.entity.Comentario;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    public ComentarioDTO toDTO(Comentario entity) {
        if (entity == null) return null;

        ComentarioDTO dto = new ComentarioDTO();
        dto.setId(entity.getId());
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null);
        dto.setProductoId(entity.getProducto() != null ? entity.getProducto().getId() : null);
        dto.setPuntuacion(entity.getPuntuacion());
        dto.setComentario(entity.getComentario());
        dto.setFecha(entity.getFecha());

        return dto;
    }

    public Comentario toEntity(ComentarioDTO dto) {
        if (dto == null) return null;

        Comentario entity = new Comentario();
        entity.setId(dto.getId());
        entity.setPuntuacion(dto.getPuntuacion());
        entity.setComentario(dto.getComentario());
        entity.setFecha(dto.getFecha());

        return entity;
    }
}

