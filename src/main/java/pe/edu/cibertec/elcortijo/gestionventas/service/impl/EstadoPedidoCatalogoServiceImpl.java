package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.EstadoPedidoCatalogoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.EstadoPedidoCatalogoService;

@Service
public class EstadoPedidoCatalogoServiceImpl extends GenericServiceImpl<EstadoPedidoCatalogoEntity, Integer> implements EstadoPedidoCatalogoService {
    public EstadoPedidoCatalogoServiceImpl(JpaRepository<EstadoPedidoCatalogoEntity, Integer> repository) {
        this.repository = repository;
    }
}
