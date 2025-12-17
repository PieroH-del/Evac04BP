package com.example.BataPeru.service;

import com.example.BataPeru.dto.ImagenProductoDTO;
import com.example.BataPeru.entity.ImagenProducto;
import com.example.BataPeru.mapper.ImagenProductoMapper;
import com.example.BataPeru.repository.ImagenProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImagenProductoService {

    private final ImagenProductoRepository imagenProductoRepository;
    private final ImagenProductoMapper imagenProductoMapper;

    public List<ImagenProductoDTO> findAll() {
        return imagenProductoRepository.findAll().stream()
                .map(imagenProductoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ImagenProductoDTO> findById(Long id) {
        return imagenProductoRepository.findById(id).map(imagenProductoMapper::toDTO);
    }

    @Transactional
    public ImagenProductoDTO save(ImagenProductoDTO imagenProductoDTO) {
        ImagenProducto imagenProducto = imagenProductoMapper.toEntity(imagenProductoDTO);
        ImagenProducto savedImagen = imagenProductoRepository.save(imagenProducto);
        return imagenProductoMapper.toDTO(savedImagen);
    }

    @Transactional
    public void delete(Long id) {
        if (!imagenProductoRepository.existsById(id)) {
            throw new RuntimeException("Imagen no encontrada con ID: " + id);
        }
        imagenProductoRepository.deleteById(id);
    }
}
