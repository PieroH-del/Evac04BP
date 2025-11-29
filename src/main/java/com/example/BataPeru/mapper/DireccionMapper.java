package com.example.BataPeru.mapper;

import com.example.BataPeru.dto.DireccionDTO;
import com.example.BataPeru.entity.Direccion;
import org.springframework.stereotype.Component;

@Component
public class DireccionMapper {

    public DireccionDTO toDTO(Direccion entity) {
        if (entity == null) return null;

        DireccionDTO dto = new DireccionDTO();
        dto.setId(entity.getId());
        dto.setUsuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null);
        dto.setDireccionCalle(entity.getDireccionCalle());
        dto.setCiudad(entity.getCiudad());
        dto.setProvincia(entity.getProvincia());
        dto.setCodigoPostal(entity.getCodigoPostal());
        dto.setPais(entity.getPais());
        dto.setEsPrincipal(entity.getEsPrincipal());

        return dto;
    }

    public Direccion toEntity(DireccionDTO dto) {
        if (dto == null) return null;

        Direccion entity = new Direccion();
        entity.setId(dto.getId());
        entity.setDireccionCalle(dto.getDireccionCalle());
        entity.setCiudad(dto.getCiudad());
        entity.setProvincia(dto.getProvincia());
        entity.setCodigoPostal(dto.getCodigoPostal());
        entity.setPais(dto.getPais());
        entity.setEsPrincipal(dto.getEsPrincipal());

        return entity;
    }
}

