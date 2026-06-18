package pe.edu.cibertec.elcortijo.gestionventas.dto;

import java.time.LocalDate;

public record EmpleadoFormDto(
        String dni,
        String nombre,
        String apellido,
        String password,
        LocalDate fechaNacimiento,
        String telefono,
        String direccion,
        Integer idRolEmpleado
) {}