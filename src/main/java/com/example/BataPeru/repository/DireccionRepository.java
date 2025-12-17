package com.example.BataPeru.repository;

import com.example.BataPeru.entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    List<Direccion> findByUsuarioId(Long usuarioId);
    Optional<Direccion> findByUsuarioIdAndEsPrincipalTrue(Long usuarioId);
    boolean existsByUsuarioIdAndEsPrincipalTrue(Long usuarioId);
    List<Direccion> findByUsuarioIdAndCiudad(Long usuarioId, String ciudad);
    List<Direccion> findByUsuarioIdAndProvincia(Long usuarioId, String provincia);
    boolean existsByUsuarioId(Long usuarioId);
}
