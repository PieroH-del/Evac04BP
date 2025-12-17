package com.example.BataPeru.service;

import com.example.BataPeru.dto.ColorDTO;
import com.example.BataPeru.entity.Color;
import com.example.BataPeru.mapper.ColorMapper;
import com.example.BataPeru.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    public List<ColorDTO> findAll() {
        return colorRepository.findAll().stream()
                .map(colorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ColorDTO> findById(Long id) {
        return colorRepository.findById(id).map(colorMapper::toDTO);
    }

    @Transactional
    public ColorDTO save(ColorDTO colorDTO) {
        Color color = colorMapper.toEntity(colorDTO);
        Color savedColor = colorRepository.save(color);
        return colorMapper.toDTO(savedColor);
    }

    @Transactional
    public void delete(Long id) {
        if (!colorRepository.existsById(id)) {
            throw new RuntimeException("Color no encontrado con ID: " + id);
        }
        colorRepository.deleteById(id);
    }
}
