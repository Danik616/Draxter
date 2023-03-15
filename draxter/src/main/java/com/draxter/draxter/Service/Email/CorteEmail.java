package com.draxter.draxter.Service.Email;

import com.draxter.draxter.Entity.AbstractEmailContext;
import com.draxter.draxter.Entity.Corte;
import com.draxter.draxter.Entity.Usuarios;

public class CorteEmail extends AbstractEmailContext {

    public Corte corte;

    @Override
    public <T> void init(T context) {
        Usuarios customer = (Usuarios) context;
        put("usuario", customer.getUsuario());
        put("correo", customer.getEmail());
        put("celular", customer.getCelular());
        setTemplateLocation("emails/corte");
        setSubject("Peticion de corte");
        setFrom("draxterprueba@gmail.com");
        setTo("draxterprueba@gmail.com");
    }

    public void setCorte(Corte corte) {
        this.corte = corte;
        put("corte", corte);
        put("diseno", corte.getDiseño());
    }

}
