package pe.edu.cibertec.elcortijo.gestionventas.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.elcortijo.gestionventas.dto.ClienteDto;
import pe.edu.cibertec.elcortijo.gestionventas.dto.NuevoClienteDto;
import pe.edu.cibertec.elcortijo.gestionventas.entity.ClienteEntity;
import pe.edu.cibertec.elcortijo.gestionventas.service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        List<ClienteEntity> clientes = clienteService.listarClientesActivos(); // Usamos el nuevo método
        return ResponseEntity.ok(clientes.stream().map(this::convertirAClienteDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerClientePorId(@PathVariable Integer id) {
        ClienteEntity cliente = clienteService.getById(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertirAClienteDto(cliente));
    }

    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody NuevoClienteDto clienteDto) {
        try {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setNombre(clienteDto.nombre());
            cliente.setApellido(clienteDto.apellido());
            cliente.setDni(clienteDto.dni());
            cliente.setTelefono(clienteDto.telefono());
            cliente.setDireccion(clienteDto.direccion());

            clienteService.create(cliente);
            return new ResponseEntity<>(convertirAClienteDto(cliente), HttpStatus.CREATED);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            String mensajeError = "Error: El DNI '" + clienteDto.dni() + "' ya se encuentra registrado.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensajeError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Integer id, @RequestBody NuevoClienteDto clienteDto) {
        try {
            ClienteEntity clienteExistente = clienteService.getById(id);
            if (clienteExistente == null) {
                return ResponseEntity.notFound().build();
            }
            clienteExistente.setNombre(clienteDto.nombre());
            clienteExistente.setApellido(clienteDto.apellido());
            clienteExistente.setDni(clienteDto.dni());
            clienteExistente.setTelefono(clienteDto.telefono());
            clienteExistente.setDireccion(clienteDto.direccion());

            clienteService.modify(clienteExistente);
            return ResponseEntity.ok(convertirAClienteDto(clienteExistente));
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            String mensajeError = "Error: El DNI '" + clienteDto.dni() + "' ya se encuentra registrado para otro cliente.";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(mensajeError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        clienteService.desactivarCliente(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteDto convertirAClienteDto(ClienteEntity cliente) {
        return new ClienteDto(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getTelefono(),
                cliente.getDireccion()
        );
    }
}