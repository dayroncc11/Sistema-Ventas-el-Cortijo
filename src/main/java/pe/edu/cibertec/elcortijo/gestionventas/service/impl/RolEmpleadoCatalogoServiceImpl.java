package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.RolEmpleadoCatalogoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.RolEmpleadoCatalogoService;

@Service
public class RolEmpleadoCatalogoServiceImpl extends GenericServiceImpl<RolEmpleadoCatalogoEntity, Integer> implements RolEmpleadoCatalogoService {
    public  RolEmpleadoCatalogoServiceImpl(JpaRepository<RolEmpleadoCatalogoEntity, Integer> repository) {
        this.repository = repository;
    }
}
