package com.draxter.draxter.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pqr")
public class PQR {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pqr")
    private long id_pqr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_usuario")
    private Usuarios usuario;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="estado")
    private String estado;

    @Column(name="respuesta")
    private String respuesta;

    @Column(name="respondidoPor")
    private String respondidoPor;

    public long getId_pqr() {
        return id_pqr;
    }

    public void setId_pqr(long id_pqr) {
        this.id_pqr = id_pqr;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespondidoPor() {
        return respondidoPor;
    }

    public void setRespondidoPor(String respondidoPor) {
        this.respondidoPor = respondidoPor;
    }

    public PQR(long id_pqr, Usuarios usuario, String descripcion, String estado, String respuesta,
            String respondidoPor) {
        this.id_pqr = id_pqr;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.estado = estado;
        this.respuesta = respuesta;
        this.respondidoPor = respondidoPor;
    }

    public PQR(Usuarios usuario, String descripcion, String estado, String respuesta, String respondidoPor) {
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.estado = estado;
        this.respuesta = respuesta;
        this.respondidoPor = respondidoPor;
    }

    public PQR(Usuarios usuario, String descripcion, String estado) {
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public PQR(String descripcion) {
        this.descripcion = descripcion;
    }

    public PQR() {
    }
    
}
