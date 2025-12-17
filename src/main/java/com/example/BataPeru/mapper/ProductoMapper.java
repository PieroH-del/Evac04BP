package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.ProductoDTO;
import com.example.BataPeru.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(source = "marca.id", target = "marcaId")
    @Mapping(source = "categoria.id", target = "categoriaId")
    ProductoDTO toDTO(Producto entity);

    @Mapping(target = "marca", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    Producto toEntity(ProductoDTO dto);
}
