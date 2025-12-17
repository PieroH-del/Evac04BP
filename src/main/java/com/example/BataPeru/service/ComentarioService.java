package com.example.BataPeru.service;

import com.example.BataPeru.dto.ComentarioDTO;
import com.example.BataPeru.entity.Comentario;
import com.example.BataPeru.mapper.ComentarioMapper;
import com.example.BataPeru.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ComentarioMapper comentarioMapper;

    @Transactional(readOnly = true)
    public List<ComentarioDTO> obtenerTodos() {
        return comentarioRepository.findAll().stream()
                .map(comentarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ComentarioDTO obtenerPorId(Long id) {
        Comentario comentario = comentarioRepository.findById(id).orElse(null);
        return comentario != null ? comentarioMapper.toDTO(comentario) : null;
    }

    @Transactional
    public ComentarioDTO crear(ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioMapper.toEntity(comentarioDTO);
        Comentario guardado = comentarioRepository.save(comentario);
        return comentarioMapper.toDTO(guardado);
    }

    @Transactional
    public ComentarioDTO actualizar(Long id, ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioRepository.findById(id).orElse(null);
        if (comentario != null) {
            comentario.setPuntuacion(comentarioDTO.getPuntuacion());
            comentario.setComentario(comentarioDTO.getComentario());
            Comentario actualizado = comentarioRepository.save(comentario);
            return comentarioMapper.toDTO(actualizado);
        }
        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        comentarioRepository.deleteById(id);
    }
}