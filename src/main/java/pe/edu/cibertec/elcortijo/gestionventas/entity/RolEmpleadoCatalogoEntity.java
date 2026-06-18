package pe.edu.cibertec.elcortijo.gestionventas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol_empleado_catalogo")
public class RolEmpleadoCatalogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol_empleado")
    private Integer idRolEmpleado;

    @Column(name = "nombre_rol", nullable = false, unique = true, length = 50)
    private String nombreRol;
}