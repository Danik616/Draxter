package com.draxter.draxter.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.Producto;
import com.draxter.draxter.Repository.IProductoRepository;

@Service
public class ProductoService implements IProductoService{

    private IProductoRepository productoRepository;


    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id){
        return productoRepository.findById(id).get();
    }
    
}
