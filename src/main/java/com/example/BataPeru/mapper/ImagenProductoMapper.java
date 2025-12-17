package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.ImagenProductoDTO;
import com.example.BataPeru.entity.ImagenProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImagenProductoMapper {

    @Mapping(source = "producto.id", target = "productoId")
    @Mapping(source = "color.id", target = "colorId")
    ImagenProductoDTO toDTO(ImagenProducto entity);

    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "color", ignore = true)
    ImagenProducto toEntity(ImagenProductoDTO dto);
}
