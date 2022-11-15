package com.draxter.draxter.dto;

public class UsuarioRegistroDTO {
    private String usuario;
    private String nombres;
    private String apellidos;
    private String imagen;
    private String email;
    private String password;
    private String pais;
    private String celular;
    private String direccion;


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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public UsuarioRegistroDTO(String usuario, String nombres, String apellidos, String imagen, String email,
            String password, String pais, String celular, String direccion) {
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.imagen = imagen;
        this.email = email;
        this.password = password;
        this.pais = pais;
        this.celular = celular;
        this.direccion = direccion;
    }


    public UsuarioRegistroDTO(String nombres, String apellidos, String imagen, String email, String password) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }
    public UsuarioRegistroDTO(String usuario) {
        this.usuario = usuario;
    }
    public UsuarioRegistroDTO() {
    }
}
