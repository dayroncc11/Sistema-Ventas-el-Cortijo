package pe.edu.cibertec.elcortijo.gestionventas.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.dto.ItemPedidoDto;
import pe.edu.cibertec.elcortijo.gestionventas.dto.NuevoPedidoDto;
import pe.edu.cibertec.elcortijo.gestionventas.entity.*;
import pe.edu.cibertec.elcortijo.gestionventas.repository.*;
import pe.edu.cibertec.elcortijo.gestionventas.service.PedidoService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl extends GenericServiceImpl<PedidoEntity, Integer> implements PedidoService {

    private final EmpleadoRepository empleadoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final FormaPagoCatalogoRepository formaPagoRepository;
    private final EstadoPedidoCatalogoRepository estadoPedidoRepository;
    private final ComprobanteRepository comprobanteRepository;

    public PedidoServiceImpl(PedidoRepository repository, EmpleadoRepository empleadoRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository, FormaPagoCatalogoRepository formaPagoRepository, EstadoPedidoCatalogoRepository estadoPedidoRepository, ComprobanteRepository comprobanteRepository) {
        this.repository = repository;

        this.empleadoRepository = empleadoRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.formaPagoRepository = formaPagoRepository;
        this.estadoPedidoRepository = estadoPedidoRepository;
        this.comprobanteRepository = comprobanteRepository;
    }

    @Override
    @Transactional
    public PedidoEntity crearNuevoPedido(NuevoPedidoDto nuevoPedidoDto, String dniVendedor) {
        EmpleadoEntity vendedor = empleadoRepository.findByDni(dniVendedor)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
        ClienteEntity cliente = clienteRepository.findById(nuevoPedidoDto.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        FormaPagoCatalogoEntity formaPago = formaPagoRepository.findById(nuevoPedidoDto.idFormaPago())
                .orElseThrow(() -> new RuntimeException("Forma de pago no encontrada"));
        EstadoPedidoCatalogoEntity estadoInicial = estadoPedidoRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Estado de pedido inicial no encontrado"));

        PedidoEntity pedido = new PedidoEntity();
        pedido.setFecha(LocalDate.now());
        pedido.setHora(LocalTime.now());
        pedido.setCliente(cliente);
        pedido.setEmpleadoVendedor(vendedor);
        pedido.setEstadoPedido(estadoInicial);
        pedido.setDetalles(new ArrayList<>());

        BigDecimal totalGeneral = BigDecimal.ZERO;

        for (ItemPedidoDto itemDto : nuevoPedidoDto.items()) {
            ProductoEntity producto = productoRepository.findById(itemDto.idProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + itemDto.idProducto()));

            if (producto.getStock() < itemDto.cantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            DetallePedidoEntity detalle = new DetallePedidoEntity();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(itemDto.cantidad());
            detalle.setPrecioUnitarioVenta(producto.getPrecioUnitario());
            BigDecimal subtotal = producto.getPrecioUnitario().multiply(BigDecimal.valueOf(itemDto.cantidad()));
            detalle.setSubtotal(subtotal);

            pedido.getDetalles().add(detalle);
            totalGeneral = totalGeneral.add(subtotal);

            producto.setStock(producto.getStock() - itemDto.cantidad());
            productoRepository.save(producto);
        }

        PedidoEntity pedidoGuardado = repository.save(pedido);

        ComprobanteEntity comprobante = new ComprobanteEntity();
        comprobante.setFecha(LocalDate.now());
        comprobante.setTotal(totalGeneral);

        comprobante.setFormaPago(formaPago);
        comprobante.setPedido(pedidoGuardado);
        comprobanteRepository.save(comprobante);

        return pedidoGuardado;
    }

    @Override
    public List<PedidoEntity> buscarPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return ((PedidoRepository) repository).findByFechaBetween(fechaInicio, fechaFin);
    }
}