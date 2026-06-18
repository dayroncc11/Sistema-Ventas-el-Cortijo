package pe.edu.cibertec.elcortijo.gestionventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.elcortijo.gestionventas.entity.DespachoEntity;

public interface DespachoRepository extends JpaRepository<DespachoEntity, Integer> {

}
