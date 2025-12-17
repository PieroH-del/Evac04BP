package com.example.BataPeru.service;

import com.example.BataPeru.dto.CategoriaDTO;
import com.example.BataPeru.entity.Categoria;
import com.example.BataPeru.mapper.CategoriaMapper;
import com.example.BataPeru.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaDTO> findById(Long id) {
        return categoriaRepository.findById(id).map(categoriaMapper::toDTO);
    }

    @Transactional
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(savedCategoria);
    }

    @Transactional
    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada con ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
