package com.example.BataPeru.service;

import com.example.BataPeru.dto.DetallePedidoDTO;
import com.example.BataPeru.entity.DetallePedido;
import com.example.BataPeru.mapper.DetallePedidoMapper;
import com.example.BataPeru.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private DetallePedidoMapper detallePedidoMapper;

    public List<DetallePedidoDTO> obtenerTodos() {
        return detallePedidoRepository.findAll().stream()
                .map(detallePedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DetallePedidoDTO obtenerPorId(Long id) {
        DetallePedido detalle = detallePedidoRepository.findById(id).orElse(null);
        return detalle != null ? detallePedidoMapper.toDTO(detalle) : null;
    }

    public DetallePedidoDTO crear(DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detalle = detallePedidoMapper.toEntity(detallePedidoDTO);
        DetallePedido guardado = detallePedidoRepository.save(detalle);
        return detallePedidoMapper.toDTO(guardado);
    }

    public DetallePedidoDTO actualizar(Long id, DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detalle = detallePedidoRepository.findById(id).orElse(null);
        if (detalle != null) {
            detalle.setCantidad(detallePedidoDTO.getCantidad());
            detalle.setPrecioUnitario(detallePedidoDTO.getPrecioUnitario());
            DetallePedido actualizado = detallePedidoRepository.save(detalle);
            return detallePedidoMapper.toDTO(actualizado);
        }
        return null;
    }

    public void eliminar(Long id) {
        detallePedidoRepository.deleteById(id);
    }
}