package com.draxter.draxter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DraxterController {

    @RequestMapping("/nosotros")
    public String nosotros() {
        return "nosotros";
    }

}
