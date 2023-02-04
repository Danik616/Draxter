package com.draxter.draxter.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.Corte;
import com.draxter.draxter.Repository.ICorteRepository;

@Service
public class CorteService implements ICorteService {

    private ICorteRepository corteRepository;

    public CorteService(ICorteRepository corteRepository) {
        this.corteRepository = corteRepository;
    }

    @Override
    public List<Corte> obtenerCortes(String usuario) {
        return corteRepository.obtenerTodosLosCortesPorIdUsuario(usuario);
    }

    @Override
    public Corte obtenerCortePorId(Long id, String usuario) {
        return corteRepository.obtenerTodosLosCortesPorIdUsuarioYId(id, usuario);
    }

    @Override
    public Corte guardarCorte(Corte corte) {
        return corteRepository.save(corte);
    }

}
