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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="usuarios", uniqueConstraints= @UniqueConstraint(columnNames="email"))
public class Usuarios {

    @Id
    private String usuario;

    
    @Column(name="nombres")
    @NotEmpty
    private String nombres;

    @NotEmpty
    @Column(name="apellidos")
    private String apellidos;

    @Column(name="imagen")
    private String imagen;

    @NotEmpty
    @Email
    @Column(name="email")
    private String email;

    @NotEmpty
    @Column(name="password")
    private String contraseña;

    @Column(name="pais")
    private String pais;
    @Column(name="celular")
    private String celular;
    @Column(name="direccion")
    private String direccion;
    

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
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Collection<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }


    


    public Usuarios(String usuario, String nombres, String apellidos, String imagen, String email, String contraseña,
            String pais, String celular, String direccion, Collection<Rol> roles) {
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.imagen = imagen;
        this.email = email;
        this.contraseña = contraseña;
        this.pais = pais;
        this.celular = celular;
        this.direccion = direccion;
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
