package pe.edu.cibertec.elcortijo.gestionventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.elcortijo.gestionventas.entity.EmpleadoEntity;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Integer> {
    java.util.List<EmpleadoEntity> findByActivoTrue();
    java.util.Optional<EmpleadoEntity> findByDni(String dni);
}
