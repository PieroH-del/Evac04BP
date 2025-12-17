package com.example.BataPeru.service;

import com.example.BataPeru.dto.MarcaDTO;
import com.example.BataPeru.entity.Marca;
import com.example.BataPeru.mapper.MarcaMapper;
import com.example.BataPeru.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public MarcaDTO crear(MarcaDTO marcaDTO) {
        Marca marca = marcaMapper.toEntity(marcaDTO);
        Marca guardado = marcaRepository.save(marca);
        return marcaMapper.toDTO(guardado);
    }

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

    public void eliminar(Long id) {
        marcaRepository.deleteById(id);
    }
}