package com.draxter.draxter.Service;

import java.util.Objects;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.SecureToken;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Exception.InvalidTokenException;
import com.draxter.draxter.Exception.UnkownIdentifierException;
import com.draxter.draxter.Repository.ISecureTokenRepository;
import com.draxter.draxter.Repository.IUsuarioRepository;
import com.draxter.draxter.Service.Email.EmailService;
import com.draxter.draxter.Service.Email.ForgotPasswordEmailContext;
import com.draxter.draxter.Service.Token.SecureTokenService;

import org.apache.commons.lang3.StringUtils;

@Service
public class CustomerAccountService implements ICustomerAccountService {

    @Autowired
    UsuarioService usuarioService;

    @Resource
    private SecureTokenService secureTokenService;

    @Resource
    ISecureTokenRepository secureTokenRepository;

    @Value("${site.base.url.https}")
    private String baseURL;

    @Resource
    private EmailService emailService;

    @Resource
    private IUsuarioRepository usuarioRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void forgottenPassword(String email) throws UnkownIdentifierException {
        Usuarios usuario = usuarioService.obtenerPorEmail(email);
        sendResetPasswordEmail(usuario);
    }

    protected void sendResetPasswordEmail(Usuarios usuario) {
        SecureToken secureToken = secureTokenService.createSecureToken();
        secureToken.setUser(usuario);
        secureTokenRepository.save(secureToken);
        ForgotPasswordEmailContext emailContext = new ForgotPasswordEmailContext();
        emailContext.init(usuario);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(String password, String token) throws InvalidTokenException, UnkownIdentifierException {
        SecureToken secureToken = secureTokenService.findByToken(token);
        if (Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken())
                || secureToken.isExpired()) {
            throw new InvalidTokenException("Token is not valid");
        }
        Usuarios user = usuarioRepository.findByEmail(secureToken.getUser().getEmail());
        if (Objects.isNull(user)) {
            throw new UnkownIdentifierException("unable to find user for the token");
        }
        secureTokenService.removeToken(secureToken);
        user.setContraseña(passwordEncoder.encode(password));
        usuarioRepository.save(user);

    }

}
