package com.draxter.draxter.dto;

public class UsuarioRegistroDTO {
    private String usuario;
    private String nombres;
    private String apellidos;
    private String imagen="imagen";
    private String email;
    private String password;
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
    public UsuarioRegistroDTO(String usuario, String nombres, String apellidos, String imagen, String email,
            String password) {
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
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
