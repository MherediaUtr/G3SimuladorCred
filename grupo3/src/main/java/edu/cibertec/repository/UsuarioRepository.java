package edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.cibertec.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
    //@Query(value = "SELECT * FROM usuario WHERE usuario = ?1 AND contrasena = ?2", nativeQuery = true)
    @Query("SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.contrasena = :contrasena")
    public UsuarioEntity validarUsuario(String user, String password);
}
