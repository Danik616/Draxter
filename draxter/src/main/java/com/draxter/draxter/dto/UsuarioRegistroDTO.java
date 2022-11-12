package com.draxter.draxter.dto;

public class UsuarioRegistroDTO {
    private String usuario;
    private String nombres;
    private String apellidos;
    private String imagen;
    private String email;
    private String contraseña;
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
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public UsuarioRegistroDTO(String usuario, String nombres, String apellidos, String imagen, String email,
            String contraseña) {
        this.usuario = usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.imagen = imagen;
        this.email = email;
        this.contraseña = contraseña;
    }
    public UsuarioRegistroDTO(String nombres, String apellidos, String imagen, String email, String contraseña) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.imagen = imagen;
        this.email = email;
        this.contraseña = contraseña;
    }
    public UsuarioRegistroDTO(String usuario) {
        this.usuario = usuario;
    }
    public UsuarioRegistroDTO() {
    }
}
