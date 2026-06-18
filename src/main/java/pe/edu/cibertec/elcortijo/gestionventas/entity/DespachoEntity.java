package pe.edu.cibertec.elcortijo.gestionventas.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.time.LocalDateTime;

    @Data
    @Entity
    @Table(name = "despacho")
    public class DespachoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_despacho")
        private Integer idDespacho;

        @Column(name = "fecha_despacho", nullable = false)
        private LocalDateTime fechaDespacho;

        @ManyToOne
        @JoinColumn(name = "id_empleado_despachador", nullable = false)
        private EmpleadoEntity empleadoDespachador;

        @OneToOne
        @JoinColumn(name = "id_pedido", nullable = false, unique = true)
        private PedidoEntity pedido;

        @Column(name = "observaciones", length = 500)
        private String observaciones;
    }