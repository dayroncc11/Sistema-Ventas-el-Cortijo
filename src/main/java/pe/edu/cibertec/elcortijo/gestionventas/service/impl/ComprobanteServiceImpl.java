package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.ComprobanteEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.ComprobanteService;

@Service
public class ComprobanteServiceImpl extends GenericServiceImpl<ComprobanteEntity, Integer> implements ComprobanteService {
    public ComprobanteServiceImpl(JpaRepository<ComprobanteEntity, Integer> repository) {
        this.repository = repository;
    }
}
