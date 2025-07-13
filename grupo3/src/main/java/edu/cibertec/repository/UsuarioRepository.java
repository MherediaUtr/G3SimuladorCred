package edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.cibertec.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
    //@Query(value = "SELECT * FROM usuario u WHERE u.user = ?1 AND u.contrasena = ?2", nativeQuery = true)
    @Query("SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.contrasena = :contrasena")
    public UsuarioEntity validarUsuario(String usuario, String contrasena);
}
