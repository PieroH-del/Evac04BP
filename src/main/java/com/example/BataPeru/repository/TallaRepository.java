package com.example.BataPeru.repository;

import com.example.BataPeru.entity.Talla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TallaRepository extends JpaRepository<Talla, Long> {

    /**
     * Obtiene una talla por su valor exacto
     */
    Optional<Talla> findByValor(String valor);

    /**
     * Obtiene todas las tallas de una región específica
     */
    List<Talla> findByRegion(String region);

    /**
     * Obtiene todas las tallas de una región, ordenadas por valor
     */
    List<Talla> findByRegionOrderByValor(String region);

    /**
     * Busca tallas que contengan un valor específico
     */
    List<Talla> findByValorContainingIgnoreCase(String valor);

    /**
     * Obtiene todas las tallas, ordenadas por valor
     */
    List<Talla> findAllByOrderByValor();

    boolean existsByValor(String valor);
}
