package MapObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ClaseBase.ClaseBase;

public class MapObjectHomeMT extends ClaseBase{
	//CONSTRUCTOR DE LA CLASE
	public MapObjectHomeMT(WebDriver driver) {
		super(driver);
	}
	
	//ELEMENTO ENLACE REGISTER
	protected By linkRegister=By.xpath("//td/a[@href='register.php']");
	//ELEMENTO ENLACE HOME
	protected By linkHome=By.xpath("(//td/font/a[@href='index.php'])[1]");
}
