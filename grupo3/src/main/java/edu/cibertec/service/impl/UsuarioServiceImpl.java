package edu.cibertec.service.impl;

import org.springframework.stereotype.Service;

import edu.cibertec.entity.UsuarioEntity;
import edu.cibertec.repository.UsuarioRepository;
import edu.cibertec.service.UsuarioService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    public final UsuarioRepository usuarioRepository;

    public UsuarioEntity validarUsuario(String usuario, String contrasena){
        return usuarioRepository.validarUsuario(usuario, contrasena);
    }
}
