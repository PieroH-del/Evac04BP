package com.example.BataPeru.service;

import com.example.BataPeru.dto.TallaDTO;
import com.example.BataPeru.entity.Talla;
import com.example.BataPeru.mapper.TallaMapper;
import com.example.BataPeru.repository.TallaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TallaService {

    private final TallaRepository tallaRepository;
    private final TallaMapper tallaMapper;

    /**
     * Obtiene todas las tallas ordenadas por valor
     */
    @Transactional(readOnly = true)
    public List<TallaDTO> obtenerTodasLasTallas() {
        return tallaRepository.findAllByOrderByValor()
                .stream()
                .map(tallaMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una talla por su ID
     */
    @Transactional(readOnly = true)
    public Optional<TallaDTO> obtenerTallaPorId(Long id) {
        return tallaRepository.findById(id)
                .map(tallaMapper::toDTO);
    }

    /**
     * Obtiene una talla por su valor exacto
     */
    @Transactional(readOnly = true)
    public Optional<TallaDTO> obtenerTallaPorValor(String valor) {
        return tallaRepository.findByValor(valor)
                .map(tallaMapper::toDTO);
    }

    /**
     * Obtiene todas las tallas de una región específica
     */
    @Transactional(readOnly = true)
    public List<TallaDTO> obtenerTallasPorRegion(String region) {
        return tallaRepository.findByRegionOrderByValor(region)
                .stream()
                .map(tallaMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca tallas por valor parcial (case-insensitive)
     */
    @Transactional(readOnly = true)
    public List<TallaDTO> buscarTallasPorValor(String valor) {
        return tallaRepository.findByValorContainingIgnoreCase(valor)
                .stream()
                .map(tallaMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Crea una nueva talla
     */
    @Transactional
    public TallaDTO crearTalla(TallaDTO tallaDTO) {
        // Validar que el valor no exista ya
        if (tallaRepository.existsByValor(tallaDTO.getValor())) {
            throw new RuntimeException("Ya existe una talla con el valor: " + tallaDTO.getValor());
        }

        Talla talla = tallaMapper.toEntity(tallaDTO);
        Talla saved = tallaRepository.save(talla);

        // Crear manualmente el DTO para evitar LazyInitializationException
        TallaDTO resultado = new TallaDTO();
        resultado.setId(saved.getId());
        resultado.setValor(saved.getValor());
        resultado.setRegion(saved.getRegion());
        resultado.setVariantesIds(List.of());

        return resultado;
    }

    /**
     * Actualiza una talla existente
     */
    @Transactional
    public TallaDTO actualizarTalla(Long id, TallaDTO tallaDTO) {
        Optional<Talla> tallaOpt = tallaRepository.findById(id);

        if (tallaOpt.isEmpty()) {
            throw new RuntimeException("Talla no encontrada con ID: " + id);
        }

        Talla talla = tallaOpt.get();

        // Si el valor cambió, validar que no exista otro con ese valor
        if (!talla.getValor().equals(tallaDTO.getValor()) && 
            tallaRepository.existsByValor(tallaDTO.getValor())) {
            throw new RuntimeException("Ya existe una talla con el valor: " + tallaDTO.getValor());
        }

        // Actualizar campos
        if (tallaDTO.getValor() != null) {
            talla.setValor(tallaDTO.getValor());
        }
        if (tallaDTO.getRegion() != null) {
            talla.setRegion(tallaDTO.getRegion());
        }

        Talla updated = tallaRepository.save(talla);
        return tallaMapper.toDTO(updated);
    }

    /**
     * Elimina una talla
     */
    @Transactional
    public void eliminarTalla(Long id) {
        if (!tallaRepository.existsById(id)) {
            throw new RuntimeException("Talla no encontrada con ID: " + id);
        }
        tallaRepository.deleteById(id);
    }

    /**
     * Obtiene el total de tallas
     */
    public Long contarTallas() {
        return tallaRepository.count();
    }

    /**
     * Obtiene el total de tallas por región
     */
    public Long contarTallasPorRegion(String region) {
        return (long) tallaRepository.findByRegion(region).size();
    }

    /**
     * Verifica si existe una talla con un valor específico
     */
    public boolean existeTalla(String valor) {
        return tallaRepository.existsByValor(valor);
    }

    /**
     * Obtiene todas las regiones disponibles
     */
    public List<String> obtenerRegionesDisponibles() {
        return tallaRepository.findAll()
                .stream()
                .map(Talla::getRegion)
                .distinct()
                .collect(Collectors.toList());
    }
}
