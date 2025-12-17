package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.ProductoDTO;
import com.example.BataPeru.entity.ImagenProducto;
import com.example.BataPeru.entity.Producto;
import com.example.BataPeru.entity.VarianteProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(source = "marca.id", target = "marcaId")
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "variantes", target = "variantesIds", qualifiedByName = "variantesToIds")
    @Mapping(source = "imagenes", target = "imagenesIds", qualifiedByName = "imagenesToIds")
    ProductoDTO toDTO(Producto entity);

    @Mapping(target = "marca", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    Producto toEntity(ProductoDTO dto);

    @Named("variantesToIds")
    default List<Long> variantesToIds(List<VarianteProducto> variantes) {
        if (variantes == null) {
            return null;
        }
        return variantes.stream()
                .map(VarianteProducto::getId)
                .collect(Collectors.toList());
    }

    @Named("imagenesToIds")
    default List<Long> imagenesToIds(List<ImagenProducto> imagenes) {
        if (imagenes == null) {
            return null;
        }
        return imagenes.stream()
                .map(ImagenProducto::getId)
                .collect(Collectors.toList());
    }
}
