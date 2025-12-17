package com.example.BataPeru.service;

import com.example.BataPeru.dto.VarianteProductoDTO;
import com.example.BataPeru.entity.Color;
import com.example.BataPeru.entity.Producto;
import com.example.BataPeru.entity.Talla;
import com.example.BataPeru.entity.VarianteProducto;
import com.example.BataPeru.mapper.VarianteProductoMapper;
import com.example.BataPeru.repository.ColorRepository;
import com.example.BataPeru.repository.ProductoRepository;
import com.example.BataPeru.repository.TallaRepository;
import com.example.BataPeru.repository.VarianteProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VarianteProductoService {

    private final VarianteProductoRepository varianteProductoRepository;
    private final VarianteProductoMapper varianteProductoMapper;
    private final ProductoRepository productoRepository;
    private final TallaRepository tallaRepository;
    private final ColorRepository colorRepository;

    @Transactional(readOnly = true)
    public List<VarianteProductoDTO> findAll() {
        return varianteProductoRepository.findAll().stream()
                .map(varianteProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<VarianteProductoDTO> findById(Long id) {
        return varianteProductoRepository.findById(id)
                .map(varianteProductoMapper::toDTO);
    }

    @Transactional
    public VarianteProductoDTO add(VarianteProductoDTO varianteProductoDTO) {
        VarianteProducto varianteProducto = varianteProductoMapper.toEntity(varianteProductoDTO);

        Producto producto = productoRepository.findById(varianteProductoDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + varianteProductoDTO.getProductoId()));
        varianteProducto.setProducto(producto);

        Talla talla = tallaRepository.findById(varianteProductoDTO.getTallaId())
                .orElseThrow(() -> new RuntimeException("Talla no encontrada con ID: " + varianteProductoDTO.getTallaId()));
        varianteProducto.setTalla(talla);

        Color color = colorRepository.findById(varianteProductoDTO.getColorId())
                .orElseThrow(() -> new RuntimeException("Color no encontrado con ID: " + varianteProductoDTO.getColorId()));
        varianteProducto.setColor(color);

        VarianteProducto savedVariante = varianteProductoRepository.save(varianteProducto);
        return varianteProductoMapper.toDTO(savedVariante);
    }

    @Transactional
    public VarianteProductoDTO update(Long id, VarianteProductoDTO varianteProductoDTO) {
        VarianteProducto varianteProducto = varianteProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variante de producto no encontrada con ID: " + id));

        varianteProducto.setStockCantidad(varianteProductoDTO.getStockCantidad());

        Producto producto = productoRepository.findById(varianteProductoDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + varianteProductoDTO.getProductoId()));
        varianteProducto.setProducto(producto);

        Talla talla = tallaRepository.findById(varianteProductoDTO.getTallaId())
                .orElseThrow(() -> new RuntimeException("Talla no encontrada con ID: " + varianteProductoDTO.getTallaId()));
        varianteProducto.setTalla(talla);

        Color color = colorRepository.findById(varianteProductoDTO.getColorId())
                .orElseThrow(() -> new RuntimeException("Color no encontrado con ID: " + varianteProductoDTO.getColorId()));
        varianteProducto.setColor(color);

        VarianteProducto updatedVariante = varianteProductoRepository.save(varianteProducto);
        return varianteProductoMapper.toDTO(updatedVariante);
    }

    @Transactional
    public void delete(Long id) {
        if (!varianteProductoRepository.existsById(id)) {
            throw new RuntimeException("Variante de producto no encontrada con ID: " + id);
        }
        varianteProductoRepository.deleteById(id);
    }
}
