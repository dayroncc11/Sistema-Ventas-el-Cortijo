package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.EmpleadoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.repository.EmpleadoRepository;
import pe.edu.cibertec.elcortijo.gestionventas.service.EmpleadoService;

import java.util.List;

@Service
public class EmpleadoServiceImpl extends  GenericServiceImpl<EmpleadoEntity, Integer> implements EmpleadoService {
    public EmpleadoServiceImpl(JpaRepository<EmpleadoEntity, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public List<EmpleadoEntity> listarEmpleadosActivos() {
        return ((EmpleadoRepository) repository).findByActivoTrue();
    }

    @Override
    public void desactivarEmpleado(Integer id) {
        EmpleadoEntity empleado = repository.findById(id).orElse(null);
        if (empleado != null) {
            empleado.setActivo(false);
            repository.save(empleado);
        }
    }
}
