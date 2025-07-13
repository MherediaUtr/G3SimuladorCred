package edu.cibertec.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idUsuario;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "correo")
    private String email;

    @Column(name = "tipo_documento")
    private Integer tipoDocumento;

    @Column(name = "dni")
    private String documento;

    @Column(name = "rol")
    private String rol;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
}