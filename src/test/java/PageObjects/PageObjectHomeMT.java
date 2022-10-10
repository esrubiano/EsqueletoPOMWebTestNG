package PageObjects;

//IMPORTAR LIBRERIAS
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;

import com.itextpdf.text.DocumentException;

import MapObjects.MapObjectHomeMT;


public class PageObjectHomeMT extends MapObjectHomeMT {
	//CREAR CONSTRUCTOR DE LA CLASE
	public PageObjectHomeMT(WebDriver driver) {
		super(driver);
	}
	//TOMAR URL DE ACCESO
		public void urlAcceso (String url) throws InterruptedException, IOException
		{
			driver.get(url);
		}
	//METODO BUSCAR PRODUCTO
	public void ingresarRegister(File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException
	{
		//CLICK EN LINK HOME PARA CAPTURAR PANTALLA
		click(linkHome, rutaCarpeta,evidencia);
		//CLICL EN LINK REGISTER
		click(linkRegister, rutaCarpeta,evidencia);
	}
	
}
