package pe.edu.cibertec.elcortijo.gestionventas.dto;

import java.math.BigDecimal;

public record NuevoProductoDto(
        String nombre,
        Integer idTipoAlimento,
        BigDecimal precioUnitario,
        Integer stock
) {}