package com.draxter.draxter.Service.Email;

import javax.mail.MessagingException;

import com.draxter.draxter.Entity.AbstractEmailContext;

public interface IEmailService {
    void sendMail(final AbstractEmailContext email) throws MessagingException;
}
