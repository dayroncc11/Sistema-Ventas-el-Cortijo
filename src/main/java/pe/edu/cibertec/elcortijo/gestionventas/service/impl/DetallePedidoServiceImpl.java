package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.DetallePedidoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.DetallePedidoService;

@Service
public class DetallePedidoServiceImpl extends GenericServiceImpl<DetallePedidoEntity, Integer> implements DetallePedidoService {
    public DetallePedidoServiceImpl(JpaRepository<DetallePedidoEntity, Integer> repository) {
        this.repository = repository;
    }
}
