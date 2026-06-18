package pe.edu.cibertec.elcortijo.gestionventas.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.elcortijo.gestionventas.service.PedidoService;
import pe.edu.cibertec.elcortijo.gestionventas.service.ProductoService;
import pe.edu.cibertec.elcortijo.gestionventas.util.ReporteService;
import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/api/reportes")
@AllArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;
    private final ProductoService productoService;
    private final PedidoService pedidoService;

    @GetMapping("/stock-pdf")
    public ResponseEntity<byte[]> generarReporteStockPdf() throws IOException {
        var productosActivos = productoService.listarProductosActivos();
        byte[] pdfBytes = reporteService.generarReporteStockPdf(productosActivos);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "reporte_stock.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    @GetMapping("/ventas-pdf")
    public ResponseEntity<byte[]> generarReporteVentasPdf(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) throws IOException {

        var pedidos = pedidoService.buscarPorFechas(fechaInicio, fechaFin);
        byte[] pdfBytes = reporteService.generarReporteVentasPdf(fechaInicio, fechaFin, pedidos);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "reporte_ventas.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}