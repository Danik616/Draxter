package com.draxter.draxter.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.draxter.draxter.Entity.PQR;

public interface IPQRRepository extends JpaRepository<PQR, Long> {

    @Query("FROM PQR WHERE id_usuario LIKE :id")
    public List<PQR> obtenerTodosLosPqrPorIdUsuario(@Param("id") String id);

    @Query("FROM PQR WHERE id_usuario LIKE :id_usuario AND id_pqr LIKE :id")
    public List<PQR> obtenerTodosLosPqrPorIdUsuarioYId(@Param("id") long id, @Param("id_usuario") String id_usuario);

    @Query("FROM PQR WHERE id_pqr LIKE :id")
    public List<PQR> obtenerTodosLosPqrPorId(@Param("id") long id);

    @Query("FROM PQR WHERE id_pqr LIKE :id")
    public PQR obtenerunPQR(@Param("id") long id);
}
