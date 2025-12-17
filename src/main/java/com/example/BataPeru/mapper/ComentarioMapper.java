package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.ComentarioDTO;
import com.example.BataPeru.entity.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "producto.id", target = "productoId")
    ComentarioDTO toDTO(Comentario entity);

    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "producto", ignore = true)
    Comentario toEntity(ComentarioDTO dto);
}
