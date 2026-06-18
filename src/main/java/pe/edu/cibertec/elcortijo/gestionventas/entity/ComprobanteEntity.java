package pe.edu.cibertec.elcortijo.gestionventas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "comprobante")
public class ComprobanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprobante")
    private Integer idComprobante;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "id_forma_pago", nullable = false)
    private FormaPagoCatalogoEntity formaPago;

    @OneToOne
    @JoinColumn(name = "id_pedido", nullable = false, unique = true)
    private PedidoEntity pedido;
}