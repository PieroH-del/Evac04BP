package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.VarianteProductoDTO;
import com.example.BataPeru.entity.VarianteProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VarianteProductoMapper {

    @Mapping(source = "producto.id", target = "productoId")
    @Mapping(source = "talla.id", target = "tallaId")
    @Mapping(source = "color.id", target = "colorId")
    VarianteProductoDTO toDTO(VarianteProducto entity);

    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "talla", ignore = true)
    @Mapping(target = "color", ignore = true)
    VarianteProducto toEntity(VarianteProductoDTO dto);
}
