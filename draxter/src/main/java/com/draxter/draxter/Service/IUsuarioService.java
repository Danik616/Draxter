package com.draxter.draxter.Service;

import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.dto.UsuarioRegistroDTO;

public interface IUsuarioService {
    
    public Usuarios save(UsuarioRegistroDTO registroDTO);
}
