package MapObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ClaseBase.ClaseBase;

public class MapObjectCrearCuentaML extends ClaseBase{
	//CONSTRUCTOR DE LA CLASE
	public MapObjectCrearCuentaML(WebDriver driver) {
		super(driver);
	}
	
	//ELEMENTO BOTON CREAR CUENTA
	protected By botonCrearCuenta=By.xpath("//a/span[@class='andes-button__content'][text()='Crear cuenta']");
	//ELEMENTO CHECKBOX TERMINOS Y CONDICIONES
	protected By checkDatos=By.xpath("//div/input[@class='andes-checkbox__input'][@id='terms-and-conds']");
	//ELEMENTO BOTON CONTINUAR
	protected By botonContinuar=By.xpath("//a/span[@class='andes-button__content'][text()='Continuar']");
	//ELEMENTO VALIDAR EMAIL
	protected By botonValidar=By.xpath("//span/button[@class='andes-button hub-item__button andes-button--medium andes-button--loud']");
	//ELEMENTO INPUT EMAIL
	protected By txtEmail=By.xpath("//div/input[@class='andes-form-control__field'][@type='email']");
	
}
