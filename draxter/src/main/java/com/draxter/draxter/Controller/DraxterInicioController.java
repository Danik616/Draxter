package com.draxter.draxter.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.draxter.draxter.Entity.Usuarios;


@Controller
public class DraxterInicioController {

    @RequestMapping("/iniciarSesion")
    public String iniciarSesion(){
        return "iniciar";
    }

    @GetMapping("/")
    public String mostrarUsuario(Model model,HttpSession session){
        Usuarios usuario= (Usuarios) session.getAttribute("usuariosesion");
        model.addAttribute("nombre", usuario.getNombres());
        model.addAttribute("usuario", usuario.getUsuario());
        model.addAttribute("pais", usuario.getPais());
        model.addAttribute("celular", usuario.getCelular());
        model.addAttribute("direccion", usuario.getDireccion());
        return "mostrarUsuario";
    }
}
