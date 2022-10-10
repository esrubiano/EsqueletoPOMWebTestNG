package utilidades;

import java.io.*;
import java.net.MalformedURLException;

import org.openqa.selenium.By;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ClaseBase.ClaseBase;

public class GenerarReportePDF {
	static String nombre = "Prueba";
	static String fecha;
	static Document documento;
	static FileOutputStream archivo;
	static Paragraph titulo;
	String rutaImagen;
	String hora;
	String horaIni, horaFin;
	
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	
	public void crearPlantilla(String nomTest, File rutaCarpeta) {
		//INSTANCIAR DOCUMENTO
		documento = new Document();
		//TOMAR LA HORA DEL SISTEMA
		hora = ClaseBase.fechaHora();
		horaIni = ClaseBase.fechaHora2();
		
		try {
			//CREAR RUTA Y NOMBRE DEL PDF
			archivo = new FileOutputStream(rutaCarpeta+"\\"+"Reporte-"+nomTest+"-"+hora+".pdf");
			PdfWriter.getInstance(documento, archivo);
			
			//*****CREAR ENCABEZADO
			//UBICACION DE LA IMAGEN
			Image header = Image.getInstance(rutaImagen);
			//TAMAÑO DE LA IMAGEN
			header.scaleToFit(50,50);
			//header.setAlignment(Chunk.ALIGN_CENTER);
			header.setWidthPercentage(100);
			
			//CREAR TITULO DEL PDF
			titulo = new Paragraph (nomTest + "\n\n"+"Fecha inicio:  "+horaIni);
			titulo.setAlignment(1);
			
			//CREAR TABLA DE ENCABEZADO
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			
			PdfPCell pos1 = new PdfPCell(header);
			pos1.setHorizontalAlignment(1);
			pos1.setVerticalAlignment(2);
			
			PdfPCell pos2 = new PdfPCell(titulo);
			pos2.setHorizontalAlignment(1); //0=left, 1=center, 2=right
			pos2.setVerticalAlignment(2);
			
			table.addCell(pos2);
			table.addCell(pos1);
			
			//GENERAR MARGEN
			documento.setMargins(30, 30, 30, 30);
			//ABRIR DOCUMENTO
			documento.open();
			
			//INSERTA LA IMAGEN
			documento.add(table);
			documento.add(Chunk.NEWLINE);
			
		}catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}catch (DocumentException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println("Error al logo: "+e.getMessage());
		}
		
	}
	
	public void crearBody (By locator, String rutaImagen) throws DocumentException, MalformedURLException, IOException {
		//OBTENER EL NOMBRE DEL LOCALIZADOR
		String locator1 = locator.toString();
		
		//DAR FORMATO A LA FUENTE
		Paragraph parrafo = new Paragraph();
		Paragraph nL = new Paragraph("\n");
		parrafo.setAlignment(Chunk.ALIGN_LEFT);
		parrafo.setFont(FontFactory.getFont("Arial",10,Font.NORMAL));
		parrafo.add("Se realiza acción sobre el elemento: "+locator1);
		
		//ADICIONAR MENSAJE AL PDF
		//documento.add(parrafo);
		
		//INSERT IMAGEN
		//UBICACION DE LA IMAGEN
		Image imagen = Image.getInstance(rutaImagen);
		imagen.setBorderColor(BaseColor.BLACK);
		
		//TAMAÑO DE LA IMAGEN
		//imagen.scaleToFit(150, 450);
		imagen.scaleToFit(500, 850);
		//imagen.scaleToFit(700, 1000);
		imagen.setAlignment(Chunk.ALIGN_CENTER);
		//documento.add(imagen);
		
		PdfPTable table = new PdfPTable(1);
	    table.setWidthPercentage(100);
	    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	    documento.add(nL);
	    table.addCell(parrafo);
	    documento.add(nL);
	    table.addCell(imagen);
	    documento.add(nL);
	    documento.add(table);
		
		//INSERTA SALTO DE LINEA
		Paragraph saltoDeLinea = new Paragraph("                                                                                                                                                                                                                                                                                                                                                                                   ");
		documento.add(saltoDeLinea);
		
	}
	
	
	public void crearBodyError(By locator, String rutaImagen, String msnError) throws DocumentException, MalformedURLException, IOException {
		//OBTENER EL NOMBRE DEL LOCALIZADOR
		String locator1 = locator.toString();
		//DAR FORMATO A LA FUENTE
		Paragraph parrafo = new Paragraph();
		parrafo.setAlignment(Chunk.ALIGN_LEFT);
		parrafo.setFont(FontFactory.getFont("Arial",10,Font.NORMAL));
		parrafo.add("Se realiza acción sobre el elemento: "+locator1);
		
		//ADICIONAR MENSAJE AL PDF
		documento.add(parrafo);
		
		//INSERT IMAGEN
		//UBICACION DE LA IMAGEN
		Image imagen = Image.getInstance(rutaImagen);
		//TAMAÑO DE LA IMAGEN
		//imagen.scaleToFit(700,700);
		imagen.scaleToFit(500, 850);
		imagen.setAlignment(Chunk.ALIGN_CENTER);
		documento.add(imagen);
		
		//MENSAJE ERROR
		//DAR FORMATO A LA FUENTE
		Paragraph parrafoError = new Paragraph();
		parrafoError.setAlignment(Chunk.ALIGN_LEFT);
		parrafoError.setFont(FontFactory.getFont("Arial",8,Font.NORMAL,BaseColor.BLACK));
		parrafoError.add("EL MENSAJE DE ERROR: "+"\n"+msnError);
		
		//ADICIONAR MENSAJE AL PDF
		documento.add(parrafoError);
		
	}
	
	
	public void cerrarPlantilla() throws DocumentException {
		documento.add(Chunk.NEWLINE);
		//DAR FORMATO A LA FUENTE
		Paragraph parrafo = new Paragraph();
		parrafo.setAlignment(Chunk.ALIGN_RIGHT);
		parrafo.setFont(FontFactory.getFont("Arial",10,Font.BOLD));
		parrafo.add("Fecha inicio: "+horaIni+"\n");
		
		//ADICIONAL MENSAJE AL PDF
		horaFin = ClaseBase.fechaHora2();
		parrafo.add("Fecha fin:  "+horaFin);
		
		//ADICIONAR MENSAJE AL PDF
		documento.add(parrafo);
		documento.close();
		
	}
}
