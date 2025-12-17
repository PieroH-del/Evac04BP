package com.example.BataPeru.service;

import com.example.BataPeru.dto.ComentarioDTO;
import com.example.BataPeru.entity.Comentario;
import com.example.BataPeru.mapper.ComentarioMapper;
import com.example.BataPeru.repository.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    public List<ComentarioDTO> findByProductoId(Long productoId) {
        return comentarioRepository.findByProductoId(productoId).stream()
                .map(comentarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ComentarioDTO> findById(Long id) {
        return comentarioRepository.findById(id).map(comentarioMapper::toDTO);
    }

    @Transactional
    public ComentarioDTO save(ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioMapper.toEntity(comentarioDTO);
        Comentario savedComentario = comentarioRepository.save(comentario);
        return comentarioMapper.toDTO(savedComentario);
    }

    @Transactional
    public void delete(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentario no encontrado con ID: " + id);
        }
        comentarioRepository.deleteById(id);
    }
}
