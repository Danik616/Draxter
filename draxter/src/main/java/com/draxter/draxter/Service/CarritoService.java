package com.draxter.draxter.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.Carrito;
import com.draxter.draxter.Entity.Producto;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Repository.ICarritoRepository;

@Service
public class CarritoService implements ICarritoService {
    private final ICarritoRepository carritoRepository;

    @Autowired
    public CarritoService(ICarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    @Override
    public void addToCarrito(Usuarios usuario, Producto producto) {
        Carrito carrito = new Carrito(usuario, producto);
        carritoRepository.save(carrito);
    }

    @Override
    public List<Producto> obtenerProductosPorUsuario(Usuarios usuario) {
        String idBusqueda = usuario.getUsuario();
        List<Carrito> carritos = carritoRepository.obtenerTodosLoscarritoPorIdUsuario(idBusqueda);
        List<Producto> productos = new ArrayList<>();
        for (Carrito carrito : carritos) {
            productos.add(carrito.getProducto());
        }
        return productos;
    }

}
