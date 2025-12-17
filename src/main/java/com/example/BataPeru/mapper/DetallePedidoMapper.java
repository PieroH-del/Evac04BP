package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.DetallePedidoDTO;
import com.example.BataPeru.entity.DetallePedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetallePedidoMapper {

    @Mapping(source = "pedido.id", target = "pedidoId")
    @Mapping(source = "varianteProducto.id", target = "varianteProductoId")
    DetallePedidoDTO toDTO(DetallePedido entity);

    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "varianteProducto", ignore = true)
    DetallePedido toEntity(DetallePedidoDTO dto);
}
