package com.example.BataPeru.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String email;
    private String nombres;
    private String apellidos;
    private String telefono;
    private LocalDateTime fechaRegistro;
    private List<Long> direccionesIds;
    private List<Long> pedidosIds;
}

