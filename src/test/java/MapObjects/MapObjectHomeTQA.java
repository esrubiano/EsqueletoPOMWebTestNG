package MapObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ClaseBase.ClaseBase;

public class MapObjectHomeTQA extends ClaseBase{
	//CONSTRUCTOR DE LA CLASE
	public MapObjectHomeTQA(WebDriver driver) {
		super(driver);
	}
	
	//ELEMENTO TARJETA ALERTS
	protected By cardAlerts=By.xpath("//*[@class='card mt-4 top-card'][3]");
	protected By cardWidgets=By.xpath("//*[@class='card mt-4 top-card'][4]");
}
