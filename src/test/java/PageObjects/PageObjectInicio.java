package PageObjects;

import java.io.File;
//IMPORTAR LIBRERIAS
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.itextpdf.text.DocumentException;

import MapObjects.MapObjectInicioML;


public class PageObjectInicio extends MapObjectInicioML {
	//CREAR CONSTRUCTOR DE LA CLASE
	public PageObjectInicio(WebDriver driver) {	
		super(driver);
	}
	//TOMAR URL DE ACCESO
	public void urlAcceso (String url) throws InterruptedException, IOException
	{
		driver.get(url);
	}
	//METODO BUSCAR PRODUCTO
	public void buscarProducto(String producto, File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException{
		
		//METODO BORRAR BUSQUEDA
		borrar(txtBusqueda, rutaCarpeta, evidencia);
		//METODO INPUT BUSQUEDA
		sendkey(producto, txtBusqueda, rutaCarpeta, evidencia);
		//METODO ENVIAR BUSQUEDA
		submit(txtBusqueda, rutaCarpeta, evidencia);
		//CLICK EN BOTON ACEPTAR COOKIES
		clickValidar(botonCookies, rutaCarpeta, evidencia);
		//METODO CERRAR POPUP UBICACION
		clickValidar(botonCerrar, rutaCarpeta, evidencia);
		//CLICK EN PRIMER PRODUCTO
		click(imgPrimerProducto, rutaCarpeta, evidencia);
		//CLICK EN AÑADIR A CARRITO
		click(botonAnadir, rutaCarpeta, evidencia);
		//CLICK EN CREAR CUENTA
		click(botonCrearCuenta, rutaCarpeta, evidencia);
	}
	
	//METODO CREAR CUENTA DESDE HOME
	public void crearCuentaLink(File rutaCarpeta, String evidencia, int execute) throws InterruptedException, IOException, DocumentException {
		//String nomMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		//CLICK EN BOTON ACEPTAR COOKIES
		clickValidar(botonCookies, rutaCarpeta, evidencia);

		//CLICK EN LINK CREAR CUENTA
		click(linkInicioCrearCuenta, rutaCarpeta, evidencia);
	}
	
	//METODO BUSQUEDA DESDE GOOGLE EXCEL
	public void busquedaInicial(Properties propiedades) throws InterruptedException, IOException
	{
		//OBTENER EL NOMBRE DEL METODO A EJECUTAR
		//CREAR CARPETA PARA ALMACENAMIENTO DE IMAGENES
		
		//ENVIAMOS EL VALOR DE LA BUSQUEDA AL NAVEGADOR
		//sendkey(leer.getCellValue(propiedades.getProperty("filePathExcel"), "busqueda", 0, 1), txtBusquedaGoogle, rutaCarpeta);
		//ENVIAR BUSQUEDA
		//submit(txtBusquedaGoogle, rutaCarpeta);
		//TIEMPO DE ESPERA
		tiempoEspera(5000);
		//CERRAR NAVEGADOR
		driver.close();
	}
	
	
}
