package com.draxter.draxter.Controller;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.draxter.draxter.Entity.PQR;
import com.draxter.draxter.Entity.Usuarios;

import com.draxter.draxter.Entity.Corte;
import com.draxter.draxter.Entity.Rol;
import com.draxter.draxter.Entity.FAQ;
import com.draxter.draxter.Service.PQRService;
import com.draxter.draxter.Service.CorteService;
import com.draxter.draxter.Service.FAQService;
import com.draxter.draxter.Service.UsuarioService;

@Controller
public class DraxterServicioController {

    private Usuarios usuario;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PQRService pqrService;

    @Autowired
    private CorteService corteService;

    @Autowired
    private FAQService faqService;

    @Autowired
    private UsuarioService usuarioService;

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

    @GetMapping("/editarPQR/{id}")
    public String editarPQR(Model model, HttpSession session, @PathVariable(name = "id") String id) {
        PQR pqr = pqrService.obtenerUnPQRPorID(id);
        model.addAttribute("pqr", pqr);
        model.addAttribute("pregunta", pqr.getDescripcion());
        return "editarPQR";
    }

    @PostMapping("/actualizarPQR/{id}")
    public String actualizarPQR(HttpSession session, @PathVariable("id") String id,
            @ModelAttribute("pqr") PQR pqr, Model model) {
        PQR pqrExistente = pqrService.obtenerUnPQRPorID(id);
        if (pqrExistente != null) {
            pqrExistente.setRespondidoPor(usuario.getUsuario());
            pqrExistente.setEstado("respondido");
            pqrExistente.setRespuesta(pqr.getRespuesta());

            pqrService.guardarPQR(pqrExistente);
            return "redirect:/servicios/monitorearPQR/vistaCompleta";
        } else {

            return "redirect:/editarPQR/{" + id + "}?error))";
        }

    }

    @GetMapping("/borrarPQR/{id}")
    public String borrarPQR(Model model, HttpSession session, @PathVariable(name = "id") String id) {
        pqrService.eliminarPQR(id);
        return "redirect:/servicios/monitorearPQR";
    }

    @GetMapping("/servicios/agregarUsuario")
    public String agregarUsuario(Model model, HttpSession session) {

        List<String> listadoPrueba = new ArrayList<String>();
        listadoPrueba.add("ROLE_ADMIN");
        listadoPrueba.add("ROLE_ASESOR");
        model.addAttribute("listadoRol", listadoPrueba);
        model.addAttribute("usuario", new Usuarios());
        return "agregarUsuario";
    }

    @PostMapping("/servicios/agregarUsuario")
    public String agregarNuevoUsuario(Model model, HttpSession session,
            @ModelAttribute("usuario") Usuarios usuario, RedirectAttributes redirAttr) {
        String email = usuario.getEmail();
        Usuarios usuarioExistente = usuarioService.obtenerPorEmail(email);
        if (usuarioExistente.getEmail().equals(email)) {
            redirAttr.addFlashAttribute("emailNotValid",
                    messageSource.getMessage("email.not.valid", null, LocaleContextHolder.getLocale()));
            return "redirect:/servicios/monitorearUsuarios";
        }
        if (usuarioExistente.getUsuario().equals(usuario.getUsuario())) {
            redirAttr.addFlashAttribute("userNotValid",
                    messageSource.getMessage("user.not.valid", null, LocaleContextHolder.getLocale()));
            return "redirect:/servicios/monitorearUsuarios";
        }
        Rol rol = new Rol();
        rol.setNombre(usuario.getBck());
        usuario.setRoles(Arrays.asList(rol));
        usuario.setBck(null);

        usuarioService.guardarUsuarioAdmin(usuario);
        redirAttr.addFlashAttribute("userSuccessSignUp",
                messageSource.getMessage("user.admin.sign.up", null, LocaleContextHolder.getLocale()));

        return "redirect:/servicios/monitorearUsuarios";
    }

    @GetMapping("/servicios/monitorearUsuarios")
    public String monitorearUsuarios(Model model, HttpSession session) {
        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
        return "monitorearUsuarios";
    }

    @GetMapping("/servicios/borrarUsuarios/{id}")
    public String borrarUsuario(Model model, HttpSession session, @PathVariable(name = "id") String id) {
        usuarioService.eliminarUsuarioPorID(id);
        return "redirect:/servicios/monitorearUsuarios";
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

        corteService.sendCorteEmailFirst(usuario, corte);
        corteService.guardarCorte(corte);

        redirAttr.addFlashAttribute("corteSuccessMsg",
                messageSource.getMessage("corte.success.msg", null, LocaleContextHolder.getLocale()));
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/mostrarFAQ")
    public String mostrarFAQ(Model model, HttpSession session) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        model.addAttribute("faqs", faqService.obtenerFAQs());
        return "mostrarFAQ";
    }

    @GetMapping("/servicios/FAQ")
    public String mostrarFAQ(Model model, HttpSession session, @Param("id") String id) {
        if (id != null) {
            faqService.eliminarFAQsporID(id);
            model.addAttribute("id", id);
            return "redirect:/servicios/FAQ";
        }
        model.addAttribute("faqs", faqService.obtenerFAQs());

        return "FAQ";
    }

    @GetMapping("/servicios/FAQ/nuevoFAQ")
    public String nuevoFAQ(Model model, HttpSession session) {
        return "nuevoFAQ";
    }

    @ModelAttribute("FAQ")
    public FAQ nuevoFaq() {
        return new FAQ();
    }

    @PostMapping("/servicios/FAQ/nuevoFAQ")
    public String peticionFAQ(@ModelAttribute("FAQ") FAQ faq, HttpSession session) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        faq.setUsuario(usuario);
        faqService.guardarFAQ(faq);
        return "redirect:/servicios/FAQ";
    }

    @GetMapping("/editarFAQ/{id}")
    public String editarFAQ(Model model, HttpSession session, @PathVariable(name = "id") String id) {
        FAQ faq = faqService.obtenerFAQporID(id);
        model.addAttribute("faq", faq);
        model.addAttribute("pregunta", faq.getPregunta());
        model.addAttribute("respuesta", faq.getRespuesta());
        return "editarFAQ";
    }

    @PostMapping("/actualizarFAQ/{id}")
    public String actualizarFAQ(HttpSession session, @PathVariable("id") String id,
            @ModelAttribute("faq") FAQ faq, Model model) {
        FAQ faqExistente = faqService.obtenerFAQporID(id);

        if (faqExistente != null) {
            faqExistente.setPregunta(faq.getPregunta());
            faqExistente.setRespuesta(faq.getRespuesta());
            faqExistente.setUsuario(usuario);

            faqService.guardarFAQ(faqExistente);
            return "redirect:/servicios/FAQ";
        } else {

            return "redirect:/editarFAQ/{" + id + "}?error))";
        }

    }

}
