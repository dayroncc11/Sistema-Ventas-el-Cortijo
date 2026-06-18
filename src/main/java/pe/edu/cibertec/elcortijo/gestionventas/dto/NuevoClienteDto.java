package pe.edu.cibertec.elcortijo.gestionventas.dto;

public record NuevoClienteDto(
        String nombre,
        String apellido,
        String dni,
        String telefono,
        String direccion
) {}