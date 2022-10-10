package MapObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ClaseBase.ClaseBase;

public class MapObjectRegisterMT extends ClaseBase{
	//CONSTRUCTOR DE LA CLASE
	public MapObjectRegisterMT(WebDriver driver) {
		super(driver);
	}
	
	//INPUT FIRST NAME
	protected By txtFirstName=By.xpath("//td/input[@name='firstName']");
	//INPUT LAST NAME
	protected By txtLastName=By.xpath("//td/input[@name='lastName']");
	//INPUT PHONE
	protected By txtPhone=By.xpath("//td/input[@name='phone']");
	//protected String strPhone = driver.findElement(By.xpath("//td/input[@name='phone']")).getText();
	//protected int intPhone = Integer.parseInt(strPhone);
	//INPUT EMAIL
	protected By txtEmails=By.xpath("//td/input[@name='userName']");
	//INPUT ADDRESS
	protected By txtAddress=By.xpath("//td/input[@name='address1']");
	//INPUT CITY
	protected By txtCity=By.xpath("//td/input[@name='city']");
	//INPUT STATE/PROVINCE
	protected By txtState=By.xpath("//td/input[@name='state']");
	//INPUT POSTAL CODE
	protected By txtPostal=By.xpath("//td/input[@name='postalCode']");
	//INPUT COUNTRY
	protected By listCountry=By.xpath("//td/select[@name='country']");
	//INPUT USERNAME
	protected By txtUsername=By.xpath("//td/input[@name='email']");
	//INPUT PASSWORD
	protected By password=By.xpath("//td/input[@name='password']");
	//INPUT CONFIRM PASSWORD
	protected By confirmpass=By.xpath("//td/input[@name='confirmPassword']");
	//INPUT BOTON ENVIAR
	protected By btnEnviar=By.xpath("//td/input[@name='submit']");
	
}
