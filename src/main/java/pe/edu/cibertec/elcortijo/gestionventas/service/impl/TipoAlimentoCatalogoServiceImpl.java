package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.TipoAlimentoCatalogoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.TipoAlimentoCatalogoService;

@Service
public class TipoAlimentoCatalogoServiceImpl extends GenericServiceImpl<TipoAlimentoCatalogoEntity, Integer> implements TipoAlimentoCatalogoService {
    public TipoAlimentoCatalogoServiceImpl(JpaRepository<TipoAlimentoCatalogoEntity, Integer> repository) {
        this.repository = repository;
    }
}
