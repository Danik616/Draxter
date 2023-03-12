package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.draxter.draxter.Entity.Orden;

public interface IOrdenRepository extends JpaRepository<Orden, Long> {

    @Query("FROM orden WHERE id_orden LIKE :id")
    public Orden obtenerUnaOrden(@Param("id") long id);
}
