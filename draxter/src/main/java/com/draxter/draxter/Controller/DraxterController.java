package com.draxter.draxter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.draxter.draxter.Service.UsuarioService;
import com.draxter.draxter.dto.UsuarioRegistroDTO;


@Controller
public class DraxterController {

    private UsuarioService usuarioService;

    @RequestMapping("/")
    public String principal(){
        return "inicio";
    }

    @RequestMapping("/nosotros")
    public String nosotros(){
        return "nosotros";
    }
    
    @RequestMapping("/iniciarSesion")
    public String iniciarSesion(){
        return "iniciar";
    }


    @RequestMapping("/terminos")
    public String terminos(){
        return "Terminos";
    }


   public DraxterController(UsuarioService usuarioService) {
     this.usuarioService = usuarioService;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retonerNuevoUsuarioRegistroDTO(){
        return new UsuarioRegistroDTO();
   }

    @GetMapping("/registrarse")
    public String mostrarFormularioDeRegistro(){
        return "registrar";
    }

    @PostMapping
  public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO){
        usuarioService.save(registroDTO);
        return "redirect:/registro?exito";
   }
    
}
