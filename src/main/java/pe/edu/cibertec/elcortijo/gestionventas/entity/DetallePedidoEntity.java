package pe.edu.cibertec.elcortijo.gestionventas.entity;

    import jakarta.persistence.*;
    import lombok.Data;
    import java.math.BigDecimal;

    @Data
    @Entity
    @Table(name = "detalle_pedido")
    public class DetallePedidoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_detalle_pedido")
        private Integer idDetallePedido;

        @ManyToOne
        @JoinColumn(name = "id_pedido", nullable = false)
        private PedidoEntity pedido;

        @ManyToOne
        @JoinColumn(name = "id_producto", nullable = false)
        private ProductoEntity producto;

        @Column(name = "cantidad", nullable = false)
        private Integer cantidad;

        @Column(name = "precio_unitario_venta", nullable = false, precision = 10, scale = 2)
        private BigDecimal precioUnitarioVenta;

        @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
        private BigDecimal subtotal;

    }