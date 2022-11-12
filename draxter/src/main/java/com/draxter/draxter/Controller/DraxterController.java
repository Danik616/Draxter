package com.draxter.draxter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DraxterController {

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

    @RequestMapping("/registrarse")
    public String resgistrarse(){
        return "registrar";
    }

    @RequestMapping("/terminos")
    public String terminos(){
        return "Terminos";
    }
}
