package com.draxter.draxter.Entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="usuarios", uniqueConstraints= @UniqueConstraint(columnNames="usuario"))
public class Usuarios {

    @Id
    private String usuario;

    @Column(name="Nombre")
    private String nombres;
    @Column(name="Apellidos")
    private String apellidos;

    private String imagen;

    private String email;

    private String contraseña;
    

    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name="usuarios_roles",
        joinColumns= @JoinColumn(
            name="usuario_id",referencedColumnName = "usuario"
        ),
        inverseJoinColumns = @JoinColumn(name="rol_id", referencedColumnName = "id")
    )
    private Collection<Rol> roles;



    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public Collection<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }
    public Usuarios(String usuario, String nombres, String apellidos, String imagen, String email, String contraseña,
            Collection<Rol> roles) {
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.imagen = imagen;
        this.email = email;
        this.contraseña = contraseña;
        this.roles = roles;
    }
    public Usuarios(String nombres, String apellidos, String usuario, String email, String contraseña,
            Collection<Rol> roles) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.contraseña = contraseña;
        this.roles = roles;
    }
    public Usuarios() {
    }

    

    
}
