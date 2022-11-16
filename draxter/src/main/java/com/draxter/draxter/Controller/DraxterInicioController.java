package com.draxter.draxter.Controller;

import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.draxter.draxter.Entity.Producto;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Service.ProductoService;

@Controller
public class DraxterInicioController {

    private Usuarios usuario;

    @Autowired
    private ProductoService productoService;

    public DraxterInicioController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @RequestMapping("/iniciarSesion")
    public String iniciarSesion() {
        return "iniciar";
    }

    @GetMapping("/")
    public String mostrarUsuario(Model model, HttpSession session) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        model.addAttribute("nombre", usuario.getNombres());
        model.addAttribute("usuario", usuario.getUsuario());
        model.addAttribute("pais", usuario.getPais());
        model.addAttribute("celular", usuario.getCelular());
        model.addAttribute("direccion", usuario.getDireccion());
        return "mostrarUsuario";
    }

    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        model.addAttribute("productos", productoService.obtenerProductos());
        return "mostrarCatalogo";
    }

    @GetMapping("/catalogo/{id}")
    public String mostrarProducto(@PathVariable Long id, Model model){
        Producto pr= productoService.obtenerProductoPorId(id);
        model.addAttribute("nombre", pr.getNombre());
        model.addAttribute("precio", pr.getPrecio());
        model.addAttribute("descripcion", pr.getDescripcion());
        String caracteristicas=pr.getCaracteristicas();
        String[] caracteristicasVector=caracteristicas.split(",");
        List<String> caracteristicasLista=Arrays.asList(caracteristicasVector);
        model.addAttribute("caracteristicas", caracteristicasLista);

        return "MostrarProducto";
    }

    @GetMapping("/servicios")
    public String mostrarServicios(){
        return "MostrarServicios";
    }
}
