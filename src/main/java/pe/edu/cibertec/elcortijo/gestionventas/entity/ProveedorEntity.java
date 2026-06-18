package pe.edu.cibertec.elcortijo.gestionventas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedor")
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ruc", unique = true, length = 11)
    private String ruc;

    @Column(name = "direccion", length = 150)
    private String direccion;

    @Column(name = "telefono", length = 15)
    private String telefono;
}