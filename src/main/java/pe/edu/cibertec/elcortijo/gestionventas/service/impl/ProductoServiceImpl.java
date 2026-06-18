package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.ProductoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.ProductoService;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<ProductoEntity, Integer> implements ProductoService {
    public ProductoServiceImpl(JpaRepository<ProductoEntity, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public java.util.List<ProductoEntity> listarProductosActivos() {
        return ((pe.edu.cibertec.elcortijo.gestionventas.repository.ProductoRepository) repository).findByActivoTrue();
    }

    @Override
    public void desactivarProducto(Integer id) {
        ProductoEntity producto = repository.findById(id).orElse(null);
        if (producto != null) {
            producto.setActivo(false);
            repository.save(producto);
        }
    }
}
