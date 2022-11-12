package com.draxter.draxter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.draxter.draxter.Entity.Usuarios;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuarios, String>{
    public Usuario findByEmail(String email);
}
