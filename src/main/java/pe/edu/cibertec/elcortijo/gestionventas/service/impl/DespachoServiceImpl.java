package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.DespachoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.DespachoService;

@Service
public class DespachoServiceImpl extends GenericServiceImpl<DespachoEntity, Integer> implements DespachoService {
    public DespachoServiceImpl(JpaRepository<DespachoEntity, Integer> repository) {
        this.repository = repository;
    }
}
