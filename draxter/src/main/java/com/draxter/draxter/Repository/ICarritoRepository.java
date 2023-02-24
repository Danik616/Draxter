package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.draxter.draxter.Entity.Carrito;

import java.util.List;

public interface ICarritoRepository extends JpaRepository<Carrito, Long> {

    @Query("FROM carrito WHERE id_usuario LIKE :id")
    public List<Carrito> obtenerTodosLoscarritoPorIdUsuario(@Param("id") String id);
}
