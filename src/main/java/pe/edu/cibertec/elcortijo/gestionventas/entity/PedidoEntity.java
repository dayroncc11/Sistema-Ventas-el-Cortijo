package pe.edu.cibertec.elcortijo.gestionventas.entity;

        import jakarta.persistence.*;
        import lombok.Data;
        import java.util.List;
        import jakarta.persistence.OneToMany;
        import jakarta.persistence.CascadeType;

        import java.time.LocalDate;
        import java.time.LocalTime;

        @Data
        @Entity
        @Table(name = "pedido")
        public class PedidoEntity {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "id_pedido")
            private Integer idPedido;

            @Column(name = "fecha", nullable = false)
            private LocalDate fecha;

            @Column(name = "hora", nullable = false)
            private LocalTime hora;

            @ManyToOne
            @JoinColumn(name = "id_estado_pedido", nullable = false)
            private EstadoPedidoCatalogoEntity estadoPedido;

            @ManyToOne
            @JoinColumn(name = "id_cliente", nullable = false)
            private ClienteEntity cliente;

            @ManyToOne
            @JoinColumn(name = "id_empleado_vendedor", nullable = false)
            private EmpleadoEntity empleadoVendedor;

            @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
            private ComprobanteEntity comprobante;

            @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
            private java.util.List<DetallePedidoEntity> detalles;
        }