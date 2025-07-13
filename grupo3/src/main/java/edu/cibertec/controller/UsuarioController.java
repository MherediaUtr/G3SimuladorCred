package edu.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import edu.cibertec.entity.UsuarioEntity;
import edu.cibertec.service.UsuarioService;

@Controller
@AllArgsConstructor
@Log
public class UsuarioController {
    private final UsuarioService usuarioService;

    @RequestMapping("/")
    public String login(HttpSession sesion) {
        UsuarioEntity usuario=(UsuarioEntity) sesion.getAttribute("usuario");
         if (usuario != null) {
            log.info("Usuario: " + usuario.getUsuario() + " ha iniciado sesión.");
            return "redirect:home";
        }else {
            log.warning("Usuario no autenticado, redirigiendo a login.");
            return "login";
        }
    }

    @RequestMapping("home")
    public ModelAndView home(HttpSession sesion){
        UsuarioEntity usuario=(UsuarioEntity) sesion.getAttribute("usuario");
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("usuario", usuario);
        return mv;
    }

    @RequestMapping("actionLogin")
    public ModelAndView actionLogin(String usuario, String contrasena, HttpSession session) {
        ModelAndView mv = new ModelAndView("login");
        UsuarioEntity usuarioEntity = usuarioService.validarUsuario(usuario, contrasena);
        if (usuarioEntity != null) {
           session.setAttribute("usuario", usuarioEntity);
           mv.setViewName("redirect:home");
        } else {
           mv.addObject("msgError", "Usuario o contraseña incorrectos");
        }
        return mv;
    }
}
