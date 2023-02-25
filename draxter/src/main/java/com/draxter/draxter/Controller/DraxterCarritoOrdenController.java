package com.draxter.draxter.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.draxter.draxter.Entity.Carrito;
import com.draxter.draxter.Entity.Producto;
import com.draxter.draxter.Entity.Usuarios;
import com.draxter.draxter.Service.CarritoService;
import com.draxter.draxter.Service.ProductoService;
import com.draxter.draxter.Service.UsuarioService;

@Controller
public class DraxterCarritoOrdenController {

    private Usuarios usuario;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private MessageSource messageSource;

    public DraxterCarritoOrdenController(ProductoService productoService, UsuarioService usuarioService,
            CarritoService carritoService) {
        this.productoService = productoService;
        this.usuarioService = usuarioService;
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
    public String eliminarDelCarrito(Model model, HttpSession session, @PathVariable Long id,
            RedirectAttributes redirAttr) {
        carritoService.eliminarCarritoPorIdProducto(id);
        redirAttr.addFlashAttribute("eliminacionCarrito",
                messageSource.getMessage("user.shopping.kart.deleted", null,
                        LocaleContextHolder.getLocale()));
        return "redirect:/carrito";
    }

}
