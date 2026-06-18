package pe.edu.cibertec.elcortijo.gestionventas.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import pe.edu.cibertec.elcortijo.gestionventas.dto.NuevoProductoDto;
import pe.edu.cibertec.elcortijo.gestionventas.dto.ProductoDto;
import pe.edu.cibertec.elcortijo.gestionventas.entity.ProductoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.entity.TipoAlimentoCatalogoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.ProductoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDto>> listarProductos() {
        List<ProductoEntity> productos = productoService.listarProductosActivos();
        List<ProductoDto> productoDtos = productos.stream()
                .map(this::convertirAProductoDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productoDtos);
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody NuevoProductoDto nuevoProductoDto) {
        try {
            ProductoEntity productoEntity = new ProductoEntity();
            productoEntity.setNombre(nuevoProductoDto.nombre());
            productoEntity.setPrecioUnitario(nuevoProductoDto.precioUnitario());
            productoEntity.setStock(nuevoProductoDto.stock());

            TipoAlimentoCatalogoEntity tipoAlimento = new TipoAlimentoCatalogoEntity();
            tipoAlimento.setIdTipoAlimento(nuevoProductoDto.idTipoAlimento());
            productoEntity.setTipoAlimento(tipoAlimento);

            productoService.create(productoEntity);
            return new ResponseEntity<>(convertirAProductoDto(productoEntity), HttpStatus.CREATED);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            String mensajeError = "Error: Ya existe un producto con el mismo nombre y tipo de alimento.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensajeError);
        }
    }

    private ProductoDto convertirAProductoDto(ProductoEntity producto) {
        return new ProductoDto(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getTipoAlimento().getNombreTipoAlimento(),
                producto.getPrecioUnitario(),
                producto.getStock()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        productoService.desactivarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoEntity> obtenerProductoPorId(@PathVariable Integer id) {
        ProductoEntity producto = productoService.getById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Integer id, @RequestBody NuevoProductoDto productoDto) {
        try {
            ProductoEntity productoExistente = productoService.getById(id);
            if (productoExistente == null) {
                return ResponseEntity.notFound().build();
            }

            productoExistente.setNombre(productoDto.nombre());
            productoExistente.setPrecioUnitario(productoDto.precioUnitario());
            productoExistente.setStock(productoDto.stock());

            TipoAlimentoCatalogoEntity tipoAlimento = new TipoAlimentoCatalogoEntity();
            tipoAlimento.setIdTipoAlimento(productoDto.idTipoAlimento());
            productoExistente.setTipoAlimento(tipoAlimento);

            productoService.modify(productoExistente);

            return ResponseEntity.ok(convertirAProductoDto(productoExistente));
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            String mensajeError = "Error: Ya existe otro producto con el mismo nombre y tipo de alimento.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensajeError);
        }
    }
}