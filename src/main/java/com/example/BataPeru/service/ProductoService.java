package com.example.BataPeru.service;

import com.example.BataPeru.dto.ProductoDTO;
import com.example.BataPeru.entity.Categoria;
import com.example.BataPeru.entity.Marca;
import com.example.BataPeru.entity.Producto;
import com.example.BataPeru.mapper.ProductoMapper;
import com.example.BataPeru.repository.CategoriaRepository;
import com.example.BataPeru.repository.MarcaRepository;
import com.example.BataPeru.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoMapper mapper;
    private final ProductoRepository repository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<ProductoDTO> findAll(){
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ProductoDTO> findById(Long id){
        return repository.findById(id).map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> findByCategoria(Long categoriaId) {
        return repository.findByCategoriaId(categoriaId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> findByGenero(String genero) {
        return repository.findByGeneroIgnoreCase(genero).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductoDTO add(ProductoDTO productoDTO){
        Producto productoEntity = mapper.toEntity(productoDTO);

        if (productoDTO.getMarcaId() != null) {
            Marca marca = marcaRepository.findById(productoDTO.getMarcaId())
                    .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + productoDTO.getMarcaId()));
            productoEntity.setMarca(marca);
        }

        if (productoDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + productoDTO.getCategoriaId()));
            productoEntity.setCategoria(categoria);
        }

        Producto productoSave = repository.save(productoEntity);
        // El mapper se usa dentro de la transacción, por lo que las colecciones lazy se cargan correctamente
        return mapper.toDTO(productoSave);
    }

    @Transactional
    public ProductoDTO update(Long id, ProductoDTO productoDTO){
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecioRegular(productoDTO.getPrecioRegular());
        producto.setGenero(productoDTO.getGenero());
        producto.setMaterial(productoDTO.getMaterial());
        producto.setActivo(productoDTO.getActivo());

        if (productoDTO.getMarcaId() != null) {
            Marca marca = marcaRepository.findById(productoDTO.getMarcaId())
                    .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + productoDTO.getMarcaId()));
            producto.setMarca(marca);
        }

        if (productoDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + productoDTO.getCategoriaId()));
            producto.setCategoria(categoria);
        }

        Producto updatedProducto = repository.save(producto);
        // El mapper se usa dentro de la transacción, por lo que las colecciones lazy se cargan correctamente
        return mapper.toDTO(updatedProducto);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }
}
