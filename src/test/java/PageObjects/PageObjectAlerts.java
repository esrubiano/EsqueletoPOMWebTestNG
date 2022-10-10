package PageObjects;

//IMPORTAR LIBRERIAS
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.itextpdf.text.DocumentException;

import org.openqa.selenium.Alert;

import MapObjects.MapObjectAlerts;

public class PageObjectAlerts extends MapObjectAlerts {
	// CREAR CONSTRUCTOR DE LA CLASE
	public PageObjectAlerts(WebDriver driver) {
		super(driver);
	}

	// TOMAR URL DE ACCESO
	public void urlAcceso(String url) throws InterruptedException, IOException {
		driver.get(url);
	}

	// METODO PROBAR ALERTAS
	public void ingresoAlertas(File rutaCarpeta, String evidencia) throws Exception, IOException {
		try {
			scroll(0, 200, rutaCarpeta, evidencia);
			// CLICK EN TARJETA ALERTAS
			click(listBtnAlerts, rutaCarpeta, evidencia);
			// tiempoEspera(500);
			// SCROLL DOWN PARA MOSTRAR ELEMENTOS
			scroll(0, 200, rutaCarpeta, evidencia);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// METODO PROBAR ALERTAS
	public void pruebaBotonAlertas(File rutaCarpeta, String evidencia)
			throws IOException, DocumentException, InterruptedException {
		try {

			// CAPTURA PANTALLA
			captureScreen(rutaCarpeta, null, evidencia);
			// CLICK EN PRIMER BOTON ALERTA
			click(btnAlert, rutaCarpeta, evidencia);
			// CAMBIA A LA VENTANA EMERGENTE
			Alert botonAlerta = driver.switchTo().alert();
			// CLICK BOTON ACEPTAR
			botonAlerta.accept();
			// TIEMPO DE ESPERA
			tiempoEspera(500);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// TOMA CAPTURA DE PANTALLA
		captureScreen(rutaCarpeta, null, evidencia);
		// TIEMPO DE ESPERA
		tiempoEspera(500);
	}

	public void pruebaBotonAlertaTimer(File rutaCarpeta, String evidencia)
			throws Exception, DocumentException, IOException {
		//
		// CLICK BOTON ALERTA TIMER
		try {
			click(btnTimer, rutaCarpeta, evidencia);
			tiempoEspera(5100);
			captureScreen(rutaCarpeta, null, evidencia);
			Alert botonAlerta = driver.switchTo().alert();
			botonAlerta.accept();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		captureScreen(rutaCarpeta, null, evidencia);
		tiempoEspera(500);

	}

	public void pruebaBotonAlertaConf(File rutaCarpeta, String evidencia)
			throws Exception, DocumentException, IOException {
		
		try {
			captureScreen(rutaCarpeta, null, evidencia);
			click(btnConfirm, rutaCarpeta, evidencia);
			Alert botonConf = driver.switchTo().alert();
			tiempoEspera(1000);
			botonConf.accept();
			tiempoEspera(2000);
			click(btnConfirm, rutaCarpeta, evidencia);
			botonConf.dismiss();
		} catch (Exception e) {
		
		}
		
	}

	public void pruebaAlertaPrompt(File rutaCarpeta, String evidencia, String nombre)
			throws InterruptedException, IOException, DocumentException {

		// CLICK BOTON ALERTA PROMPT BOX
		try {
			click(btnPrompt, rutaCarpeta, evidencia);
			tiempoEspera(500);
			Alert botonAlerta = driver.switchTo().alert();
			tiempoEspera(1000);
			botonAlerta.sendKeys(nombre);
			tiempoEspera(1000);
			botonAlerta.accept();
			tiempoEspera(1000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		tiempoEspera(3000);
		captureScreen(rutaCarpeta, null, evidencia);
	}
}