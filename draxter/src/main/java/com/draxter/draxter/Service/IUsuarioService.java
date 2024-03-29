package com.draxter.draxter.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.dto.UsuarioRegistroDTO;

public interface IUsuarioService extends UserDetailsService {

    public Usuarios save(UsuarioRegistroDTO registroDTO);

    public Usuarios guardar(Usuarios usuario);

    public List<Usuarios> obtenerUsuarios(Usuarios usuario);

    public void eliminarUsuarioPorID(String id);

    public Usuarios obtenerUsuarioPorID(String id);

    public Usuarios obtenerPorEmail(String email);

    public Usuarios guardarUsuarioAdmin(Usuarios usuario);
}
