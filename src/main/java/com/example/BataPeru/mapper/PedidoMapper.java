package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.PedidoDTO;
import com.example.BataPeru.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DetallePedidoMapper.class})
public interface PedidoMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "detalles", target = "detalles")
    PedidoDTO toDTO(Pedido entity);

    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "detalles", ignore = true)
    Pedido toEntity(PedidoDTO dto);
}
