package pe.edu.cibertec.elcortijo.gestionventas.dto;

public record ClienteDto(
        Integer idCliente,
        String nombre,
        String apellido,
        String dni,
        String telefono,
        String direccion
) {}