package PageObjects;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.itextpdf.text.DocumentException;

import MapObjects.MapObjectCrearCuentaML;

public class PageObjectCrearCuenta extends MapObjectCrearCuentaML {
	//CONSTRUCTOR DE CLASE
	public PageObjectCrearCuenta(WebDriver driver) {
		super(driver);
	}
	
	//METODO CREAR CUENTA DESDE EXCEL
	public void crearCuenta(String email, File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException{
		try {
			//final String nomMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			//CLICK AL CHECKLIST DE DATOS PERSONALES
			click(checkDatos, rutaCarpeta, evidencia);
			//CLICL AL BOTON CONTINUAR
			click(botonContinuar, rutaCarpeta, evidencia);
			//CLICK AL BOTON VALIDAR CORREO
			click(botonValidar, rutaCarpeta, evidencia);
			//BORRAR CAMPO TXT EMAIL
			borrar(txtEmail, rutaCarpeta, evidencia);
			//ENVIAR TXT AL CAMPO EMAIL
			sendkey(email, txtEmail, rutaCarpeta, evidencia);
			//TIEMPO DE ESPERA
			tiempoEspera(4000);
		} catch (InterruptedException e) {
			//IMPRIME MENSAJE
			System.out.println("mensaje");
		}
	}
}
