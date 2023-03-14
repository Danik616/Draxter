package com.draxter.draxter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Service.UsuarioService;
import com.draxter.draxter.dto.UsuarioRegistroDTO;

@Controller
@RequestMapping("/registrarse")
public class DraxterRegistroController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "registrar";
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retonerNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO,
            RedirectAttributes redirAttr) {
        String email = registroDTO.getEmail();
        Usuarios usuarioExistente = usuarioService.obtenerPorEmail(email);
        if (usuarioExistente.getEmail().equals(email)) {
            redirAttr.addFlashAttribute("emailNotValid",
                    messageSource.getMessage("email.not.valid", null, LocaleContextHolder.getLocale()));
            return "redirect:/registrarse";
        }
        usuarioService.save(registroDTO);
        redirAttr.addFlashAttribute("userSucessSignUp",
                messageSource.getMessage("user.sign.up", null, LocaleContextHolder.getLocale()));
        return "redirect:/registrarse";
    }
}
