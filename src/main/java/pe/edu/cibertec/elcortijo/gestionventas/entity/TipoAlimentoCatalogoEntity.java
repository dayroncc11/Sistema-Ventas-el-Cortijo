package pe.edu.cibertec.elcortijo.gestionventas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_alimento_catalogo")
public class TipoAlimentoCatalogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_alimento")
    private Integer idTipoAlimento;

    @Column(name = "nombre_tipo_alimento", nullable = false, unique = true, length = 100)
    private String nombreTipoAlimento;
}