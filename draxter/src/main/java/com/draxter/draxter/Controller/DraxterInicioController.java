package com.draxter.draxter.Controller;

import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.draxter.draxter.Entity.PQR;
import com.draxter.draxter.Entity.Producto;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Service.PQRService;
import com.draxter.draxter.Service.ProductoService;

@Controller
public class DraxterInicioController {

    private Usuarios usuario;

    @Autowired
    private ProductoService productoService;

    private PQRService pqrService;

    

    public DraxterInicioController(ProductoService productoService, PQRService pqrService) {
        this.productoService = productoService;
        this.pqrService = pqrService;
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
        model.addAttribute("id", id);
        String caracteristicas=pr.getCaracteristicas();
        String[] caracteristicasVector=caracteristicas.split(",");
        List<String> caracteristicasLista=Arrays.asList(caracteristicasVector);
        model.addAttribute("caracteristicas", caracteristicasLista);

        return "MostrarProducto";
    }

    @GetMapping("/catalogo/{id}/pagarPedido")
    public String pagarPedido(){
        return "pagarPedido";
    }

    @RequestMapping("/servicios")
    public String mostrarServicios(){
        return "MostrarServicios";
    }

    @GetMapping("/servicios/radicarPQR")
    public String radicarPQR(){
        return "radicarPQR";
    }

    @ModelAttribute("pqr")
    public PQR pqr(){
        return new PQR();
    }

    @PostMapping("/servicios/radicarPQR")
    public String radicacionPQR(@ModelAttribute("pqr") PQR pqr, HttpSession session){
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        pqr.setEstado("No respondido");
        pqr.setUsuario(usuario);
        pqrService.guardarPQR(pqr);

        return "redirect:/servicios/radicarPQR?exito";
    }

    @GetMapping("/servicios/monitorearPQR")
    public String monitorearPQR(Model model, HttpSession session){
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        model.addAttribute("pqrs", pqrService.obtenerPQRs(usuario.getUsuario()));
        return "monitorearPQRCliente";
    }

    @ModelAttribute("id")
    public String id(){
        return new String();
    }
    @GetMapping("/servicios/monitorearPQR/{id}")
    public String monitorearPQRPorId(@ModelAttribute("id") String id, Model model, HttpSession session){
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        Long id_busqueda=Long.parseLong(id);
        model.addAttribute("pqrsi",pqrService.obtenerPQRPorId(id_busqueda, usuario.getUsuario()));
        return "redirect:/servicios/monitorearPQR?exito";
    }

    @GetMapping("/servicios/pedirCorte")
    public String pedirCorte(){
        return "PedirCorte";
    }

    @GetMapping("/servicios/mostrarFAQ")
    public String mostrarFAQ(){
        return "mostrarFAQ";
    }

}
