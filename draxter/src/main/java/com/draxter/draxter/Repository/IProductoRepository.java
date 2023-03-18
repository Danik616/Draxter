package com.draxter.draxter.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.draxter.draxter.Entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

    @Query("FROM producto WHERE genero LIKE :genero AND visibilidad NOT LIKE :visibilidad")
    public List<Producto> obtenerTodosLosProductoPorGenero(@Param("genero") String genero,
            @Param("visibilidad") boolean visibilidad);

    @Query("FROM producto WHERE visibilidad NOT LIKE :visibilidad")
    public List<Producto> obtenerTodosLosProductos(
            @Param("visibilidad") boolean visibilidad);
}
