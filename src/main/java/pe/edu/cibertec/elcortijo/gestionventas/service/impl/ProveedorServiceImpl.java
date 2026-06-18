package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.ProveedorEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.ProveedorService;

@Service
public class ProveedorServiceImpl extends GenericServiceImpl<ProveedorEntity, Integer> implements ProveedorService {
    public ProveedorServiceImpl(JpaRepository<ProveedorEntity, Integer> repository) {
        this.repository = repository;
    }
}
