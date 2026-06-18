package pe.edu.cibertec.elcortijo.gestionventas.dto;

public record CambiarPasswordDto(
        String passwordActual,
        String nuevaPassword
) {}