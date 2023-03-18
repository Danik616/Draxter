package com.draxter.draxter.Controller;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.draxter.draxter.Entity.Producto;
import com.draxter.draxter.Entity.Rol;
import com.draxter.draxter.Entity.Usuarios;

import com.draxter.draxter.Service.ProductoService;
import com.draxter.draxter.Service.UsuarioService;
import com.draxter.draxter.data.ResetPasswordData;

@Controller
public class DraxterInicioController {

    private Usuarios usuario;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MessageSource messageSource;

    List<Rol> listadoRol = new ArrayList<Rol>();

    public DraxterInicioController(ProductoService productoService,
            UsuarioService usuarioService) {
        this.productoService = productoService;

        this.usuarioService = usuarioService;
    }

    @RequestMapping("/iniciarSesion")
    public String iniciarSesion(Model model) {
        model.addAttribute("forgotPassword", new ResetPasswordData());
        return "iniciar";
    }

    @RequestMapping("/nosotros")
    public String nosotros() {
        return "nosotros";
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
        model.addAttribute("user", usuario);
        return "mostrarUsuario";
    }

    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model, @Param("filtro") String genero) {
        model.addAttribute("productos", productoService.obtenerProductoPorGenero(genero));
        return "mostrarCatalogo";
    }

    @GetMapping("/catalogo/{id}")
    public String mostrarProducto(@PathVariable Long id, Model model, RedirectAttributes redirAttr) {

        Producto pr = productoService.obtenerProductoPorId(id);
        if (pr.isVisibilidad() == false) {
            redirAttr.addFlashAttribute("productNoDispo",
                    messageSource.getMessage("product.not.dispo", null,
                            LocaleContextHolder.getLocale()));
            return "redirect:/catalogo";
        }
        model.addAttribute("producto", pr);
        String caracteristicas = pr.getCaracteristicas();
        String tallaje = pr.getTallaje();
        String[] tallajeVector = tallaje.split(",");
        String[] caracteristicasVector = caracteristicas.split(",");

        model.addAttribute("caracteristicas", caracteristicasVector);
        model.addAttribute("tallas", tallajeVector);

        return "quickView";
    }

    @GetMapping("/servicios/agregarProducto")
    public String agregarProducto(Model model, HttpSession session) {
        List<String> listadoGenero = new ArrayList<String>();
        List<String> listadoTallas = new ArrayList<String>();
        listadoGenero.add("FEMENINA");
        listadoGenero.add("MASCULINA");
        listadoGenero.add("UNISEX");
        listadoTallas.add("XS");
        listadoTallas.add("S");
        listadoTallas.add("M");
        listadoTallas.add("L");
        listadoTallas.add("XL");

        model.addAttribute("listadoGenero", listadoGenero);
        model.addAttribute("tallas", listadoTallas);
        return "agregarProducto";
    }

    @PostMapping("/servicios/agregarProducto")
    public String guardarProducto(HttpSession session, @RequestParam("file") MultipartFile imagen,
            @ModelAttribute("producto") Producto producto, Model model, RedirectAttributes redirAttr) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        producto.setUsuario(usuario);
        producto.setVisibilidad(true);
        if (!imagen.isEmpty()) {
            if (producto.getTallaje() != null) {
                String rutaAbsoluta = "draxter//src//main//resources//static//assets//img";

                try {
                    byte[] bytesImg = imagen.getBytes();
                    String id = producto.getNombre();
                    String nombreArchivo = id + imagen.getOriginalFilename();
                    Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreArchivo);
                    Files.write(rutaCompleta, bytesImg);
                    producto.setImagen(nombreArchivo);
                } catch (IOException e) {
                    e.printStackTrace();

                }
                productoService.guardaProducto(producto);
                redirAttr.addFlashAttribute("infoSaved",
                        messageSource.getMessage("info.saved", null, LocaleContextHolder.getLocale()));
                return "redirect:/servicios/agregarProducto";
            } else {
                redirAttr.addFlashAttribute("tallajeEmpty",
                        messageSource.getMessage("tallaje.empty", null, LocaleContextHolder.getLocale()));
                return "redirect:/servicios/agregarProducto";
            }
        } else {
            redirAttr.addFlashAttribute("imgNotEmpty",
                    messageSource.getMessage("img.not.empty", null, LocaleContextHolder.getLocale()));
            return "redirect:/servicios/agregarProducto";
        }

    }

    @GetMapping("/servicios/monitorearProductos")
    public String monitorearProductos(Model model, HttpSession session) {
        model.addAttribute("productos", productoService.obtenerProductos());
        return "mostrarCatalogo";
    }

    @GetMapping("/servicios/monitorearProductos/{id}")
    public String eliminarProducto(Model model, HttpSession session, @PathVariable(name = "id") String id,
            RedirectAttributes redirAttr) {
        try {
            productoService.eliminarProductoPorID(id);
            redirAttr.addFlashAttribute("productDeleted",
                    messageSource.getMessage("product.deleted", null, LocaleContextHolder.getLocale()));
        } catch (Exception ex) {
            long idBusqueda = Long.parseLong(id);
            Producto producto = productoService.obtenerProductoPorId(idBusqueda);
            producto.setVisibilidad(false);
            productoService.guardaProducto(producto);
            redirAttr.addFlashAttribute("productNotDeleted",
                    messageSource.getMessage("product.not.deleted", null, LocaleContextHolder.getLocale()));
        }

        return "redirect:/servicios/monitorearProductos";
    }

    @GetMapping("/servicios/monitorearProductos/editar/{id}")
    public String editarProducto(Model model, HttpSession session, @PathVariable(name = "id") String id,
            RedirectAttributes redirAttr) {
        long idBusqueda = Long.parseLong(id);
        Producto producto = productoService.obtenerProductoPorId(idBusqueda);

        if (producto.isVisibilidad() == false) {
            redirAttr.addFlashAttribute("productNoDispo",
                    messageSource.getMessage("product.not.dispo", null,
                            LocaleContextHolder.getLocale()));
            return "redirect:/servicios/monitorearProductos";
        }
        List<String> listadoGenero = new ArrayList<String>();
        List<String> listadoTallas = new ArrayList<String>();
        listadoGenero.add("FEMENINA");
        listadoGenero.add("MASCULINA");
        listadoGenero.add("UNISEX");
        listadoTallas.add("XS");
        listadoTallas.add("S");
        listadoTallas.add("M");
        listadoTallas.add("L");
        listadoTallas.add("XL");

        model.addAttribute("listadoGenero", listadoGenero);
        model.addAttribute("tallas", listadoTallas);
        model.addAttribute("producto", producto);
        return "editarProducto";
    }

    @PostMapping("/servicios/monitorearProductos/editar/{id}")
    public String actualizarProducto(@RequestParam("file") MultipartFile imagen, HttpSession session,
            @PathVariable(name = "id") String id,
            @ModelAttribute("producto") Producto producto, RedirectAttributes redirAttr) {
        usuario = (Usuarios) session.getAttribute("usuariosesion");
        long idBusqueda = Long.parseLong(id);
        Producto productoExistente = productoService.obtenerProductoPorId(idBusqueda);
        String nombreAnteriorImagen = productoExistente.getImagen();
        productoExistente.setUsuario(usuario);
        if (!imagen.isEmpty()) {
            String rutaAbsoluta = "draxter//src//main//resources//static//assets//img";

            try {
                byte[] bytesImg = imagen.getBytes();
                String nombreProducto = producto.getNombre();
                String nombreArchivo = nombreProducto + imagen.getOriginalFilename();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreArchivo);
                if (nombreAnteriorImagen != null) {
                    Path rutaEliminacion = Paths.get(rutaAbsoluta + "//" + nombreAnteriorImagen);
                    Files.delete(rutaEliminacion);
                }
                Files.write(rutaCompleta, bytesImg);
                productoExistente.setImagen(nombreArchivo);
            } catch (IOException e) {
                e.printStackTrace();

            }

        }

        if (producto.getTallaje() != null) {
            productoExistente.setCaracteristicas(producto.getCaracteristicas());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setGenero(producto.getGenero());
            productoExistente.setTallaje(producto.getTallaje());
            productoExistente.setVisibilidad(true);
            productoService.guardaProducto(productoExistente);
            redirAttr.addFlashAttribute("infoSaved",
                    messageSource.getMessage("info.saved", null, LocaleContextHolder.getLocale()));
        } else {
            redirAttr.addFlashAttribute("tallajeEmpty",
                    messageSource.getMessage("tallaje.empty", null, LocaleContextHolder.getLocale()));
        }
        return "redirect:/servicios/monitorearProductos";

    }

    @GetMapping("/servicios/editarUsuario/{id}")
    public String editarUsuario(Model model, HttpSession session, @PathVariable(name = "id") String id) {
        Usuarios usuario = usuarioService.obtenerUsuarioPorID(id);
        model.addAttribute("usuario", usuario);
        return "editarUsuario";
    }

    @PostMapping("/servicios/editarUsuario/{id}")
    public String actualizarUsuario(Model model, @RequestParam("file") MultipartFile imagen, HttpSession session,
            @PathVariable(name = "id") String id,
            @ModelAttribute("usuario") Usuarios usuario, RedirectAttributes redirAttr) {
        Usuarios usuarioExistente = usuarioService.obtenerUsuarioPorID(id);
        String nombreAnteriorImagen = usuarioExistente.getImagen();
        if (!imagen.isEmpty()) {
            String rutaAbsoluta = "draxter//src//main//resources//static//assets//img";

            try {
                byte[] bytesImg = imagen.getBytes();
                String usuarioDelUsuario = usuarioExistente.getUsuario();
                String nombreArchivo = usuarioDelUsuario + imagen.getOriginalFilename();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreArchivo);
                if (nombreAnteriorImagen != null) {
                    Path rutaEliminacion = Paths.get(rutaAbsoluta + "//" + nombreAnteriorImagen);
                    Files.delete(rutaEliminacion);
                }
                Files.write(rutaCompleta, bytesImg);

                usuarioExistente.setImagen(nombreArchivo);

            } catch (IOException e) {
                e.printStackTrace();

            }

            return "redirect:/";
        }
        usuarioExistente.setApellidos(usuario.getApellidos());
        usuarioExistente.setCelular(usuario.getCelular());
        usuarioExistente.setDireccion(usuario.getDireccion());
        usuarioExistente.setNombres(usuario.getNombres());
        usuarioExistente.setPais(usuario.getPais());
        usuarioService.guardar(usuarioExistente);
        redirAttr.addFlashAttribute("infoSaved",
                messageSource.getMessage("info.saved", null, LocaleContextHolder.getLocale()));
        return "redirect:/";

    }

}
