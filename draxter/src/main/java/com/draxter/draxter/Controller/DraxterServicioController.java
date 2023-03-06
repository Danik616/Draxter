package com.draxter.draxter.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.draxter.draxter.Entity.PQR;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Service.PQRService;

@Controller
public class DraxterServicioController {

    private Usuarios usuario;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PQRService pqrService;

    @RequestMapping("/servicios")
    public String mostrarServicios() {
        return "MostrarServicios";
    }

    @GetMapping("/servicios/radicarPQR")
    public String radicarPQR() {
        return "radicarPQR";
    }

    @ModelAttribute("pqr")
    public PQR pqr() {
        return new PQR();
    }

    @PostMapping("/servicios/radicarPQR")
    public String radicacionPQR(@ModelAttribute("pqr") PQR pqr, HttpSession session, RedirectAttributes redirAttr) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        pqr.setEstado("No respondido");
        pqr.setUsuario(usuario);
        pqrService.guardarPQR(pqr);

        redirAttr.addFlashAttribute("pqrSuccessMsg",
                messageSource.getMessage("pqr.success.msg", null, LocaleContextHolder.getLocale()));

        return "redirect:/servicios";
    }

}
