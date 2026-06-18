package pe.edu.cibertec.elcortijo.gestionventas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record PedidoResumenDto(
        Integer idPedido,
        String nombreCliente,
        LocalDate fecha,
        LocalTime hora,
        String nombreEstado,
        String badgeEstado,
        BigDecimal total
) {}