package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.MarcaDTO;
import com.example.BataPeru.entity.Marca;
import com.example.BataPeru.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MarcaMapper {

    @Mapping(source = "productos", target = "productosIds", qualifiedByName = "productosToIds")
    MarcaDTO toDTO(Marca entity);

    Marca toEntity(MarcaDTO dto);

    @Named("productosToIds")
    default List<Long> productosToIds(List<Producto> productos) {
        if (productos == null) {
            return null;
        }
        return productos.stream()
                .map(Producto::getId)
                .collect(Collectors.toList());
    }
}
