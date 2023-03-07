package com.draxter.draxter.Controller;

import javax.servlet.http.HttpSession;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.data.repository.query.Param;

import com.draxter.draxter.Entity.PQR;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Entity.Corte;
import com.draxter.draxter.Service.PQRService;
import com.draxter.draxter.Service.CorteService;

@Controller
public class DraxterServicioController {

    private Usuarios usuario;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PQRService pqrService;

    @Autowired
    private CorteService corteService;

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

    @GetMapping("/servicios/monitorearPQR")
    public String monitorearPQRmodal(Model model, HttpSession session) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        List<PQR> listaPqr = pqrService.obtenerPQRs(usuario.getUsuario());
        List<PQR> listaPqrAdmin = pqrService.obtenerTodosLosPQRs();
        model.addAttribute("pqrs", listaPqr);
        model.addAttribute("pqrsAdmin", listaPqrAdmin);
        return "monitorearPQRmodal";
    }

    @GetMapping("/servicios/monitorearPQR/vistaCompleta")
    public String monitorearPQR(Model model, HttpSession session, @Param("id") String id) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        List<PQR> listaPqr = pqrService.obtenerPQRPorId(id, usuario.getUsuario());
        List<PQR> listaPqrAdmin = pqrService.obtenerPQRPorIdAdmin(id);
        model.addAttribute("pqrs", listaPqr);
        model.addAttribute("pqrsAdmin", listaPqrAdmin);
        model.addAttribute("id", id);
        return "monitorearPQR";
    }

    @GetMapping("/servicios/pedirCorte")
    public String pedirCorte() {
        return "PedirCorte";
    }

    @ModelAttribute("corte")
    public Corte corte() {
        return new Corte();
    }

    @PostMapping("/servicios/pedirCorte")
    public String peticionCorte(@ModelAttribute("corte") Corte corte, HttpSession session,
            RedirectAttributes redirAttr) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        corte.setUsuario(usuario);
        corteService.guardarCorte(corte);

        redirAttr.addFlashAttribute("corteSuccessMsg",
                messageSource.getMessage("corte.success.msg", null, LocaleContextHolder.getLocale()));
        return "redirect:/servicios";
    }

}
