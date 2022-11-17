package com.draxter.draxter.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.draxter.draxter.Entity.PQR;

@Repository
public interface IPQRRepository extends JpaRepository<PQR, Long>{
    
    @Query("FROM PQR WHERE id_usuario LIKE :id")
    public List<PQR> obtenerTodosLosPqrPorIdUsuario(@Param("id") String id);

    @Query("FROM PQR WHERE id_usuario LIKE :id_usuario AND id_pqr LIKE :id")
    public PQR obtenerTodosLosPqrPorIdUsuarioYId(@Param("id") Long id, @Param("id_usuario") String id_usuario);
}
