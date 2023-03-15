package com.draxter.draxter.Service;

import java.util.List;

import com.draxter.draxter.Entity.Corte;
import com.draxter.draxter.Entity.Usuarios;

public interface ICorteService {
    public List<Corte> obtenerCortes(String usuario);

    public Corte obtenerCortePorId(Long id, String usuario);

    public Corte guardarCorte(Corte corte);

    public void sendCorteEmailFirst(Usuarios usuario, Corte corte);

}
