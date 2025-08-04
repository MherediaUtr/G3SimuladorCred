package edu.cibertec.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import edu.cibertec.entity.ProductoEntity;
import edu.cibertec.entity.ErrorEntity;
import edu.cibertec.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones sobre productos de crédito")
public class ProductoController {

    private final ProductoService productoService;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorEntity> handleError(ResponseStatusException ex) {
        String statusText = ex.getStatusCode().toString();
        String reason = ex.getReason();
        Integer code = ex.getStatusCode().value();
        ErrorEntity err = new ErrorEntity(statusText, reason, code);
        return ResponseEntity.status(ex.getStatusCode()).body(err);
    }

    @GetMapping
    @Operation(summary = "Listar Productos")
    public ResponseEntity<List<ProductoEntity>> listar() {
        List<ProductoEntity> list = productoService.listarProductos();
        list.forEach(p -> {
            Link self = linkTo(methodOn(ProductoController.class)
                    .obtener(p.getIdProd())).withSelfRel();
            p.add(self);
        });
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Producto por ID")
    public ResponseEntity<ProductoEntity> obtener(@PathVariable Integer id) {
        try {
            ProductoEntity prod = productoService.obtenerProducto(id);
            if (prod == null) {
                return ResponseEntity.notFound().build();
            }
            prod.add(linkTo(methodOn(ProductoController.class)
                    .obtener(id)).withSelfRel());
            return ResponseEntity.ok(prod);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Producto no encontrado", e);
        }
    }

    @PostMapping
    @Operation(summary = "Registrar Producto")
    public ResponseEntity<ProductoEntity> crear(@RequestBody ProductoEntity prod) {
        try {
            // Completar campos si vienen null
            if (prod.getFechaCrea() == null) {
                prod.setFechaCrea(LocalDateTime.now());
            }
            if (prod.getEstado() == null) {
                prod.setEstado(true);
            }

            ProductoEntity creado = productoService.registrarProducto(prod);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "No se pudo registrar el producto", e);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Producto")
    public ResponseEntity<ProductoEntity> actualizar(
            @PathVariable Integer id,
            @RequestBody ProductoEntity prod) {
        ProductoEntity existente = productoService.obtenerProducto(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar campos
        existente.setDescripcion(prod.getDescripcion());
        existente.setTasa(prod.getTasa());
        existente.setMesesMinimo(prod.getMesesMinimo());
        existente.setMesesMaximo(prod.getMesesMaximo());
        existente.setIngresoMinimo(prod.getIngresoMinimo());
        existente.setMontoMinimo(prod.getMontoMinimo());
        existente.setMontoMaximo(prod.getMontoMaximo());
        existente.setCredInicial(prod.getCredInicial());
        existente.setRequiereSeguro(prod.getRequiereSeguro());
        existente.setPorcSeguro(prod.getPorcSeguro());
        existente.setPorcMora(prod.getPorcMora());
        existente.setEstado(prod.getEstado());
        existente.setFechaUpd(LocalDateTime.now());

        ProductoEntity actualizado = productoService.actualizarProducto(existente);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Producto")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ProductoEntity prod = productoService.eliminarProducto(id);
        if (prod == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
