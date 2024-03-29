package com.draxter.draxter.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.Producto;
import com.draxter.draxter.Repository.IProductoRepository;

@Service
public class ProductoService implements IProductoService {

    private IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productoRepository.obtenerTodosLosProductos(false);
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).get();
    }

    @Override
    public List<Producto> obtenerProductoPorGenero(String genero) {
        if (genero != null) {
            return productoRepository.obtenerTodosLosProductoPorGenero(genero, false);
        }
        return productoRepository.obtenerTodosLosProductos(false);

    }

    @Override
    public Producto guardaProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProductoPorID(String id) throws SQLIntegrityConstraintViolationException, Exception {
        long idBusqueda = Long.parseLong(id);
        productoRepository.deleteById(idBusqueda);

    }

}
