package com.draxter.draxter.Service.Email;

import com.draxter.draxter.Entity.AbstractEmailContext;
import com.draxter.draxter.Entity.Usuarios;
import org.springframework.web.util.UriComponentsBuilder;

public class ForgotPasswordEmailContext extends AbstractEmailContext {

    private String token;

    @Override
    public <T> void init(T context) {
        Usuarios customer = (Usuarios) context;
        put("firstName", customer.getNombres());
        setTemplateLocation("emails/forgot-password");
        setSubject("Forgotten Password");
        setFrom("draxterprueba@gmail.com");
        setTo(customer.getEmail());
    }


    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token) {
        final String url = UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/password/change").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
