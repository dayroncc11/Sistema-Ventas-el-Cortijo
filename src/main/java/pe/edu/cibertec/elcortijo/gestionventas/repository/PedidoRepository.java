package pe.edu.cibertec.elcortijo.gestionventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.elcortijo.gestionventas.entity.PedidoEntity;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
    List<PedidoEntity> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
