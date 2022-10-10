package MapObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ClaseBase.ClaseBase;

public class MapObjectWidgets extends ClaseBase{
	//CONSTRUCTOR DE LA CLASE
	public MapObjectWidgets(WebDriver driver) {
		super(driver);
	}
	
	//ELEMENTO ENLACE REGISTER
	protected By listBtnDatePicker=By.xpath("//li/span[text()[contains(.,'Date Picker')]]");
	protected By inputDate=By.xpath("//div/input[@id='datePickerMonthYearInput']");
	protected By inputDateNTime=By.xpath("//div/input[@id='dateAndTimePickerInput']");
}
