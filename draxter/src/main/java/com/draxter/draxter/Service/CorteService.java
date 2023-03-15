package com.draxter.draxter.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.draxter.draxter.Entity.Corte;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Repository.ICorteRepository;
import com.draxter.draxter.Service.Email.CorteEmail;
import com.draxter.draxter.Service.Email.EmailService;
import javax.mail.MessagingException;

@Service
public class CorteService implements ICorteService {

    @Resource
    private EmailService emailService;

    private ICorteRepository corteRepository;

    public CorteService(ICorteRepository corteRepository) {
        this.corteRepository = corteRepository;
    }

    @Override
    public List<Corte> obtenerCortes(String usuario) {
        return corteRepository.obtenerTodosLosCortesPorIdUsuario(usuario);
    }

    @Override
    public Corte obtenerCortePorId(Long id, String usuario) {
        return corteRepository.obtenerTodosLosCortesPorIdUsuarioYId(id, usuario);
    }

    @Override
    public Corte guardarCorte(Corte corte) {
        return corteRepository.save(corte);
    }

    @Override
    public void sendCorteEmailFirst(Usuarios usuario, Corte corte) {
        sendCorteEmail(usuario, corte);
    }

    protected void sendCorteEmail(Usuarios usuario, Corte corte) {
        CorteEmail emailContext = new CorteEmail();
        emailContext.init(usuario);
        emailContext.setCorte(corte);
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
