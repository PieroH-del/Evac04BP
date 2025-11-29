package com.example.BataPeru.mapper;
import com.example.BataPeru.dto.UsuarioDTO;
import com.example.BataPeru.entity.Direccion;
import com.example.BataPeru.entity.Pedido;
import com.example.BataPeru.entity.Usuario;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
@Component
public class UsuarioMapper {
    public UsuarioDTO toDTO(Usuario entity) {
        if (entity == null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setNombres(entity.getNombres());
        dto.setApellidos(entity.getApellidos());
        dto.setTelefono(entity.getTelefono());
        dto.setFechaRegistro(entity.getFechaRegistro());
        if (entity.getDirecciones() != null) {
            dto.setDireccionesIds(entity.getDirecciones().stream()
                    .map(Direccion::getId)
                    .collect(Collectors.toList()));
        }
        if (entity.getPedidos() != null) {
            dto.setPedidosIds(entity.getPedidos().stream()
                    .map(Pedido::getId)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        Usuario entity = new Usuario();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setNombres(dto.getNombres());
        entity.setApellidos(dto.getApellidos());
        entity.setTelefono(dto.getTelefono());
        entity.setFechaRegistro(dto.getFechaRegistro());
        return entity;
    }
}

