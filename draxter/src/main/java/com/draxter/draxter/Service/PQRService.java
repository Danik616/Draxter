package com.draxter.draxter.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.PQR;
import com.draxter.draxter.Repository.IPQRRepository;

@Service
public class PQRService implements IPQRService{

    private IPQRRepository pqrRepository;
    
    public PQRService(IPQRRepository pqrRepository) {
        this.pqrRepository = pqrRepository;
    }

    @Override
    public List<PQR> obtenerPQRs(String usuario) {
        return pqrRepository.obtenerTodosLosPqrPorIdUsuario(usuario);
    }

    @Override
    public PQR obtenerPQRPorId(Long id, String usuario) {
        return pqrRepository.obtenerTodosLosPqrPorIdUsuarioYId(id, usuario);
    }

    @Override
    public PQR guardarPQR(PQR pqr) {
        return pqrRepository.save(pqr);
    }
    
}
