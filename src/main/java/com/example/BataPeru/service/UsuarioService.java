package com.example.BataPeru.service;

import com.example.BataPeru.dto.UsuarioDTO;
import com.example.BataPeru.entity.Usuario;
import com.example.BataPeru.mapper.UsuarioMapper;
import com.example.BataPeru.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está en uso.");
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(nuevoUsuario);
    }

    public Optional<UsuarioDTO> login(String email, String password) {
        // La validación de contraseña se hace directamente en la consulta
        return usuarioRepository.findByEmailAndPassword(email, password)
                .map(usuarioMapper::toDTO);
    }

    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toDTO);
    }

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuario.setNombres(usuarioDTO.getNombres());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setTelefono(usuarioDTO.getTelefono());
        // No se debería poder cambiar el email o la contraseña desde un update genérico
        
        Usuario updatedUsuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(updatedUsuario);
    }

    @Transactional
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
