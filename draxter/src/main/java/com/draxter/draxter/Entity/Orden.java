package com.draxter.draxter.Entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity(name = "orden")
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(name = "cantidad")
    private long cantidad;

    @Column(name = "talla")
    private String tallaje;

    @Column(name = "valorAPagar")
    private long valorTotal;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    public Orden(long id, Usuarios usuario, Producto producto, long cantidad, String tallaje, long valorTotal,
            LocalDateTime fecha) {
        this.id = id;
        this.usuario = usuario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.tallaje = tallaje;
        this.valorTotal = valorTotal;
        this.fecha = fecha;
    }

    public Orden() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public String getTallaje() {
        return tallaje;
    }

    public void setTallaje(String tallaje) {
        this.tallaje = tallaje;
    }

    public long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}
