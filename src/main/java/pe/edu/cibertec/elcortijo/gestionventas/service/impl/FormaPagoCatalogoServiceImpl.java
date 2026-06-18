package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.FormaPagoCatalogoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.FormaPagoCatalogoService;

@Service
public class FormaPagoCatalogoServiceImpl extends GenericServiceImpl<FormaPagoCatalogoEntity, Integer> implements FormaPagoCatalogoService {
    public FormaPagoCatalogoServiceImpl(JpaRepository<FormaPagoCatalogoEntity, Integer> repository) {
        this.repository = repository;
    }
}
