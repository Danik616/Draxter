package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.draxter.draxter.Entity.Corte;

import java.util.List;

public interface ICorteRepository extends JpaRepository<Corte, Long> {

    @Query("FROM corte WHERE id_usuario LIKE :id_usuario")
    public List<Corte> obtenerTodosLosCortesPorIdUsuario(@Param("id_usuario") String id);

    @Query("FROM corte WHERE id_usuario LIKE :id_usuario AND id_corte LIKE :id_corte")
    public Corte obtenerTodosLosCortesPorIdUsuarioYId(@Param("id_corte") Long id,

            @Param("id_usuario") String id_usuario);

}
