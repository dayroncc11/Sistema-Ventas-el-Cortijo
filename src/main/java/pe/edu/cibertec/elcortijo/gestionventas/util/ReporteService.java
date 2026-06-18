package pe.edu.cibertec.elcortijo.gestionventas.util;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.elcortijo.gestionventas.entity.PedidoEntity;
import pe.edu.cibertec.elcortijo.gestionventas.entity.ProductoEntity;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReporteService {

    public byte[] generarReporteStockPdf(List<ProductoEntity> productos) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, baos);

        document.open();

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
        Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Font fontCabecera = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.WHITE);
        Font fontCuerpo = FontFactory.getFont(FontFactory.HELVETICA, 9, Color.BLACK);

        Paragraph titulo = new Paragraph("Reporte de Stock de Productos", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Paragraph subtitulo = new Paragraph("Generado el: " + LocalDateTime.now().format(formatter), fontSubtitulo);
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        subtitulo.setSpacingAfter(20);

        document.add(titulo);
        document.add(subtitulo);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1f, 4f, 3f, 2f, 2f});
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(52, 71, 103));
        cell.setPadding(5);

        String[] headers = {"ID", "Nombre", "Tipo de Alimento", "Precio", "Stock"};
        for(String header : headers){
            cell.setPhrase(new Phrase(header, fontCabecera));
            table.addCell(cell);
        }

        for (ProductoEntity producto : productos) {
            table.addCell(new Phrase(String.valueOf(producto.getIdProducto()), fontCuerpo));
            table.addCell(new Phrase(producto.getNombre(), fontCuerpo));
            table.addCell(new Phrase(producto.getTipoAlimento().getNombreTipoAlimento(), fontCuerpo));
            table.addCell(new Phrase(String.format("$%.2f", producto.getPrecioUnitario()), fontCuerpo));
            table.addCell(new Phrase(String.valueOf(producto.getStock()), fontCuerpo));
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }

    public byte[] generarReporteVentasPdf(LocalDate fechaInicio, LocalDate fechaFin, List<PedidoEntity> pedidos) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, baos);

        document.open();

        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);
        Font fontCabecera = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, Color.WHITE);
        Font fontCuerpo = FontFactory.getFont(FontFactory.HELVETICA, 8);
        Font fontResumen = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);

        Paragraph titulo = new Paragraph("Reporte de Ventas", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Paragraph periodo = new Paragraph("Período: " + fechaInicio.format(formatter) + " al " + fechaFin.format(formatter), fontSubtitulo);
        periodo.setAlignment(Element.ALIGN_CENTER);
        document.add(periodo);

        Paragraph fechaGeneracion = new Paragraph("Generado el: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), fontSubtitulo);
        fechaGeneracion.setAlignment(Element.ALIGN_CENTER);
        fechaGeneracion.setSpacingAfter(20);
        document.add(fechaGeneracion);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 4f, 3f, 2f, 2f});

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(52, 71, 103));
        cell.setPadding(5);

        String[] headers = {"ID Pedido", "Cliente", "Vendedor", "Fecha", "Total"};
        for(String header : headers){
            cell.setPhrase(new Phrase(header, fontCabecera));
            table.addCell(cell);
        }

        BigDecimal totalVentas = BigDecimal.ZERO;
        for (PedidoEntity pedido : pedidos) {
            table.addCell(new Phrase(String.valueOf(pedido.getIdPedido()), fontCuerpo));
            table.addCell(new Phrase(pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido(), fontCuerpo));
            table.addCell(new Phrase(pedido.getEmpleadoVendedor().getNombre() + " " + pedido.getEmpleadoVendedor().getApellido(), fontCuerpo));
            table.addCell(new Phrase(pedido.getFecha().format(formatter), fontCuerpo));
            BigDecimal totalPedido = pedido.getComprobante() != null ? pedido.getComprobante().getTotal() : BigDecimal.ZERO;
            table.addCell(new Phrase(String.format("$%.2f", totalPedido), fontCuerpo));
            totalVentas = totalVentas.add(totalPedido);
        }
        document.add(table);

        Paragraph resumenTitulo = new Paragraph("\nResumen del Período:", fontResumen);
        resumenTitulo.setSpacingBefore(20);
        document.add(resumenTitulo);

        document.add(new Paragraph("Número de Pedidos: " + pedidos.size(), fontCuerpo));
        document.add(new Paragraph("Monto Total de Ventas: $" + String.format("%.2f", totalVentas), fontCuerpo));

        document.close();
        return baos.toByteArray();
    }
}