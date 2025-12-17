package com.example.BataPeru.service;

import com.example.BataPeru.dto.MarcaDTO;
import com.example.BataPeru.entity.Marca;
import com.example.BataPeru.mapper.MarcaMapper;
import com.example.BataPeru.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    public List<MarcaDTO> findAll() {
        return marcaRepository.findAll().stream()
                .map(marcaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<MarcaDTO> findById(Long id) {
        return marcaRepository.findById(id).map(marcaMapper::toDTO);
    }

    @Transactional
    public MarcaDTO save(MarcaDTO marcaDTO) {
        Marca marca = marcaMapper.toEntity(marcaDTO);
        Marca savedMarca = marcaRepository.save(marca);
        return marcaMapper.toDTO(savedMarca);
    }

    @Transactional
    public void delete(Long id) {
        if (!marcaRepository.existsById(id)) {
            throw new RuntimeException("Marca no encontrada con ID: " + id);
        }
        marcaRepository.deleteById(id);
    }
}
