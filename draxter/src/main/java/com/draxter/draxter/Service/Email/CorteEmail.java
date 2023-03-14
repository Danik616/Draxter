package com.draxter.draxter.Service.Email;

import com.draxter.draxter.Entity.AbstractEmailContext;
import com.draxter.draxter.Entity.Usuarios;

public class CorteEmail extends AbstractEmailContext {

    @Override
    public <T> void init(T context) {
        Usuarios customer = (Usuarios) context;
        put("usuario", customer);
        setTemplateLocation("emails/corte");
        setSubject("Peticion de corte");
        setFrom("draxterprueba@gmail.com");
        setTo("draxterprueba@gmail.com");
    }

}
