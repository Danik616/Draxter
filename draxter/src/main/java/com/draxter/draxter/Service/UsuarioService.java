package com.draxter.draxter.Service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.Rol;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Repository.IUsuarioRepository;
import com.draxter.draxter.dto.UsuarioRegistroDTO;

@Service
public class UsuarioService implements IUsuarioService {

    private IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository){
        super();
        this.usuarioRepository=usuarioRepository;
    }
    @Override
    public Usuarios save(UsuarioRegistroDTO registroDTO) {
        Usuarios usuario=new Usuarios(registroDTO.getNombres(), registroDTO.getApellidos(), 
        registroDTO.getEmail(), registroDTO.getPassword(), Arrays.asList(new Rol("ROLE_USER")));
         return usuarioRepository.save(usuario);
    }

    public IUsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

}
