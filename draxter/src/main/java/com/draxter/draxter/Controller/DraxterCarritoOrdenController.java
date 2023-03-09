package com.draxter.draxter.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.draxter.draxter.Entity.Producto;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Entity.Carrito;
import com.draxter.draxter.Service.CarritoService;
import com.draxter.draxter.Service.ProductoService;

@Controller
public class DraxterCarritoOrdenController {

    private Usuarios usuario;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private MessageSource messageSource;

    public DraxterCarritoOrdenController(ProductoService productoService,
            CarritoService carritoService) {
        this.productoService = productoService;
        this.carritoService = carritoService;
    }

    @GetMapping("carrito/{id}")
    public String añadirAlCarrito(Model model, HttpSession session, @PathVariable Long id,
            RedirectAttributes redirAttr) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        Producto productoExistente = productoService.obtenerProductoPorId(id);
        List<Producto> productos = carritoService.obtenerProductosPorUsuario(usuario);
        if (productos.contains(productoExistente)) {
            redirAttr.addFlashAttribute("carritoNoGuardado",
                    messageSource.getMessage("user.shopping.kart.no.saved", null,
                            LocaleContextHolder.getLocale()));
            return "redirect:/catalogo"; // crear un modal o ventana emergente
        } else {
            redirAttr.addFlashAttribute("carritoGuardado",
                    messageSource.getMessage("user.shopping.kart.saved", null,
                            LocaleContextHolder.getLocale()));
            carritoService.addToCarrito(usuario, productoExistente);
            return "redirect:/catalogo"; // crear un modal o ventana emergente
        }

    }

    @GetMapping("carrito")
    public String mostrarPedidos(Model model, HttpSession session) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        List<Producto> productos = carritoService.obtenerProductosPorUsuario(usuario);
        model.addAttribute("productos", productos);
        return "AdministrarPedidos";
    }

    @GetMapping("eliminarDelCarrito/{id}")
    public String eliminarDelCarrito(Model model, @PathVariable Long id,
            RedirectAttributes redirAttr) {
        carritoService.eliminarCarritoPorIdProducto(id);
        redirAttr.addFlashAttribute("eliminacionCarrito",
                messageSource.getMessage("user.shopping.kart.deleted", null,
                        LocaleContextHolder.getLocale()));
        return "redirect:/carrito";
    }

    @GetMapping("carrito/producto/{id}")
    public String verDetalles(Model model, RedirectAttributes redirAttr, HttpSession session, @PathVariable Long id) {
        Producto pr = productoService.obtenerProductoPorId(id);
        model.addAttribute("producto", pr);
        String caracteristicas = pr.getCaracteristicas();
        String[] caracteristicasVector = caracteristicas.split(",");
        // pasar un objeto de tipo orden
        model.addAttribute("caracteristicas", caracteristicasVector);
        return "productoCarrito";
    }

    // Desde aqui se va a manejar las ordenes

    // convertir en un post mapping para traer el producto orden y añadirle la
    // información completa
    @GetMapping("comprarProducto/{id}")
    public String comprarProducto(Model model, RedirectAttributes redirAttr, HttpSession session,
            @PathVariable Long id) {
        Producto pr = productoService.obtenerProductoPorId(id);
        Carrito carrito = carritoService.obtenerCarritoPorIdProducto(id);
        model.addAttribute("producto", pr);
        String caracteristicas = pr.getCaracteristicas();
        String[] caracteristicasVector = caracteristicas.split(",");

        model.addAttribute("caracteristicas", caracteristicasVector);
        return "productoCarrito";
    }
}
