package edu.cibertec.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FNC_producto", schema = "financiera")
@Schema(name = "Producto", description = "Producto de crédito para simulaciones")
public class ProductoEntity extends RepresentationModel<ProductoEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IPROD_ID")
    private Integer idProd;

    @Column(name = "CPROD_DESCRIPCION", length = 200, nullable = false)
    private String descripcion;

    @Column(name = "NPROD_TASA", precision = 5, scale = 2, nullable = false)
    private BigDecimal tasa;

    @Column(name = "IPROD_MESES_MINIMO", nullable = false)
    private Integer mesesMinimo;

    @Column(name = "IPROD_MESES_MAXIMO", nullable = false)
    private Integer mesesMaximo;

    @Column(name = "NPROD_INGRESO_MINIMO", precision = 12, scale = 2, nullable = false)
    private BigDecimal ingresoMinimo;

    @Column(name = "NPROD_MONTO_MINIMO", precision = 12, scale = 2, nullable = false)
    private BigDecimal montoMinimo;

    @Column(name = "NPROD_MONTO_MAXIMO", precision = 12, scale = 2, nullable = false)
    private BigDecimal montoMaximo;

    @Column(name = "NPROD_CRED_INICIAL", precision = 12, scale = 2, nullable = false)
    private BigDecimal credInicial;

    @Column(name = "BPROD_REQUIERE_SEGURO", nullable = false)
    private Boolean requiereSeguro;

    @Column(name = "NPROD_PORC_SEGURO", precision = 5, scale = 2, nullable = false)
    private BigDecimal porcSeguro;

    @Column(name = "NPROD_PORC_MORA", precision = 5, scale = 2, nullable = false)
    private BigDecimal porcMora;

    @Column(name = "BPROD_ESTADO", nullable = false)
    private Boolean estado;

    @Column(name = "DPROD_FECHA_CREACION", nullable = false)
    private LocalDateTime fechaCrea;

    @Column(name = "DPROD_FECHA_MODIF")
    private LocalDateTime fechaUpd;

    @Column(name = "CPROD_USR_CREACION", length = 50)
    private String userCrea;

    @Column(name = "CPROD_USR_MODIF", length = 50)
    private String userUpd;
}
