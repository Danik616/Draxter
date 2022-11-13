package com.draxter.draxter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.draxter.draxter.Service.UsuarioService;
import com.draxter.draxter.dto.UsuarioRegistroDTO;

@Controller
@RequestMapping("/registrarse")
public class DraxterRegistroController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String mostrarFormularioDeRegistro(){
        return "registrar";
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retonerNuevoUsuarioRegistroDTO(){
        return new UsuarioRegistroDTO();
   }

    @PostMapping
  public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO){
        usuarioService.save(registroDTO);
        return "redirect:/registro?exito";
   }
}
