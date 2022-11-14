package com.draxter.draxter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DraxterInicioController {

    @RequestMapping("/iniciarSesion")
    public String iniciarSesion(){
        return "iniciar";
    }

    @RequestMapping("/")
    public String cliente(){
        return "mostrarCliente";
    }
}
