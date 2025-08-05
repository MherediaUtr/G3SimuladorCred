package edu.cibertec.service;

import java.util.List;

import edu.cibertec.entity.UsuarioEntity;

public interface UsuarioService {
    public UsuarioEntity validarUsuario(String usuario, String contrasena);
    public UsuarioEntity registrarUsuario(UsuarioEntity usuarioEntity);
    public List<UsuarioEntity> listarUsuarios();
    public UsuarioEntity obtenerUsuarioPorId(Integer id);
    public UsuarioEntity actualizarUsuario(UsuarioEntity usuarioEntity);
    public UsuarioEntity eliminarUsuario(Integer id);
}
