package pe.edu.cibertec.elcortijo.gestionventas.service;

import pe.edu.cibertec.elcortijo.gestionventas.entity.ProductoEntity;

public interface ProductoService extends GenericService<ProductoEntity, Integer> {
    java.util.List<ProductoEntity> listarProductosActivos();
    void desactivarProducto(Integer id);
}
