package com.draxter.draxter.Controller;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.draxter.draxter.Entity.PQR;
import com.draxter.draxter.Entity.Producto;
import com.draxter.draxter.Entity.Rol;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Entity.Corte;
import com.draxter.draxter.Entity.FAQ;
import com.draxter.draxter.Service.PQRService;
import com.draxter.draxter.Service.ProductoService;
import com.draxter.draxter.Service.UsuarioService;
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

    @Autowired
    private UsuarioService usuarioService;

    List<Rol> listadoRol = new ArrayList<Rol>();

    public DraxterInicioController(ProductoService productoService, PQRService pqrService, CorteService corteService,
            FAQService faqService, UsuarioService usuarioService) {
        this.productoService = productoService;
        this.pqrService = pqrService;
        this.corteService = corteService;
        this.faqService = faqService;
        this.usuarioService = usuarioService;

        listadoRol.add(new Rol("ROLE_ASESOR"));
        listadoRol.add(new Rol("ROLE_ADMIN"));
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

    @PostMapping("/actualizarPQR/{id}")
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
    public String mostrarFAQ(Model model, HttpSession session, @Param("id") String id) {
        if (id != null) {
            faqService.eliminarFAQsporID(id);
            model.addAttribute("id", id);
            return "redirect:/servicios/FAQ";
        }
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
        return "redirect:/servicios/FAQ";
    }

    @GetMapping("/editarFAQ/{id}")
    public String editarFAQ(Model model, HttpSession session, @PathVariable(name = "id") String id) {
        FAQ faq = faqService.obtenerFAQporID(id);
        model.addAttribute("faq", faq);
        model.addAttribute("pregunta", faq.getPregunta());
        model.addAttribute("respuesta", faq.getRespuesta());
        return "editarFAQ";
    }

    @PostMapping("/actualizarFAQ/{id}")
    public String actualizarFAQ(HttpSession session, @PathVariable("id") String id,
            @ModelAttribute("faq") FAQ faq, Model model) {
        FAQ faqExistente = faqService.obtenerFAQporID(id);

        if (faqExistente != null) {
            faqExistente.setPregunta(faq.getPregunta());
            faqExistente.setRespuesta(faq.getRespuesta());
            faqExistente.setUsuario(usuario);

            faqService.guardarFAQ(faqExistente);
            return "redirect:/servicios/FAQ";
        } else {

            return "redirect:/editarFAQ/{" + id + "}?error))";
        }

    }

    @GetMapping("/servicios/agregarProducto")
    public String agregarProducto(Model model, HttpSession session) {
        return "agregarProducto";
    }

    @PostMapping("/servicios/agregarProducto")
    public String guardarProducto(HttpSession session, @RequestParam("file") MultipartFile imagen,
            @ModelAttribute("producto") Producto producto, Model model) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        producto.setUsuario(usuario);
        if (!imagen.isEmpty()) {
            String rutaAbsoluta = "draxter//src//main//resources//static//assets//img";

            try {
                byte[] bytesImg = imagen.getBytes();
                String id = Long.toString(producto.getId());
                String nombreArchivo = id + imagen.getOriginalFilename();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreArchivo);
                Files.write(rutaCompleta, bytesImg);
                producto.setImagen(nombreArchivo);
            } catch (IOException e) {
                e.printStackTrace();

            }
            productoService.guardaProducto(producto);
        } else {
            return "redirect:/servicios/agregarProducto?vacio";
        }

        return "redirect:/servicios/agregarProducto?Sucess";
    }

    @GetMapping("/servicios/agregarUsuario")
    public String agregarUsuario(Model model, HttpSession session) {

        model.addAttribute("listadoRol", listadoRol);
        model.addAttribute("usuario", new Usuarios());
        return "agregarUsuario";
    }

    @PostMapping("/servicios/agregarUsuario")
    public String agregarNuevoUsuario(Model model, HttpSession session, @Param("rol") Rol rol,
            @ModelAttribute("usuario") Usuarios usuario) {
        if (rol.getNombre() != "ROLE_ADMIN" && rol.getNombre() != "ROLE_ASESOR") {
            return "redirect:/servicios/agregarUsuario?error";
        }
        rol.setNombre("ROLE_ADMIN");
        usuario.setRoles(Arrays.asList(rol));
        usuarioService.guardar(usuario);

        return "redirect:/agregarUsuario";
    }

    @GetMapping("/servicios/monitorearProductos")
    public String monitorearProductos(Model model, HttpSession session) {

        return "mostrarCatalogo";
    }
}
