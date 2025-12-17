package com.example.BataPeru.service;

import com.example.BataPeru.dto.DetallePedidoDTO;
import com.example.BataPeru.dto.PedidoDTO;
import com.example.BataPeru.entity.*;
import com.example.BataPeru.mapper.PedidoMapper;
import com.example.BataPeru.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final VarianteProductoRepository varianteProductoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PedidoMapper pedidoMapper;

    @Transactional
    public PedidoDTO crearPedido(PedidoDTO pedidoDTO) {
        Usuario usuario = usuarioRepository.findById(pedidoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        BigDecimal total = BigDecimal.ZERO;
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEstado("PENDIENTE");
        pedido.setFechaPedido(Instant.now()); // Corregido a Instant
        pedido.setDireccionEnvioId(pedidoDTO.getDireccionEnvioId());
        pedido.setMetodoPago(pedidoDTO.getMetodoPago());

        // Guardar el pedido primero para obtener un ID
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        for (DetallePedidoDTO detalleDTO : pedidoDTO.getDetalles()) {
            VarianteProducto variante = varianteProductoRepository.findById(detalleDTO.getVarianteProductoId())
                    .orElseThrow(() -> new RuntimeException("Variante de producto no encontrada"));

            if (variante.getStockCantidad() < detalleDTO.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + variante.getProducto().getNombre());
            }

            // Descontar stock
            variante.setStockCantidad(variante.getStockCantidad() - detalleDTO.getCantidad());
            varianteProductoRepository.save(variante);

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedidoGuardado);
            detalle.setVarianteProducto(variante);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(variante.getProducto().getPrecioRegular()); // Usar el precio del producto
            
            detallePedidoRepository.save(detalle);

            total = total.add(detalle.getPrecioUnitario().multiply(new BigDecimal(detalle.getCantidad())));
        }

        pedidoGuardado.setTotalPagar(total);
        Pedido pedidoFinal = pedidoRepository.save(pedidoGuardado);
        
        return pedidoMapper.toDTO(pedidoFinal);
    }

    public Optional<PedidoDTO> findById(Long id) {
        return pedidoRepository.findById(id).map(pedidoMapper::toDTO);
    }

    public List<PedidoDTO> findByUsuarioId(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId).stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public PedidoDTO actualizarEstado(Long id, String estado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(estado);
        Pedido pedidoActualizado = pedidoRepository.save(pedido);
        return pedidoMapper.toDTO(pedidoActualizado);
    }
}
