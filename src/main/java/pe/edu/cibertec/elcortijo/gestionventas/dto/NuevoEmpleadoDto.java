package pe.edu.cibertec.elcortijo.gestionventas.dto;

import java.time.LocalDate;

public record NuevoEmpleadoDto(
        String dni,
        String nombre,
        String apellido,
        LocalDate fechaNacimiento,
        String telefono,
        String direccion,
        Integer idRolEmpleado
) {}