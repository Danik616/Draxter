package com.draxter.draxter.Entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuarios {

    @Id
    private String usuario;

    @Column(name = "nombres")
    @NotEmpty
    private String nombres;

    @NotEmpty
    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "imagen")
    private String imagen;

    @NotEmpty
    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "password")
    private String contraseña;

    @Column(name = "pais")
    private String pais;
    @Column(name = "celular")
    private String celular;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "bck")
    private String bck;

    public String getBck() {
        return bck;
    }

    public void setBck(String bck) {
        this.bck = bck;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "usuario"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
    private Collection<Rol> roles;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<PQR> pqr = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Corte> corte = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Producto> producto = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<FAQ> faq = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<SecureToken> tokens;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carrito")
    private Carrito carrito;

    public Set<Corte> getCorte() {
        return corte;
    }

    public void setCorte(Set<Corte> corte) {
        this.corte = corte;
    }

    public Set<Producto> getProducto() {
        return producto;
    }

    public void setProducto(Set<Producto> producto) {
        this.producto = producto;
    }

    public Set<FAQ> getFaq() {
        return faq;
    }

    public void setFaq(Set<FAQ> faq) {
        this.faq = faq;
    }

    public Set<SecureToken> getTokens() {
        return tokens;
    }

    public void setTokens(Set<SecureToken> tokens) {
        this.tokens = tokens;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public String getUsuario() {
        return usuario;
    }

    public Set<PQR> getPqr() {
        return pqr;
    }

    public void setPqr(Set<PQR> pqr) {
        this.pqr = pqr;
        for (PQR pqrs : pqr) {
            pqrs.setUsuario(this);
        }
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
