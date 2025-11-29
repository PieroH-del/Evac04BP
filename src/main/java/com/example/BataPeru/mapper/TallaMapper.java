package com.example.BataPeru.mapper;
import com.example.BataPeru.dto.TallaDTO;
import com.example.BataPeru.entity.Talla;
import com.example.BataPeru.entity.VarianteProducto;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
@Component
public class TallaMapper {
    public TallaDTO toDTO(Talla entity) {
        if (entity == null) return null;
        TallaDTO dto = new TallaDTO();
        dto.setId(entity.getId());
        dto.setValor(entity.getValor());
        dto.setRegion(entity.getRegion());
        if (entity.getVariantes() != null) {
            dto.setVariantesIds(entity.getVariantes().stream()
                    .map(VarianteProducto::getId)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
    public Talla toEntity(TallaDTO dto) {
        if (dto == null) return null;
        Talla entity = new Talla();
        entity.setId(dto.getId());
        entity.setValor(dto.getValor());
        entity.setRegion(dto.getRegion());
        return entity;
    }
}

