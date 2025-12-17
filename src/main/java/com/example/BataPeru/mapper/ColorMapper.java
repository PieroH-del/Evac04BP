package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.ColorDTO;
import com.example.BataPeru.entity.Color;
import com.example.BataPeru.entity.VarianteProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    @Mapping(source = "variantes", target = "variantesIds", qualifiedByName = "variantesToIds")
    ColorDTO toDTO(Color entity);

    Color toEntity(ColorDTO dto);

    @Named("variantesToIds")
    default List<Long> variantesToIds(List<VarianteProducto> variantes) {
        if (variantes == null) {
            return null;
        }
        return variantes.stream()
                .map(VarianteProducto::getId)
                .collect(Collectors.toList());
    }
}
