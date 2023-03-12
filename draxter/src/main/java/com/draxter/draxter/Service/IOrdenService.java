package com.draxter.draxter.Service;

import java.util.List;

import com.draxter.draxter.Entity.Orden;

public interface IOrdenService {

    public Orden guardarOrden(Orden orden);

    public List<Orden> obtenerTodasLasOrdenesPorIdUsuario();

    public Orden obtenerOrdenPorID(Orden orden);
}
