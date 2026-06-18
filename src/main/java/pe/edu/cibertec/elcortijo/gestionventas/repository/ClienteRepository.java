package pe.edu.cibertec.elcortijo.gestionventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.elcortijo.gestionventas.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    java.util.List<ClienteEntity> findByActivoTrue();
}
