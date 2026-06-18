package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.EmpleadoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.repository.EmpleadoRepository;
import java.util.Collections;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        EmpleadoEntity empleado = empleadoRepository.findByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró empleado con DNI: " + dni));

        return new User(
                empleado.getDni(),
                empleado.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + empleado.getRolEmpleado().getNombreRol().toUpperCase()))
        );
    }
}