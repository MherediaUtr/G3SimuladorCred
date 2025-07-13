package edu.cibertec.service;

import edu.cibertec.entity.UsuarioEntity;
import edu.cibertec.repository.UsuarioRepository;

public interface UsuarioService {
    public UsuarioEntity validarUsuario(String usuario, String contrasena);
}
