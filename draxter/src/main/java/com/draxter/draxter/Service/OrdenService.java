package com.draxter.draxter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.Orden;
import com.draxter.draxter.Repository.IOrdenRepository;

@Service
public class OrdenService implements IOrdenService {

    @Autowired
    IOrdenRepository ordenRepository;

    @Override
    public Orden guardarOrden(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> obtenerTodasLasOrdenesPorIdUsuario() {
        return ordenRepository.findAll();
    }

    @Override
    public Orden obtenerOrdenPorID(Orden orden) {
        long id = orden.getId();
        return ordenRepository.obtenerUnaOrden(id);
    }

}
