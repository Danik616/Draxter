package com.draxter.draxter.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;

@Entity(name = "producto")
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private long precio;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "caracteristicas")
    private String caracteristicas;

    @Column(name = "genero")
    public String genero;

    @Column(name = "tallaje")
    public String tallaje;

    @Column(name = "visibilidad")
    public boolean visibilidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private Set<Carrito> carrito = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTallaje() {
        return tallaje;
    }

    public void setTallaje(String tallaje) {
        this.tallaje = tallaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Producto(long id, String nombre, String descripcion, long precio, String imagen, String caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.caracteristicas = caracteristicas;
    }

    public Producto() {
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(boolean visibilidad) {
        this.visibilidad = visibilidad;
    }

    public Set<Carrito> getCarrito() {
        return carrito;
    }

    public void setCarrito(Set<Carrito> carrito) {
        this.carrito = carrito;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
