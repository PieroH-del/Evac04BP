package com.example.BataPeru.repository;

import com.example.BataPeru.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriaId(Long categoriaId);
    List<Producto> findByGenero(String genero);
    List<Producto> findByGeneroIgnoreCase(String genero);
}
