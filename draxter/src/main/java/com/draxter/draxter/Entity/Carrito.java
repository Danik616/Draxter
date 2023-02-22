package com.draxter.draxter.Entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private long id_carrito;

    @OneToMany(mappedBy = "id_producto")
    private Set<Producto> producto;

    @OneToOne(mappedBy = "id_usuario")
    private Usuarios usuarios;

    public long getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(long id_carrito) {
        this.id_carrito = id_carrito;
    }

    public Set<Producto> getProducto() {
        return producto;
    }

    public void setProducto(Set<Producto> producto) {
        this.producto = producto;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Carrito() {
    }

    public Carrito(long id_carrito, Set<Producto> producto, Usuarios usuarios) {
        this.id_carrito = id_carrito;
        this.producto = producto;
        this.usuarios = usuarios;
    }

}
