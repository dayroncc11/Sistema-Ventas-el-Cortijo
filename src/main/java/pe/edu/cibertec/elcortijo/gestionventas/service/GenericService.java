package pe.edu.cibertec.elcortijo.gestionventas.service;

import java.util.List;

public interface GenericService <T, ID> {
    T getById(ID id);
    List<T> getAll();
    void create(T entity);
    void modify(T entity);
    void remove(ID id);
}
