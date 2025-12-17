package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.UsuarioDTO;
import com.example.BataPeru.entity.Direccion;
import com.example.BataPeru.entity.Pedido;
import com.example.BataPeru.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "direcciones", target = "direccionesIds", qualifiedByName = "direccionesToIds")
    @Mapping(source = "pedidos", target = "pedidosIds", qualifiedByName = "pedidosToIds")
    UsuarioDTO toDTO(Usuario entity);

    @Mapping(target = "direcciones", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    @Mapping(target = "comentarios", ignore = true)
    @Mapping(target = "fechaRegistro", expression = "java(java.time.LocalDateTime.now())")
    Usuario toEntity(UsuarioDTO dto);

    @Named("direccionesToIds")
    default List<Long> direccionesToIds(List<Direccion> direcciones) {
        if (direcciones == null) {
            return null;
        }
        return direcciones.stream()
                .map(Direccion::getId)
                .collect(Collectors.toList());
    }

    @Named("pedidosToIds")
    default List<Long> pedidosToIds(List<Pedido> pedidos) {
        if (pedidos == null) {
            return null;
        }
        return pedidos.stream()
                .map(Pedido::getId)
                .collect(Collectors.toList());
    }
}
