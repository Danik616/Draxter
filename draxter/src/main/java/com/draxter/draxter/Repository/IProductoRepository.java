package com.draxter.draxter.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.draxter.draxter.Entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

    @Query("FROM producto WHERE genero LIKE :genero")
    public List<Producto> obtenerTodosLosProductoPorGenero(@Param("genero") String genero);
}
