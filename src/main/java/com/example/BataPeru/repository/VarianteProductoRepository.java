package com.example.BataPeru.repository;

import com.example.BataPeru.entity.VarianteProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VarianteProductoRepository extends JpaRepository<VarianteProducto, Long> {
    Optional<VarianteProducto> findByProductoIdAndTallaIdAndColorId(Long productoId, Long tallaId, Long colorId);
}
