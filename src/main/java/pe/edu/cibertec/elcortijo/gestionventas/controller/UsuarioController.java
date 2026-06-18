package pe.edu.cibertec.elcortijo.gestionventas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.elcortijo.gestionventas.dto.ActualizarPerfilDto;
import pe.edu.cibertec.elcortijo.gestionventas.dto.CambiarPasswordDto;
import pe.edu.cibertec.elcortijo.gestionventas.dto.PerfilDto;
import pe.edu.cibertec.elcortijo.gestionventas.dto.UsuarioDto;
import pe.edu.cibertec.elcortijo.gestionventas.entity.EmpleadoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.repository.EmpleadoRepository;
import pe.edu.cibertec.elcortijo.gestionventas.service.EmpleadoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoService empleadoService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(EmpleadoRepository empleadoRepository, EmpleadoService empleadoService, PasswordEncoder passwordEncoder) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoService = empleadoService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/actual")
    public ResponseEntity<UsuarioDto> obtenerUsuarioActual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String dni = authentication.getName();

        EmpleadoEntity empleado = empleadoRepository.findByDni(dni).orElse(null);
        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        UsuarioDto usuarioDto = new UsuarioDto(
                empleado.getDni(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getNombre() + " " + empleado.getApellido(),
                roles
        );

        return ResponseEntity.ok(usuarioDto);
    }

    @GetMapping("/perfil")
    public ResponseEntity<PerfilDto> obtenerDatosPerfil() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmpleadoEntity empleado = empleadoRepository.findByDni(authentication.getName()).orElse(null);
        if (empleado == null) return ResponseEntity.notFound().build();

        PerfilDto perfilDto = new PerfilDto(
                empleado.getDni(),
                empleado.getDni() + "@elcortijo.com",
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getTelefono(),
                empleado.getDireccion()
        );
        return ResponseEntity.ok(perfilDto);
    }

    @PutMapping("/perfil")
    public ResponseEntity<Void> actualizarPerfil(@RequestBody ActualizarPerfilDto perfilDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmpleadoEntity empleado = empleadoRepository.findByDni(authentication.getName()).orElse(null);
        if (empleado == null) return ResponseEntity.notFound().build();

        empleado.setNombre(perfilDto.nombre());
        empleado.setApellido(perfilDto.apellido());
        empleado.setTelefono(perfilDto.telefono());
        empleado.setDireccion(perfilDto.direccion());

        empleadoService.modify(empleado);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/perfil/cambiar-password")
    public ResponseEntity<String> cambiarPassword(@RequestBody CambiarPasswordDto passwordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmpleadoEntity empleado = empleadoRepository.findByDni(authentication.getName()).orElse(null);
        if (empleado == null) return ResponseEntity.notFound().build();

        if (!passwordEncoder.matches(passwordDto.passwordActual(), empleado.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña actual es incorrecta.");
        }

        empleado.setPassword(passwordEncoder.encode(passwordDto.nuevaPassword()));
        empleadoService.modify(empleado);

        return ResponseEntity.ok("Contraseña actualizada exitosamente.");
    }

}