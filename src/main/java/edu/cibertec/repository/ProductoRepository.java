package edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.swagger.v3.oas.annotations.tags.Tag;
import edu.cibertec.entity.ProductoEntity;

@RepositoryRestResource(path = "productos", collectionResourceRel = "productos")
@Tag(name = "Productos", description = "Repository para operaciones sobre productos de crédito")
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    /**
     * Busca todos los productos por su estado (1 = activo, 0 = inactivo).
     */
    List<ProductoEntity> findByEstado(Boolean estado);
}
