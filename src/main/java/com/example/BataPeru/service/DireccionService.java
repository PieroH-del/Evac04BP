package com.example.BataPeru.service;

import com.example.BataPeru.dto.DireccionDTO;
import com.example.BataPeru.entity.Direccion;
import com.example.BataPeru.entity.Usuario;
import com.example.BataPeru.mapper.DireccionMapper;
import com.example.BataPeru.repository.DireccionRepository;
import com.example.BataPeru.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DireccionService {

    private final DireccionRepository direccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final DireccionMapper direccionMapper;

    @Transactional(readOnly = true)
    public List<DireccionDTO> obtenerDireccionesPorUsuario(Long usuarioId) {
        return direccionRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(direccionMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public DireccionDTO crearDireccion(DireccionDTO direccionDTO) {
        Usuario usuario = usuarioRepository.findById(direccionDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado para crear la dirección"));

        Direccion direccion = direccionMapper.toEntity(direccionDTO);
        direccion.setUsuario(usuario);

        // Lógica para la dirección principal
        if (Boolean.TRUE.equals(direccion.getEsPrincipal())) {
            direccionRepository.findByUsuarioIdAndEsPrincipalTrue(usuario.getId())
                    .ifPresent(d -> {
                        d.setEsPrincipal(false);
                        direccionRepository.save(d);
                    });
        } else {
            // Si no hay otra dirección principal, esta se convierte en la principal
            if (!direccionRepository.existsByUsuarioIdAndEsPrincipalTrue(usuario.getId())) {
                direccion.setEsPrincipal(true);
            }
        }

        Direccion saved = direccionRepository.save(direccion);
        return direccionMapper.toDTO(saved);
    }

    @Transactional
    public DireccionDTO actualizarDireccion(Long id, DireccionDTO direccionDTO) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + id));

        // Actualizar campos
        direccion.setDireccionCalle(direccionDTO.getDireccionCalle());
        direccion.setCiudad(direccionDTO.getCiudad());
        direccion.setProvincia(direccionDTO.getProvincia());
        direccion.setCodigoPostal(direccionDTO.getCodigoPostal());
        direccion.setPais(direccionDTO.getPais());

        if (Boolean.TRUE.equals(direccionDTO.getEsPrincipal()) && !direccion.getEsPrincipal()) {
            direccionRepository.findByUsuarioIdAndEsPrincipalTrue(direccion.getUsuario().getId())
                    .ifPresent(d -> {
                        d.setEsPrincipal(false);
                        direccionRepository.save(d);
                    });
            direccion.setEsPrincipal(true);
        }

        Direccion updated = direccionRepository.save(direccion);
        return direccionMapper.toDTO(updated);
    }

    @Transactional
    public void eliminarDireccion(Long id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));
        
        direccionRepository.delete(direccion);

        // Si la dirección eliminada era la principal, asignar una nueva
        if (Boolean.TRUE.equals(direccion.getEsPrincipal())) {
            direccionRepository.findByUsuarioId(direccion.getUsuario().getId()).stream().findFirst()
                    .ifPresent(d -> {
                        d.setEsPrincipal(true);
                        direccionRepository.save(d);
                    });
        }
    }
    
    // Otros métodos que ya estaban bien
    @Transactional(readOnly = true)
    public Optional<DireccionDTO> obtenerDireccionPrincipal(Long usuarioId) {
        return direccionRepository.findByUsuarioIdAndEsPrincipalTrue(usuarioId)
                .map(direccionMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<DireccionDTO> obtenerDireccionPorId(Long id) {
        return direccionRepository.findById(id)
                .map(direccionMapper::toDTO);
    }
}
