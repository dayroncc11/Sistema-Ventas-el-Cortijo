package pe.edu.cibertec.elcortijo.gestionventas.service;

import pe.edu.cibertec.elcortijo.gestionventas.entity.EmpleadoEntity;

import java.util.List;

public interface EmpleadoService extends GenericService<EmpleadoEntity, Integer> {
    List<EmpleadoEntity> listarEmpleadosActivos();
    void desactivarEmpleado(Integer id);
}
