package pe.edu.cibertec.elcortijo.gestionventas.dto;

import java.math.BigDecimal;

public record ProductoDto(
        Integer idProducto,
        String nombre,
        String tipoAlimento,
        BigDecimal precioUnitario,
        Integer stock
) {}