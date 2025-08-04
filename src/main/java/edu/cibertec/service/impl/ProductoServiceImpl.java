package edu.cibertec.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.cibertec.entity.ProductoEntity;
import edu.cibertec.repository.ProductoRepository;
import edu.cibertec.service.ProductoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    public List<ProductoEntity> listarProductos() {
        // 1 = activo
        return productoRepository.findByEstado(true);
    }

    @Override
    public ProductoEntity obtenerProducto(Integer id) {
        return productoRepository.findById(id)
                .orElse(null);
    }

    @Override
    public ProductoEntity registrarProducto(ProductoEntity producto) {
        // Inicialmente viene con estado null o 1
        producto.setEstado(true);
        return productoRepository.save(producto);
    }

    @Override
    public ProductoEntity actualizarProducto(ProductoEntity producto) {

        return productoRepository.save(producto);
    }

    @Override
    public ProductoEntity eliminarProducto(Integer id) {
        ProductoEntity prod = obtenerProducto(id);
        if (prod != null) {

            prod.setEstado(false);
            prod = productoRepository.save(prod);
        }
        return prod;
    }
}
