package pe.edu.cibertec.elcortijo.gestionventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.elcortijo.gestionventas.entity.DetallePedidoEntity;

public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, Integer> {

}
