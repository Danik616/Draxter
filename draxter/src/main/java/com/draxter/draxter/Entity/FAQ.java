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
@Table(name = "faq")
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_faq")
    private long id_faq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @Column(name = "pregunta")
    private String pregunta;

    @Column(name = "respuesta")
    private String respuesta;

    public long getId_faq() {
        return id_faq;
    }

    public void setId_faq(long id_faq) {
        this.id_faq = id_faq;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public FAQ() {
    }

    public FAQ(long id_faq, Usuarios usuario, String pregunta, String respuesta) {
        this.id_faq = id_faq;
        this.usuario = usuario;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public FAQ(Usuarios usuario, String pregunta, String respuesta) {
        this.usuario = usuario;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

}
