package pe.edu.cibertec.elcortijo.gestionventas.service;

import pe.edu.cibertec.elcortijo.gestionventas.entity.PedidoEntity;

public interface PedidoService extends GenericService<PedidoEntity, Integer> {
    java.util.List<pe.edu.cibertec.elcortijo.gestionventas.entity.PedidoEntity> buscarPorFechas(java.time.LocalDate fechaInicio, java.time.LocalDate fechaFin);
    pe.edu.cibertec.elcortijo.gestionventas.entity.PedidoEntity crearNuevoPedido(pe.edu.cibertec.elcortijo.gestionventas.dto.NuevoPedidoDto nuevoPedidoDto, String dniVendedor);
}
