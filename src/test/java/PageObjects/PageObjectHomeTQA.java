package PageObjects;

//IMPORTAR LIBRERIAS
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.itextpdf.text.DocumentException;

import MapObjects.MapObjectHomeTQA;



public class PageObjectHomeTQA extends MapObjectHomeTQA {
	//CREAR CONSTRUCTOR DE LA CLASE
	public PageObjectHomeTQA(WebDriver driver) {
		super(driver);
	}
	//TOMAR URL DE ACCESO
	public void urlAcceso (String url) throws InterruptedException, IOException
	{
		driver.get(url);
	}
	//METODO BUSCAR PRODUCTO
	public void ingresarAlertas(File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException
	{
		//CLICK EN TARJETA ALERTAS
		scroll(0,300,rutaCarpeta,evidencia);
		click(cardAlerts, rutaCarpeta, evidencia);
		tiempoEspera(500);

	}
	
	public void ingresarWidgets(File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException
	{
		//CLICK EN TARJETA ALERTAS
		scroll(0,300,rutaCarpeta,evidencia);
		click(cardWidgets, rutaCarpeta, evidencia);
		tiempoEspera(500);

	}
}