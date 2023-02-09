package com.draxter.draxter.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.dto.UsuarioRegistroDTO;

public interface IUsuarioService extends UserDetailsService {

    public Usuarios save(UsuarioRegistroDTO registroDTO);

    public Usuarios guardar(Usuarios usuario);
}
