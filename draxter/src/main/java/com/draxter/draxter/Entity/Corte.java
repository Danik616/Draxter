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
@Table(name = "corte")
public class Corte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_corte")
    private long id_corte;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @Column(name = "tipoDeTela")
    private String tipoDeTela;

    @Column(name = "metros")
    private long metros;

    @Column(name = "nombreTela")
    private String nombre;

    @Column(name = "anchuraRollo")
    // anchura dada en cm
    private long anchura;

    @Column(name = "diseño")
    // condcional, agregar en un futuro
    private String diseño;

    public long getId_corte() {
        return id_corte;
    }

    public void setId_corte(long id_corte) {
        this.id_corte = id_corte;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getTipoDeTela() {
        return tipoDeTela;
    }

    public void setTipoDeTela(String tipoDeTela) {
        this.tipoDeTela = tipoDeTela;
    }

    public long getMetros() {
        return metros;
    }

    public void setMetros(long metros) {
        this.metros = metros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getAnchura() {
        return anchura;
    }

    public void setAnchura(long anchura) {
        this.anchura = anchura;
    }

    public String getDiseño() {
        return diseño;
    }

    public void setDiseño(String diseño) {
        this.diseño = diseño;
    }

    public Corte(long id_corte, Usuarios usuario, String tipoDeTela, long metros, String nombre, long anchura,
            String diseño) {
        this.id_corte = id_corte;
        this.usuario = usuario;
        this.tipoDeTela = tipoDeTela;
        this.metros = metros;
        this.nombre = nombre;
        this.anchura = anchura;
        this.diseño = diseño;
    }

    public Corte() {
    }

    public Corte(Usuarios usuario, String tipoDeTela, long metros, String nombre, long anchura, String diseño) {
        this.usuario = usuario;
        this.tipoDeTela = tipoDeTela;
        this.metros = metros;
        this.nombre = nombre;
        this.anchura = anchura;
        this.diseño = diseño;
    }

}
