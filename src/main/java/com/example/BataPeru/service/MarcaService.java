package com.example.BataPeru.service;

import com.example.BataPeru.dto.MarcaDTO;
import com.example.BataPeru.entity.Marca;
import com.example.BataPeru.mapper.MarcaMapper;
import com.example.BataPeru.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaMapper marcaMapper;

    public List<MarcaDTO> obtenerTodos() {
        return marcaRepository.findAll().stream()
                .map(marcaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MarcaDTO obtenerPorId(Long id) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        return marca != null ? marcaMapper.toDTO(marca) : null;
    }

    @Transactional
    public MarcaDTO crear(MarcaDTO marcaDTO) {
        Marca marca = marcaMapper.toEntity(marcaDTO);
        marca.setFechaCreacion(LocalDateTime.now());
        Marca guardado = marcaRepository.save(marca);

        MarcaDTO resultado = new MarcaDTO();
        resultado.setId(guardado.getId());
        resultado.setNombre(guardado.getNombre());
        resultado.setLogoUrl(guardado.getLogoUrl());
        resultado.setActivo(guardado.getActivo());
        resultado.setFechaCreacion(guardado.getFechaCreacion());
        resultado.setProductosIds(List.of()); // Nueva marca no tiene productos

        return resultado;
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
