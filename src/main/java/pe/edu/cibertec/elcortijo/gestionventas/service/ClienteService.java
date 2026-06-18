package pe.edu.cibertec.elcortijo.gestionventas.service;

import pe.edu.cibertec.elcortijo.gestionventas.entity.ClienteEntity;

public interface ClienteService extends GenericService<ClienteEntity, Integer> {
    java.util.List<ClienteEntity> listarClientesActivos();
    void desactivarCliente(Integer id);
}
