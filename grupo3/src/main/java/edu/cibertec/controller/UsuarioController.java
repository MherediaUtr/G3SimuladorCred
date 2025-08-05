package edu.cibertec.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.AllArgsConstructor;
import edu.cibertec.entity.UsuarioEntity;
import edu.cibertec.service.UsuarioService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/")
    public List<UsuarioEntity> listarUsuarios() {
       return usuarioService.listarUsuarios();
    }

    @GetMapping("/{idusuario}")
    public UsuarioEntity obtenerUsuario(@PathVariable(value = "idusuario") Integer id) {
       return usuarioService.obtenerUsuarioPorId(id);
    }

    @PostMapping("/")
    public UsuarioEntity registrarUsuario(@RequestBody UsuarioEntity entity) {
                
        return usuarioService.registrarUsuario(entity);
    }
    

    @PutMapping("/")
    public UsuarioEntity actualizarUsuario(@RequestBody UsuarioEntity entity) {
        return usuarioService.actualizarUsuario(entity);
    }

    @DeleteMapping("/{idusuario}")
    public UsuarioEntity eliminarUsuario(@RequestParam(value = "idusuario") Integer id) {
        return usuarioService.eliminarUsuario(id);
    }
}
