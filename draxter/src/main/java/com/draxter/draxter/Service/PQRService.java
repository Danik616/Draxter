package com.draxter.draxter.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.PQR;
import com.draxter.draxter.Repository.IPQRRepository;

@Service
public class PQRService implements IPQRService {

    private IPQRRepository pqrRepository;

    public PQRService(IPQRRepository pqrRepository) {
        this.pqrRepository = pqrRepository;
    }

    @Override
    public List<PQR> obtenerPQRs(String usuario) {
        return pqrRepository.obtenerTodosLosPqrPorIdUsuario(usuario);
    }

    @Override
    public List<PQR> obtenerPQRPorId(String id, String usuario) {
        if (id != null) {
            long idbusqueda = Long.parseLong(id);
            return pqrRepository.obtenerTodosLosPqrPorIdUsuarioYId(idbusqueda, usuario);
        }
        return pqrRepository.obtenerTodosLosPqrPorIdUsuario(usuario);

    }

    @Override
    public PQR guardarPQR(PQR pqr) {
        return pqrRepository.save(pqr);
    }

    @Override
    public List<PQR> obtenerTodosLosPQRs() {
        return pqrRepository.findAll();
    }

    @Override
    public List<PQR> obtenerPQRPorIdAdmin(String id) {
        if(id != null){
            long idBusqueda=Long.parseLong(id);
            return pqrRepository.obtenerTodosLosPqrPorId(idBusqueda);
        }
        return pqrRepository.findAll();
    }

    @Override
    public PQR obtenerUnPQRPorID(String id) {
        long idBusqueda=Long.parseLong(id);
        return pqrRepository.obtenerunPQR(idBusqueda);
    }

}
