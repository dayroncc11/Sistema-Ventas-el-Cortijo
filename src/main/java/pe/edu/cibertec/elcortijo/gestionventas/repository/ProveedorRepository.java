package pe.edu.cibertec.elcortijo.gestionventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.elcortijo.gestionventas.entity.ProveedorEntity;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {

}
