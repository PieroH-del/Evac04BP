package com.example.BataPeru.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TallaDTO {
    private Long id;
    private String valor;
    private String region;
    private List<Long> variantesIds;
}

