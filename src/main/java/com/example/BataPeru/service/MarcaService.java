package com.example.BataPeru.service;

import com.example.BataPeru.dto.MarcaDTO;
import com.example.BataPeru.entity.Marca;
import com.example.BataPeru.mapper.MarcaMapper;
import com.example.BataPeru.repository.MarcaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    private static final Logger logger = LoggerFactory.getLogger(MarcaService.class);

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaMapper marcaMapper;

    @Transactional(readOnly = true)
    public List<MarcaDTO> obtenerTodos() {
        return marcaRepository.findAll().stream()
                .map(marcaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MarcaDTO obtenerPorId(Long id) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        return marca != null ? marcaMapper.toDTO(marca) : null;
    }

    @Transactional
    public MarcaDTO crear(MarcaDTO marcaDTO) {
        try {
            logger.info("Intentando crear marca: {}", marcaDTO.getNombre());
            Marca marca = marcaMapper.toEntity(marcaDTO);
            logger.debug("Marca mapeada a entidad: {}", marca);

            marca.setFechaCreacion(LocalDateTime.now());
            // Si activo es null, establecer true por defecto
            if (marca.getActivo() == null) {
                marca.setActivo(true);
            }

            logger.debug("Guardando marca en la base de datos...");
            Marca guardado = marcaRepository.save(marca);
            logger.info("Marca creada exitosamente con ID: {}", guardado.getId());

            // Una marca recién creada no tiene productos asociados
            MarcaDTO resultado = new MarcaDTO();
            resultado.setId(guardado.getId());
            resultado.setNombre(guardado.getNombre());
            resultado.setLogoUrl(guardado.getLogoUrl());
            resultado.setActivo(guardado.getActivo());
            resultado.setFechaCreacion(guardado.getFechaCreacion());
            resultado.setProductosIds(List.of()); // Nueva marca sin productos

            return resultado;
        } catch (Exception e) {
            logger.error("Error al crear marca: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public MarcaDTO actualizar(Long id, MarcaDTO marcaDTO) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        if (marca != null) {
            marca.setNombre(marcaDTO.getNombre());
            marca.setLogoUrl(marcaDTO.getLogoUrl());
            marca.setActivo(marcaDTO.getActivo());
            Marca actualizado = marcaRepository.save(marca);
            return marcaMapper.toDTO(actualizado);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        marcaRepository.deleteById(id);
    }
}
