package PageObjects;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.itextpdf.text.DocumentException;

import MapObjects.MapObjectRegisterMT;

public class PageObjectRegisterMT extends MapObjectRegisterMT {
	//CONSTRUCTOR DE CLASE
	public PageObjectRegisterMT(WebDriver driver) {
		super(driver);
	}
	
	//METODO CREAR CUENTA DESDE EXCEL
	public void registro(File rutaCarpeta, String evidencia, String field1, String field2,
						String field3, String field4, String field5, String field6, String field7, 
						String field8, String field9, String field10, String field11) 
						throws InterruptedException, IOException, DocumentException{
		try {
			//BORRAR CAMPO
			borrar(txtFirstName, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO FIRST NAME
			sendkey(field1, txtFirstName, rutaCarpeta, evidencia);
			//BORRAR CAMPO
			borrar(txtLastName, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO LAST NAME
			sendkey(field2, txtLastName, rutaCarpeta, evidencia);
			//BORRAR CAMPO
			borrar(txtPhone, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO PHONE
			sendkey(field3, txtPhone, rutaCarpeta, evidencia);
			//BORRAR CAMPO
			borrar(txtEmails, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO EMAIL
			sendkey(field4, txtEmails, rutaCarpeta, evidencia);
			//BORRAR CAMPO
			borrar(txtAddress, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO ADDRESS
			sendkey(field5, txtAddress, rutaCarpeta, evidencia);
			//BORRAR CAMPO
			borrar(txtCity, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO CITY
			sendkey(field6, txtCity, rutaCarpeta, evidencia);
			//BORRAR CAMPO
			borrar(txtState, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO STATE
			sendkey(field7, txtState, rutaCarpeta, evidencia);
			//BORRAR CAMPO
			borrar(txtPostal, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO POSTAL
			sendkey(field8, txtPostal, rutaCarpeta, evidencia);

			//ENVIA TEXTO CAMPO COUNTRY
			click(listCountry, rutaCarpeta, evidencia);
			sendkey(field9, listCountry, rutaCarpeta, evidencia);
			//sendkey(u'\ue007', listCountry, rutaCarpeta);

			//BORRAR CAMPO
			borrar(txtUsername, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO USERNAME
			sendkey(field10, txtUsername, rutaCarpeta, evidencia);
			//BORRAR CAMPO
			borrar(password, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO PASSWORD
			sendkey(field11, password, rutaCarpeta, evidencia);
			//BORRAR CAMPO
			borrar(confirmpass, rutaCarpeta, evidencia);
			//ENVIA TEXTO CAMPO CONFIRM PASSWORD
			sendkey(field11, confirmpass, rutaCarpeta, evidencia);
			
			WebElement enviar = driver.findElement(By.xpath("//td/input[@name='submit']"));
			//WebElement enviar = driver.findElement(btnEnviar);
			Assert.assertEquals(true, enviar.isEnabled()); //Verifies that element is​ disabled​
			System.out.println("Element is enabled – Assert passed");
			
			//CLICK EN BOTON ENVIAR
			click(btnEnviar, rutaCarpeta, evidencia);
			
			WebElement msgReg = driver.findElement(By.xpath("//*[text()[contains(.,'Thank you for registering')]]"));
			String ExpectedText="Thank you for registering. You may now sign-in using the user name and password you've just entered.";
			Assert.assertEquals(ExpectedText, msgReg.getText()); //Verifies that element is​ disabled​
			System.out.println("msgReg text is a expected – Assert passed");
			
			
			//TIEMPO DE ESPERA
			tiempoEspera(4000);

		} catch (InterruptedException e) {
			//IMPRIME MENSAJE
			System.out.println("mensaje");
		}
	}
}
