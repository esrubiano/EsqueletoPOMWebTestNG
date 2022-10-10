package PageObjects;

//IMPORTAR LIBRERIAS
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;

import MapObjects.MapObjectWidgets;

public class PageObjectWidgets extends MapObjectWidgets {
	// CREAR CONSTRUCTOR DE LA CLASE
	public PageObjectWidgets(WebDriver driver) {
		super(driver);
	}

	//TOMAR URL DE ACCESO
	public void urlAcceso (String url) throws InterruptedException, IOException
	{
		driver.get(url);
	}

	// METODO PROBAR ALERTAS
	public void ingresoDatePicker(File rutaCarpeta, String evidencia) throws Exception, IOException
	{
		try {
			scroll(0, 500, rutaCarpeta,evidencia);
			// CLICK EN TARJETA ALERTAS
			click(listBtnDatePicker, rutaCarpeta,evidencia);
			//tiempoEspera(500);
			//SCROLL DOWN PARA MOSTRAR ELEMENTOS
			scroll(0, 200, rutaCarpeta,evidencia);
			
			
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	// METODO SELECT DATE
	public void selectDate(File rutaCarpeta, String evidencia) throws Exception, IOException
	{
		try {
			//CAPTURA PANTALLA
			captureScreen(rutaCarpeta,null,evidencia);
			//CLICK EN PRIMER BOTON ALERTA
			click(inputDate, rutaCarpeta,evidencia);
			//DEFINE STRING PARA SELECCIONAR TODO + RETROCESO
			String delete = Keys.chord(Keys.CONTROL, "a", Keys.DELETE);
			String esc = Keys.chord(Keys.ESCAPE);
			//BORRAR CAMPO FECHA
			sendkey(delete, inputDate, rutaCarpeta,evidencia);
			//ENVIA SENDKEYS CON FECHA RESTADA
			sendFecha(inputDate, rutaCarpeta,evidencia);
			//TIEMPO DE ESPERA
			tiempoEspera(2100);
			sendkey(esc, inputDate, rutaCarpeta,evidencia);
			//CAPTURA PANTALLA
			captureScreen(rutaCarpeta,null,evidencia);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		//TOMA CAPTURA DE PANTALLA
		captureScreen(rutaCarpeta,null,evidencia);
		//TIEMPO DE ESPERA
		tiempoEspera(500);
	}
	

	public void selectDateNTime(File rutaCarpeta, String evidencia)
			throws Exception, IOException {
		//
		// CLICK BOTON ALERTA TIMER
		try {
			//CAPTURA PANTALLA
			captureScreen(rutaCarpeta,null,evidencia);
			//CLICK EN PRIMER BOTON ALERTA
			click(inputDateNTime, rutaCarpeta,evidencia);
			//DEFINE STRING PARA SELECCIONAR TODO + RETROCESO
			String delete = Keys.chord(Keys.CONTROL, "a", Keys.DELETE);
			String esc = Keys.chord(Keys.ESCAPE);
			//BORRAR CAMPO FECHA
			sendkey(delete, inputDateNTime, rutaCarpeta,evidencia);
			//ENVIA SENDKEYS CON FECHA RESTADA
			sendFechaHora(inputDateNTime, rutaCarpeta,evidencia);
			sendkey(esc, inputDateNTime, rutaCarpeta,evidencia);
			//TIEMPO DE ESPERA
			tiempoEspera(5000);
			//CAPTURA PANTALLA
			captureScreen(rutaCarpeta,null,evidencia);
			//value="September 8, 2022 9:47 AM"
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		captureScreen(rutaCarpeta,null,evidencia);
		tiempoEspera(500);

	}

}