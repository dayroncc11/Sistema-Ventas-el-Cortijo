package pe.edu.cibertec.elcortijo.gestionventas.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    @Data
    @Entity
    @Table(name = "empleado")
    public class EmpleadoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_empleado")
        private Integer idEmpleado;

        @Column(name = "dni", nullable = false, unique = true, length = 8)
        private String dni;

        @Column(name = "nombre", nullable = false, length = 50)
        private String nombre;

        @Column(name = "apellido", nullable = false, length = 50)
        private String apellido;

        @Column(name = "fecha_nac")
        private java.time.LocalDate fechaNacimiento;

        @Column(name = "telefono", length = 15)
        private String telefono;

        @Column(name = "direccion", length = 150)
        private String direccion;

        @ManyToOne
        @JoinColumn(name = "id_rol_empleado", nullable = false)
        private RolEmpleadoCatalogoEntity rolEmpleado;

        @Column(name = "activo", nullable = false)
        private boolean activo = true;

        @Column(name = "password", nullable = false)
        private String password;
    }