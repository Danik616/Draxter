package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.draxter.draxter.Entity.Usuarios;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuarios, String> {
    public Usuarios findByEmail(String email);

    @Query("FROM Usuarios WHERE usuario LIKE :id")
    public Usuarios obtenerUsuarioPorID(@Param("id") String id);
}
