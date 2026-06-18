package pe.edu.cibertec.elcortijo.gestionventas.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    @Data
    @Entity
    @Table(name = "cliente")
    public class ClienteEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_cliente")
        private Integer idCliente;

        @Column(name = "nombre", nullable = false, length = 50)
        private String nombre;

        @Column(name = "apellido", nullable = false, length = 50)
        private String apellido;

        @Column(name = "dni", nullable = false, unique = true, length = 8)
        private String dni;

        @Column(name = "telefono", length = 15)
        private String telefono;

        @Column(name = "direccion", length = 150)
        private String direccion;

        @Column(name = "activo", nullable = false)
        private boolean activo = true;
    }