package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.DireccionDTO;
import com.example.BataPeru.entity.Direccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DireccionMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    DireccionDTO toDTO(Direccion entity);

    @Mapping(target = "usuario", ignore = true)
    Direccion toEntity(DireccionDTO dto);
}
