package com.draxter.draxter.Controller;

import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.draxter.draxter.Entity.Corte;
import com.draxter.draxter.Entity.FAQ;
import com.draxter.draxter.Service.PQRService;
import com.draxter.draxter.Service.ProductoService;
import com.draxter.draxter.Service.CorteService;
import com.draxter.draxter.Service.FAQService;

@Controller
public class DraxterInicioController {

    private Usuarios usuario;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private PQRService pqrService;

    @Autowired
    private CorteService corteService;

    @Autowired
    private FAQService faqService;

    public DraxterInicioController(ProductoService productoService, PQRService pqrService, CorteService corteService,
            FAQService faqService) {
        this.productoService = productoService;
        this.pqrService = pqrService;
        this.corteService = corteService;
        this.faqService = faqService;
    }

    @RequestMapping("/iniciarSesion")
    public String iniciarSesion() {
        return "iniciar";
    }

    @GetMapping("/")
    public String mostrarUsuario(Model model, HttpSession session) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        model.addAttribute("nombre", usuario.getNombres());
        model.addAttribute("correo", usuario.getEmail());
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
    public String mostrarProducto(@PathVariable Long id, Model model) {
        Producto pr = productoService.obtenerProductoPorId(id);
        model.addAttribute("nombre", pr.getNombre());
        model.addAttribute("precio", pr.getPrecio());
        model.addAttribute("descripcion", pr.getDescripcion());
        model.addAttribute("id", id);
        String caracteristicas = pr.getCaracteristicas();
        String[] caracteristicasVector = caracteristicas.split(",");
        List<String> caracteristicasLista = Arrays.asList(caracteristicasVector);
        model.addAttribute("caracteristicas", caracteristicasLista);

        return "MostrarProducto";
    }

    @GetMapping("/catalogo/{id}/pagarPedido")
    public String pagarPedido() {
        return "pagarPedido";
    }

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
    public String radicacionPQR(@ModelAttribute("pqr") PQR pqr, HttpSession session) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        pqr.setEstado("No respondido");
        pqr.setUsuario(usuario);
        pqrService.guardarPQR(pqr);

        return "redirect:/servicios/radicarPQR?exito";
    }

    @GetMapping("/servicios/monitorearPQR")
    public String monitorearPQR(Model model, HttpSession session, @Param("id") String id) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        List<PQR> listaPqr = pqrService.obtenerPQRPorId(id, usuario.getUsuario());
        List<PQR> listaPqrAdmin = pqrService.obtenerPQRPorIdAdmin(id);
        model.addAttribute("pqrs", listaPqr);
        model.addAttribute("pqrsAdmin", listaPqrAdmin);
        model.addAttribute("id", id);
        return "monitorearPQRCliente";
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
    public String peticionCorte(@ModelAttribute("corte") Corte corte, HttpSession session) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        corte.setUsuario(usuario);
        corteService.guardarCorte(corte);
        return "redirect:/servicios/pedirCorte?exito";
    }

    @GetMapping("/servicios/mostrarFAQ")
    public String mostrarFAQ(Model model, HttpSession session) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        model.addAttribute("faqs", faqService.obtenerFAQs());
        return "mostrarFAQ";
    }

    @GetMapping("/pedidos")
    public String mostrarPedidos(Model model, HttpSession session) {
        return "AdministrarPedidos";
    }

    @GetMapping("/editarPQR/{id}")
    public String editarPQR(Model model, HttpSession session, @PathVariable(name = "id") String id) {
        PQR pqr = pqrService.obtenerUnPQRPorID(id);
        model.addAttribute("pqr", pqr);
        model.addAttribute("pregunta", pqr.getDescripcion());
        return "editarPQR";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPQR(HttpSession session, @PathVariable("id") String id,
            @ModelAttribute("pqr") PQR pqr, Model model) {
        PQR pqrExistente = pqrService.obtenerUnPQRPorID(id);
        if (pqrExistente != null) {
            pqrExistente.setRespondidoPor(usuario.getUsuario());
            pqrExistente.setEstado("respondido");
            pqrExistente.setRespuesta(pqr.getRespuesta());

            pqrService.guardarPQR(pqrExistente);
            return "redirect:/servicios/monitorearPQR";
        } else {

            return "redirect:/editarPQR/{" + id + "}?error))";
        }

    }

    @GetMapping("/borrarPQR/{id}")
    public String borrarPQR(Model model, HttpSession session, @PathVariable(name = "id") String id) {
        pqrService.eliminarPQR(id);
        return "redirect:/servicios/monitorearPQR";
    }

    @GetMapping("/servicios/FAQ")
    public String editarFAQ(Model model, HttpSession session) {
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
        return "redirect:/servicios/FAQ/nuevoFAQ?exito";
    }

}
