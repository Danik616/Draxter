package com.draxter.draxter.Service;

import java.util.List;

import com.draxter.draxter.Entity.Producto;

public interface IProductoService {

    public List<Producto> obtenerProductos();

    public Producto obtenerProductoPorId(Long id);

    public Producto guardaProducto(Producto producto);

    public void eliminarProductoPorID(String id);
}
