package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.PedidoDTO;
import com.example.BataPeru.entity.DetallePedido;
import com.example.BataPeru.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "detalles", target = "detallesIds", qualifiedByName = "detallesToIds")
    @Mapping(target = "detalles", ignore = true) // Ignorar este campo en la conversión a DTO
    PedidoDTO toDTO(Pedido entity);

    @Mapping(target = "usuario", ignore = true)
    Pedido toEntity(PedidoDTO dto);

    @Named("detallesToIds")
    default List<Long> detallesToIds(List<DetallePedido> detalles) {
        if (detalles == null) {
            return null;
        }
        return detalles.stream()
                .map(DetallePedido::getId)
                .collect(Collectors.toList());
    }
}
