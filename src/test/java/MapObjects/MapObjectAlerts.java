package MapObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ClaseBase.ClaseBase;

public class MapObjectAlerts extends ClaseBase{
	//CONSTRUCTOR DE LA CLASE
	public MapObjectAlerts(WebDriver driver) {
		super(driver);
	}
	
	//ELEMENTO ENLACE REGISTER
	protected By listBtnAlerts=By.xpath("//li/span[text()[contains(.,'Alerts')]]");
	protected By btnAlert=By.xpath("//div/button[@id='alertButton']");
	protected By btnTimer=By.xpath("//div/button[@id='timerAlertButton']");
	protected By btnConfirm=By.xpath("//div/button[@id='confirmButton']");
	protected By btnPrompt=By.xpath("//div/button[@id='promtButton']");

}
