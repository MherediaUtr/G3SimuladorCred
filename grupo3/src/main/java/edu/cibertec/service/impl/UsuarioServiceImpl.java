package edu.cibertec.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cibertec.entity.UsuarioEntity;
import edu.cibertec.repository.UsuarioRepository;
import edu.cibertec.service.UsuarioService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    public final UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioEntity validarUsuario(String usuario, String contrasena){
        return usuarioRepository.validarUsuario(usuario, contrasena);
    }

    @Override
    public UsuarioEntity registrarUsuario(UsuarioEntity usuarioEntity) {
        LocalDate fechaActual = LocalDate.now();
        String encodedPassword = passwordEncoder.encode(usuarioEntity.getContrasena());
        usuarioEntity.setContrasena(encodedPassword);
        usuarioEntity.setEstado(true); // Assuming new users are active by default
        usuarioEntity.setRol("CLIENTE");
        usuarioEntity.setFechaCreacion(new Date(fechaActual.getYear(), fechaActual.lengthOfMonth(), fechaActual.getDayOfMonth()));
        usuarioEntity.setFechaModif(new Date(fechaActual.getYear(), fechaActual.lengthOfMonth(), fechaActual.getDayOfMonth()));
        usuarioEntity.setUsrCreacion(usuarioEntity.getUsuario());
        usuarioEntity.setUsrModif(usuarioEntity.getUsuario());
        return usuarioRepository.save(usuarioEntity);
    }

    @Override
    public List<UsuarioEntity> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioEntity obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElse(null);
    }

    @Override
    public UsuarioEntity actualizarUsuario(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    @Override
    public UsuarioEntity eliminarUsuario(Integer id) {
        UsuarioEntity usuario = obtenerUsuarioPorId(id);
        if (usuario != null) {
            usuario.setEstado(false);
            usuarioRepository.save(usuario);
        }
        return usuario;
    }
    
}
