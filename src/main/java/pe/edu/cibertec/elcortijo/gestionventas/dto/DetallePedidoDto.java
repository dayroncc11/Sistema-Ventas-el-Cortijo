package pe.edu.cibertec.elcortijo.gestionventas.dto;
import java.math.BigDecimal;

public record DetallePedidoDto(
        String nombreProducto,
        Integer cantidad,
        BigDecimal precioUnitarioVenta,
        BigDecimal subtotal
) {}