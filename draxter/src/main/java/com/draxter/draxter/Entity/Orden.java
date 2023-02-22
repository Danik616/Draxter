package com.draxter.draxter.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.OneToOne;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private long id_orden;

    @OneToOne(mappedBy = "id_usuario")
    private Usuarios usuarios;

    @OneToOne(mappedBy = "id_producto")
    private Producto producto;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "cantidad")
    private long cantidad;

    @Column(name = "valorAPagar")
    private long ValorAPagar;

    @Column(name = "estado")
    private String estado;

    public long getId_orden() {
        return id_orden;
    }

    public void setId_orden(long id_orden) {
        this.id_orden = id_orden;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha() {
        this.fecha = LocalDateTime.now();
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public long getValorAPagar() {
        return ValorAPagar;
    }

    public void setValorAPagar(long valorAPagar) {
        ValorAPagar = valorAPagar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
