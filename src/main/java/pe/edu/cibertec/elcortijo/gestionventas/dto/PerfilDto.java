package pe.edu.cibertec.elcortijo.gestionventas.dto;

public record PerfilDto(
        String nombreDeUsuario,
        String correoElectronico,
        String nombre,
        String apellido,
        String telefono,
        String direccion
) {}