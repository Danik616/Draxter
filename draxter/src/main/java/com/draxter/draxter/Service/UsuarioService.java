package com.draxter.draxter.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.draxter.draxter.Entity.Rol;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Repository.IRolRepository;
import com.draxter.draxter.Repository.IUsuarioRepository;
import com.draxter.draxter.dto.UsuarioRegistroDTO;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private IUsuarioRepository usuarioRepository;
    private IRolRepository rolRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository, IRolRepository rolRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public Usuarios save(UsuarioRegistroDTO registroDTO) {
        Usuarios usuario = new Usuarios(registroDTO.getUsuario(), registroDTO.getNombres(), registroDTO.getApellidos(),
                registroDTO.getImagen(),
                registroDTO.getEmail(), passwordEncoder.encode(registroDTO.getPassword()), registroDTO.getPais(),
                registroDTO.getCelular(), registroDTO.getDireccion(), Arrays.asList(new Rol("ROLE_USER")));
        return usuarioRepository.save(usuario);
    }

    public IUsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usuariosesion", usuario);

        return new User(usuario.getEmail(), usuario.getContraseña(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

    @Override
    public Usuarios guardar(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuarios> obtenerUsuarios(Usuarios usuario) {
        String id = usuario.getUsuario();
        return usuarioRepository.obtenerTodosLosUsuarios(id);
    }

    @Override
    public void eliminarUsuarioPorID(String id) {
        Usuarios usuario = usuarioRepository.obtenerUsuarioPorID(id);
        Collection<Rol> roles = usuario.getRoles();
        for (Rol rol : roles) {
            rolRepository.deleteById(rol.getId());
        }
        usuarioRepository.deleteById(id);

    }

    @Override
    public Usuarios obtenerUsuarioPorID(String id) {
        return usuarioRepository.obtenerUsuarioPorID(id);
    }

    @Override
    public Usuarios obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuarios guardarUsuarioAdmin(Usuarios usuario) {
        String contraseña = usuario.getContraseña();
        usuario.setContraseña(passwordEncoder.encode(contraseña));
        return usuarioRepository.save(usuario);
    }
}
