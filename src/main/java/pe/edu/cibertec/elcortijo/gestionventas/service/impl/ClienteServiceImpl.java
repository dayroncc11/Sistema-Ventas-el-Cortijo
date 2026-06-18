package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.ClienteEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.ClienteService;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<ClienteEntity, Integer> implements ClienteService {
    public ClienteServiceImpl(JpaRepository<ClienteEntity, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public java.util.List<ClienteEntity> listarClientesActivos() {
        return ((pe.edu.cibertec.elcortijo.gestionventas.repository.ClienteRepository) repository).findByActivoTrue();
    }

    @Override
    public void desactivarCliente(Integer id) {
        ClienteEntity cliente = repository.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setActivo(false);
            repository.save(cliente);
        }
    }
}
