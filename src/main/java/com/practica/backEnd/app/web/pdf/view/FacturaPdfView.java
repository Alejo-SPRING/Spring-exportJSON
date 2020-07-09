package com.practica.backEnd.app.web.pdf.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.practica.backEnd.app.web.model.entity.UsuarioHasVenta;
import com.practica.backEnd.app.web.model.entity.VentaHasProducto;

@Component("cliente/menu")
public class FacturaPdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<UsuarioHasVenta> factura = (List<UsuarioHasVenta>) model.get("ventas");
		
		PdfPTable table = new PdfPTable(1);
		table.addCell("Datos del cliente");
		table.addCell(factura.get(0).getUsuarioId().getUsuarioNombre() + " " + factura.get(0).getUsuarioId().getUsuarioApellido());
		table.setSpacingAfter(20);
		PdfPTable table2 = new PdfPTable(1);
		table2.addCell("Datos de la factura");
		for(UsuarioHasVenta venta : factura) {
			table2.addCell(venta.getVentaId().getVentaTotal() + " $");
			for(VentaHasProducto producto : venta.getVentaId().getListProductos()) {
				table2.addCell(producto.getProductoId().getProductoNombre());
				table2.addCell(producto.getProductoId().getProductoValor() + " $");
			}
		}
		document.add(table);
		document.add(table2);
	}

}
