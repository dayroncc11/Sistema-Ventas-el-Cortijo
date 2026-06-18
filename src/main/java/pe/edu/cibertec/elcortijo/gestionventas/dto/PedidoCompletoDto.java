package pe.edu.cibertec.elcortijo.gestionventas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record PedidoCompletoDto(
        Integer idPedido,
        LocalDate fecha,
        LocalTime hora,
        String nombreCliente,
        String dniCliente,
        String direccionCliente,
        String nombreVendedor,
        String nombreEstado,
        List<DetallePedidoDto> detalles,
        BigDecimal total
) {}