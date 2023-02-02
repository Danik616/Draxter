package com.draxter.draxter.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.draxter.draxter.Entity.Corte;

import java.util.List;

@Repository
public interface ICorteRepository extends JpaRepository<Corte, Long> {
    @Query("FROM CORTE WHERE id_usuario LIKE :id")
    public List<Corte> obtenerTodosLosCortesPorIdUsuario(@Param("id") String id);

    @Query("FROM CORTE WHERE id_usuario LIKE :id_usuario AND id_corte LIKE :id")
    public Corte obtenerTodosLosCortesPorIdUsuarioYId(@Param("id") Long id, @Param("id_usuario") String id_usuario);
}
