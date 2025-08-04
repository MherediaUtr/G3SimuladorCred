package edu.cibertec.service;

import java.util.List;
import edu.cibertec.entity.ProductoEntity;

/**
 * Interfaz de servicio para operaciones de negocio sobre productos de crédito.
 */
public interface ProductoService {

    /**
     * Lista todos los productos (activos e inactivos).
     */
    List<ProductoEntity> listarProductos();

    /**
     * Obtiene un producto por su ID.
     * 
     * @param id el identificador del producto
     * @return el ProductoEntity, o null si no existe
     */
    ProductoEntity obtenerProducto(Integer id);

    /**
     * Registra un nuevo producto de crédito.
     * 
     * @param producto la entidad con datos del nuevo producto
     * @return la entidad creada con su ID generado
     */
    ProductoEntity registrarProducto(ProductoEntity producto);

    /**
     * Actualiza un producto existente.
     * 
     * @param producto la entidad con los datos modificados (debe incluir el ID)
     * @return la entidad actualizada
     */
    ProductoEntity actualizarProducto(ProductoEntity producto);

    /**
     * Elimina (o desactiva) un producto por su ID.
     * 
     * @param id el identificador del producto a eliminar
     * @return la entidad eliminada (opcional) o null
     */
    ProductoEntity eliminarProducto(Integer id);
}
