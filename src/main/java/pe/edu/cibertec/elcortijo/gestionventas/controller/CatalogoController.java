package pe.edu.cibertec.elcortijo.gestionventas.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.elcortijo.gestionventas.entity.FormaPagoCatalogoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.entity.RolEmpleadoCatalogoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.entity.TipoAlimentoCatalogoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.FormaPagoCatalogoService;
import pe.edu.cibertec.elcortijo.gestionventas.service.RolEmpleadoCatalogoService;
import pe.edu.cibertec.elcortijo.gestionventas.service.TipoAlimentoCatalogoService;
import java.util.List;

@RestController
@RequestMapping("/api/catalogos")
@AllArgsConstructor
public class CatalogoController {

    private final TipoAlimentoCatalogoService tipoAlimentoService;
    private final RolEmpleadoCatalogoService rolEmpleadoCatalogoService;
    private final FormaPagoCatalogoService formaPagoService;

    @GetMapping("/tipos-alimento")
    public ResponseEntity<List<TipoAlimentoCatalogoEntity>> listarTiposAlimento() {
        return ResponseEntity.ok(tipoAlimentoService.getAll());
    }

    @GetMapping("/roles-empleado")
    public ResponseEntity<List<RolEmpleadoCatalogoEntity>> listarRoles() {
        return ResponseEntity.ok(rolEmpleadoCatalogoService.getAll());
    }

    @GetMapping("/formas-pago")
    public ResponseEntity<List<FormaPagoCatalogoEntity>> listarFormasPago() {
        return ResponseEntity.ok(formaPagoService.getAll());
    }
}