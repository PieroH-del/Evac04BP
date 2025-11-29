package com.example.BataPeru.repository;

import com.example.BataPeru.entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    /**
     * Obtiene todas las direcciones de un usuario
     */
    List<Direccion> findByUsuarioId(Long usuarioId);

    /**
     * Obtiene la dirección principal de un usuario
     */
    Optional<Direccion> findByUsuarioIdAndEsPrincipalTrue(Long usuarioId);

    /**
     * Obtiene direcciones de un usuario en una ciudad específica
     */
    List<Direccion> findByUsuarioIdAndCiudad(Long usuarioId, String ciudad);

    /**
     * Obtiene direcciones de un usuario en una provincia específica
     */
    List<Direccion> findByUsuarioIdAndProvincia(Long usuarioId, String provincia);

    /**
     * Verifica si existe una dirección para un usuario
     */
    boolean existsByUsuarioId(Long usuarioId);
}
