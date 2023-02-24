package com.draxter.draxter.Service;

import java.util.List;

import com.draxter.draxter.Entity.Producto;
import com.draxter.draxter.Entity.Usuarios;

public interface ICarritoService {

    public List<Producto> obtenerProductosPorUsuario(Usuarios usuario);

    public void addToCarrito(Usuarios usuario, Producto producto);
}
