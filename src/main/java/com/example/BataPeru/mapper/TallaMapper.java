package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.TallaDTO;
import com.example.BataPeru.entity.Talla;
import com.example.BataPeru.entity.VarianteProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TallaMapper {

    @Mapping(source = "variantes", target = "variantesIds", qualifiedByName = "variantesToIds")
    TallaDTO toDTO(Talla entity);

    Talla toEntity(TallaDTO dto);

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
