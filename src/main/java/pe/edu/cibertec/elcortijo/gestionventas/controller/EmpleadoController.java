package pe.edu.cibertec.elcortijo.gestionventas.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.elcortijo.gestionventas.dto.EmpleadoDto;
import pe.edu.cibertec.elcortijo.gestionventas.dto.EmpleadoFormDto;
import pe.edu.cibertec.elcortijo.gestionventas.entity.EmpleadoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.entity.RolEmpleadoCatalogoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.EmpleadoService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empleados")
@AllArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<EmpleadoDto>> listarEmpleados() {
        List<EmpleadoEntity> empleados = empleadoService.listarEmpleadosActivos();
        return ResponseEntity.ok(empleados.stream().map(this::convertirAEmpleadoDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoEntity> obtenerEmpleadoPorId(@PathVariable Integer id) {
        EmpleadoEntity empleado = empleadoService.getById(id);
        return empleado != null ? ResponseEntity.ok(empleado) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearEmpleado(@RequestBody EmpleadoFormDto empleadoDto) {
        try {
            if (empleadoDto.password() == null || empleadoDto.password().isEmpty()) {
                return ResponseEntity.badRequest().body("La contraseña es obligatoria para nuevos empleados.");
            }
            EmpleadoEntity empleado = new EmpleadoEntity();
            mapDtoAEntidad(empleadoDto, empleado);
            empleado.setPassword(passwordEncoder.encode(empleadoDto.password()));

            empleadoService.create(empleado);
            return new ResponseEntity<>(convertirAEmpleadoDto(empleado), HttpStatus.CREATED);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El DNI ya se encuentra registrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Integer id, @RequestBody EmpleadoFormDto empleadoDto) {
        try {
            EmpleadoEntity empleadoExistente = empleadoService.getById(id);
            if (empleadoExistente == null) {
                return ResponseEntity.notFound().build();
            }

            mapDtoAEntidad(empleadoDto, empleadoExistente);

            if (empleadoDto.password() != null && !empleadoDto.password().isEmpty()) {
                empleadoExistente.setPassword(passwordEncoder.encode(empleadoDto.password()));
            }

            empleadoService.modify(empleadoExistente);
            return ResponseEntity.ok(convertirAEmpleadoDto(empleadoExistente));
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El DNI ya se encuentra registrado para otro empleado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Integer id) {
        empleadoService.desactivarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    private EmpleadoDto convertirAEmpleadoDto(EmpleadoEntity empleado) {
        return new EmpleadoDto(
                empleado.getIdEmpleado(), empleado.getDni(), empleado.getNombre(),
                empleado.getApellido(), empleado.getFechaNacimiento(), empleado.getTelefono(),
                empleado.getDireccion(), empleado.getRolEmpleado().getNombreRol()
        );
    }

    private void mapDtoAEntidad(EmpleadoFormDto dto, EmpleadoEntity entidad) {
        entidad.setDni(dto.dni());
        entidad.setNombre(dto.nombre());
        entidad.setApellido(dto.apellido());
        entidad.setFechaNacimiento(dto.fechaNacimiento());
        entidad.setTelefono(dto.telefono());
        entidad.setDireccion(dto.direccion());
        RolEmpleadoCatalogoEntity rol = new RolEmpleadoCatalogoEntity();
        rol.setIdRolEmpleado(dto.idRolEmpleado());
        entidad.setRolEmpleado(rol);
    }
}