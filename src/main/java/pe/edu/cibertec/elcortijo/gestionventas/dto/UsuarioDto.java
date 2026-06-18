package pe.edu.cibertec.elcortijo.gestionventas.dto;

import java.util.List;

public record UsuarioDto(
        String dni,
        String nombre,
        String apellido,
        String nombreCompleto,
        List<String> roles
) {}