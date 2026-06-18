package pe.edu.cibertec.elcortijo.gestionventas.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.math.BigDecimal;

    @Data
    @Entity
    @Table(name = "producto", uniqueConstraints = {
            @UniqueConstraint(name = "idx_producto_unico", columnNames = {"nombre", "id_tipo_alimento"})
    })
    public class ProductoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_producto")
        private Integer idProducto;

        @Column(name = "nombre", nullable = false, length = 100)
        private String nombre;

        @ManyToOne
        @JoinColumn(name = "id_tipo_alimento", nullable = false)
        private TipoAlimentoCatalogoEntity tipoAlimento;

        @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
        private BigDecimal precioUnitario;

        @Column(name = "stock", nullable = false)
        private Integer stock;

        @Column(name = "activo", nullable = false)
        private boolean activo = true;

    }