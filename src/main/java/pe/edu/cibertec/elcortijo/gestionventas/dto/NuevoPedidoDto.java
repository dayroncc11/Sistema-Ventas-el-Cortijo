package pe.edu.cibertec.elcortijo.gestionventas.dto;

import java.util.List;

public record NuevoPedidoDto(
        Integer idCliente,
        Integer idFormaPago,
        List<ItemPedidoDto> items
) {}