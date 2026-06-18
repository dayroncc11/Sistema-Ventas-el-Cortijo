package pe.edu.cibertec.elcortijo.gestionventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.elcortijo.gestionventas.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
    java.util.List<ProductoEntity> findByActivoTrue();
}
