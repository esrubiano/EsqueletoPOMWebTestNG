package MapObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

import ClaseBase.ClaseBase;

public class MapObjectInicioML extends ClaseBase 
{
	//CONSTRUCTOR DE CLASE
	public MapObjectInicioML(WebDriver driver) 
	{
		super(driver);
	}
	
	//ELEMENTOS PAGINA INICIAL
	/*protected By txtBusquedaGoogle=By.name("q");
	protected By btnBuscar=By.name("btnK");*/
	
	//ELEMENTO CUADRO DE BUSQUEDA
	protected By txtBusqueda=By.xpath("//form/input[@class='nav-search-input']");
	//ELEMENTO IMAGEN PRODUCTO
	protected By imgProducto=By.xpath("//div/img[@alt='Xiaomi Redmi Note 11 (Snapdragon) Dual SIM 128 GB azul ocaso 4 GB RAM']");
	//ELEMENTO CERRAR POPUP UBICACION
	protected By botonCerrar=By.xpath("//div/button[@class='andes-tooltip-button-close']");
	//ELEMENTO BOTON ACEPTAR COOKIES
	protected By botonCookies=By.xpath("//div/button[@class='cookie-consent-banner-opt-out__action cookie-consent-banner-opt-out__action--primary cookie-consent-banner-opt-out__action--key-accept']");
	//ELEMENTO BOTON AÑADIR AL CARRITO
	protected By botonAnadir=By.xpath("//div/button[@class='andes-button andes-spinner__icon-base andes-button--quiet'][@type='submit']");
	//ELEMENTO LINK HEADER CREAR CUENTA
	protected By linkInicioCrearCuenta=By.xpath("//nav/a[text()='Crea tu cuenta'][1]");
	//ELEMENTO IMAGEN PRIMER PRODUCTO
	protected By imgPrimerProducto=By.xpath("(//div/img[@class='ui-search-result-image__element shops__image-element'])[1]");
	//ELEMENTO BOTON CREAR CUENTA
	protected By botonCrearCuenta=By.xpath("//a/span[@class='andes-button__content'][text()='Crear cuenta']");
	//ELEMENTO BUSQUEDA GOOGLE
	protected By txtBusquedaGoogle=By.name("q");
}
