package pe.edu.cibertec.elcortijo.gestionventas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "forma_pago_catalogo")
public class FormaPagoCatalogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forma_pago")
    private Integer idFormaPago;

    @Column(name = "nombre_forma_pago", nullable = false, unique = true, length = 50)
    private String nombreFormaPago;
}