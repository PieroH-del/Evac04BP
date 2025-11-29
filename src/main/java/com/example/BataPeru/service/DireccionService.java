package com.example.BataPeru.service;

import com.example.BataPeru.dto.DireccionDTO;
import com.example.BataPeru.entity.Direccion;
import com.example.BataPeru.entity.Usuario;
import com.example.BataPeru.mapper.DireccionMapper;
import com.example.BataPeru.repository.DireccionRepository;
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
    private final DireccionMapper direccionMapper;

    public List<DireccionDTO> obtenerDireccionesPorUsuario(Long usuarioId) {
        return direccionRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(direccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<DireccionDTO> obtenerDireccionPrincipal(Long usuarioId) {
        return direccionRepository.findByUsuarioIdAndEsPrincipalTrue(usuarioId)
                .map(direccionMapper::toDTO);
    }

    public Optional<DireccionDTO> obtenerDireccionPorId(Long id) {
        return direccionRepository.findById(id)
                .map(direccionMapper::toDTO);
    }

    public List<DireccionDTO> obtenerDireccionesPorCiudad(Long usuarioId, String ciudad) {
        return direccionRepository.findByUsuarioIdAndCiudad(usuarioId, ciudad)
                .stream()
                .map(direccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<DireccionDTO> obtenerDireccionesPorProvincia(Long usuarioId, String provincia) {
        return direccionRepository.findByUsuarioIdAndProvincia(usuarioId, provincia)
                .stream()
                .map(direccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public DireccionDTO crearDireccion(DireccionDTO direccionDTO, Usuario usuario) {
        Direccion direccion = direccionMapper.toEntity(direccionDTO);
        direccion.setUsuario(usuario);

        if (direccion.getEsPrincipal() == null) {
            boolean tieneEnPrincipal = direccionRepository.findByUsuarioIdAndEsPrincipalTrue(usuario.getId()).isPresent();
            direccion.setEsPrincipal(!tieneEnPrincipal);
        }

        if (Boolean.TRUE.equals(direccion.getEsPrincipal())) {
            direccionRepository.findByUsuarioIdAndEsPrincipalTrue(usuario.getId())
                    .ifPresent(d -> {
                        d.setEsPrincipal(false);
                        direccionRepository.save(d);
                    });
        }

        Direccion saved = direccionRepository.save(direccion);
        return direccionMapper.toDTO(saved);
    }

    @Transactional
    public DireccionDTO actualizarDireccion(Long id, DireccionDTO direccionDTO) {
        Optional<Direccion> direccionOpt = direccionRepository.findById(id);

        if (direccionOpt.isEmpty()) {
            throw new RuntimeException("Dirección no encontrada con ID: " + id);
        }

        Direccion direccion = direccionOpt.get();

        if (direccionDTO.getDireccionCalle() != null) {
            direccion.setDireccionCalle(direccionDTO.getDireccionCalle());
        }
        if (direccionDTO.getCiudad() != null) {
            direccion.setCiudad(direccionDTO.getCiudad());
        }
        if (direccionDTO.getProvincia() != null) {
            direccion.setProvincia(direccionDTO.getProvincia());
        }
        if (direccionDTO.getCodigoPostal() != null) {
            direccion.setCodigoPostal(direccionDTO.getCodigoPostal());
        }
        if (direccionDTO.getPais() != null) {
            direccion.setPais(direccionDTO.getPais());
        }

        if (Boolean.TRUE.equals(direccionDTO.getEsPrincipal()) && !Boolean.TRUE.equals(direccion.getEsPrincipal())) {
            direccionRepository.findByUsuarioIdAndEsPrincipalTrue(direccion.getUsuario().getId())
                    .ifPresent(d -> {
                        d.setEsPrincipal(false);
                        direccionRepository.save(d);
                    });
            direccion.setEsPrincipal(true);
        } else if (Boolean.FALSE.equals(direccionDTO.getEsPrincipal())) {
            direccion.setEsPrincipal(false);
        }

        Direccion updated = direccionRepository.save(direccion);
        return direccionMapper.toDTO(updated);
    }

    @Transactional
    public void eliminarDireccion(Long id) {
        direccionRepository.findById(id).ifPresentOrElse(
                direccion -> {
                    if (Boolean.TRUE.equals(direccion.getEsPrincipal())) {
                        direccionRepository.findByUsuarioId(direccion.getUsuario().getId())
                                .stream()
                                .filter(d -> !d.getId().equals(id))
                                .findFirst()
                                .ifPresent(d -> {
                                    d.setEsPrincipal(true);
                                    direccionRepository.save(d);
                                });
                    }
                    direccionRepository.deleteById(id);
                },
                () -> {
                    throw new RuntimeException("Dirección no encontrada con ID: " + id);
                }
        );
    }

    public boolean usuarioTieneDirecciones(Long usuarioId) {
        return direccionRepository.existsByUsuarioId(usuarioId);
    }

    public Long contarDireccionesPorUsuario(Long usuarioId) {
        return (long) direccionRepository.findByUsuarioId(usuarioId).size();
    }
}
