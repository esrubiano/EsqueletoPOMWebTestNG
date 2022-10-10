package ClaseBase;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.itextpdf.text.DocumentException;

import utilidades.GenerarReportePDF;


public class ClaseBase {
	protected static WebDriver driver;
	
	//CONSTRUCTOR DE CLASE
	public ClaseBase(WebDriver driver)
	{
		super();
	}
	
	//METODO DE NAVEGADOR
	public static WebDriver chromeDriverConnection()
	{
		//SETEAR LAS OPCIONES DE NAVEGADOR
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		//SETEAR LAS PROPIEDADES DEL NAVEGADOR
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/driver/chromedriver.exe");
		driver = new ChromeDriver();
		
		//MAXIMIZAR NAVEGADOR
		driver.manage().window().maximize();
		return driver;
	}
	
	//METODO SCROLL
	public void scroll(int x, int y, File rutaCarpeta, String evidencia) throws InterruptedException, IOException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+x+","+y+")", "");
		tiempoEspera(900);
		try {
			captureScreen(rutaCarpeta,null, evidencia);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}	
	
	
	//METODO CLICK
	public void click(By locator, File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException
	{
		captureScreen(rutaCarpeta, locator, evidencia);
		driver.findElement(locator).click();
		tiempoEspera(500);
	}
	//METODO BORRAR
	public void borrar(By locator, File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException
	{
		driver.findElement(locator).clear();
		captureScreen(rutaCarpeta, locator, evidencia);
	}
	
	//METODO CAMBIAR
	public void cambiar(String value, By locator, File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException
	{
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("document.findElement("+locator+").setAttribute('value', '"+value+"')");
		captureScreen(rutaCarpeta, locator, evidencia);
	}
	
	//METODO ENVIAR TEXTO
	public void sendkey(String inputText, By locator, File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException
	{
		driver.findElement(locator).sendKeys(inputText);
		captureScreen(rutaCarpeta, locator, evidencia);
	}
	//METODO ENTER SUBMIT
	public void submit(By locator, File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException
	
	{
		driver.findElement(locator).submit();
		captureScreen(rutaCarpeta, locator, evidencia);
	}
	//METODO TIEMPO DE ESPERA
	public void tiempoEspera(long tiempo) throws InterruptedException
	{
		Thread.sleep(tiempo);
	}
	
	//CAPTURA FECHA DEL SISTEMA
	public static String fechaHora() {
		//TOMAMOS LA FECHA DEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		//DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		//DAR FORMATO A LA FECHA DEL SISTEMA
		String formatFecha = fecha.format(fechaSistema);
		return formatFecha;
	}
	
	//CAPTURA FECHA DEL SISTEMA PARA PDF
	public static String fechaHora2() {
		//TOMAMOS LA FECHA DEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		//DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
		//DAR FORMATO A LA FECHA DEL SISTEMA
		String formatFecha = fecha.format(fechaSistema);
		return formatFecha;
	}
	
	public void eliminarArchivo(String rutaImagen) {
		File fichero = new File (rutaImagen);
		fichero.delete();
	}
	
	//CAPTURAR HORA DEL SISTEMA
	public String HoraSistema() {
		//TOMAMOS LA FECHA DEL SISTEMA
		LocalTime horaSistema = LocalTime.now();
		//DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("HHmmss");
		//DAR FORMATO A LA FECHA DEL SISTEMA
		String hora = fecha.format(horaSistema);
		return hora;
	}
	
	//CAPTURA DE PANTALLA
	public void captureScreen(File rutaCarpeta, By locator, String generarEvidencia) throws IOException, DocumentException, InterruptedException {
		
		if (generarEvidencia.equals("SI")) {
			String hora = HoraSistema();
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(rutaCarpeta+"\\"+hora+".png"));
			String rutaImagen = new File(rutaCarpeta+"\\"+hora+".png").toString();
			//INSTANCIAMOS LA CLASE GENERAR PDF
			GenerarReportePDF informePdf = new GenerarReportePDF();
			//SE PROCEDE A INSERTAR LOCALIZADOR DE IMAGEN EN EL PDF
			if (locator!=null) {
				informePdf.crearBody(locator, rutaImagen);
			}
			
			//ELIMINAR IMAGEN CREADA
			eliminarArchivo(rutaImagen);
		}
		
	}
	
	public void captureScreenError(File rutaCarpeta, By locator, String generarEvidencia, String msnError) throws Exception
    {
        if (generarEvidencia.equals("Si"))
        {
            String hora = HoraSistema();
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(rutaCarpeta+"\\"+hora+".png"));
            String rutaImagen =new File(rutaCarpeta+"\\"+hora+".png").toString();
            
            //INSTACIAMOS LA CLASE GENERAR PDF
            GenerarReportePDF informePdf = new GenerarReportePDF();
            //SE PROCEDE A INSERTAR LOCALIZADOR HE IMAGEN EN EL PDF
            informePdf.crearBodyError(locator,rutaImagen,msnError);
            //ELIMINAR IMAGEN CREADA
            
            eliminarArchivo(rutaImagen);
        }
    }
	
	
	//CREA CARPETA 
	public File crearCarpeta(String outputFile, String nomTest) {
		//ALMACENA LA FECHA DEL SISTEMA
		String fecha = fechaHora();
		//CREA EL NOMBRE DE LA CARPETA
		String nomCarpeta = nomTest+"-"+fecha;
		//OBTIENE RUTA DE ALOJAMIENTO DE SALIDA Y EL NOMBRE DEL TEST A EJECUTAR
		File directorio = new File(outputFile, nomCarpeta);
		//CREA LA CARPETA
		directorio.mkdir();
		return directorio;
	}
	
	//VALIDAR ELEMENTO
	public void clickValidar(By localizador, File rutaCarpeta, String generarEvidencia) {
	    try {
	        driver.findElement(localizador).isEnabled();
	        click(localizador,rutaCarpeta,generarEvidencia);
	    }catch (Exception e){
	    }
	}
	
	
	//CAPTURA FECHA DEL SISTEMA CON RETARDO
	public String fechaTQA(int m, int d) throws Exception, IOException {
		//DEFINIR FORMATO FECHA
		DateTimeFormatter fechaTQA = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		//ALMACENAMOS LA FECHA DEL SISTEMA
		LocalDateTime fechaActual = LocalDateTime.now();
		//LocalDateTime fechaActual = LocalDateTime.of(year, month, day, hour, minute);
		//RESTAMOS LOS VALORES A LA FECHA ACTUAL
		LocalDateTime fechaRestada = fechaActual.minusMonths(m).minusDays(d);
		//LE DAMOS FORMATO A LA FECHA Y LA GUARDAMOS EN UN STRING
		String formatFecha = fechaTQA.format(fechaRestada);
		//DEVUELVE EL STRING
		return formatFecha;
	}
	
	//CAPTURA FECHA DEL SISTEMA CON RETARDO
	public String fechaHoraTQA(int h) {
		//DEFINIR FORMATO FECHA
		//DateTimeFormatter fechaTQA = DateTimeFormatter.ofPattern("dd/MM/yyyy");"MMMM dd, yyyy"
		DateTimeFormatter fechaTQA = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		//value="September 8, 2022 9:47 AM"
		//ALMACENAMOS LA FECHA DEL SISTEMA
		LocalDateTime fechaActual = LocalDateTime.now();
		LocalDateTime fechaRestada = fechaActual.minusDays(h).minusMonths(h).minusYears(h).minusHours(h);
		String formatFecha = fechaTQA.format(fechaRestada);
		//DAR FORMATO A LA FECHA DEL SISTEMA
		return formatFecha;
	}
	
	//ENVIA FECHA
	public void sendFecha( By locator, File rutaCarpeta, String evidencia) throws Exception
	{
		String fecha = fechaTQA(1,1);
		driver.findElement(locator).sendKeys(fecha);
		tiempoEspera(500);
		captureScreen(rutaCarpeta,null,evidencia);
	}
	
	public void sendFechaHora( By locator, File rutaCarpeta, String evidencia) throws InterruptedException, IOException, DocumentException
	{
		String fecha = fechaHoraTQA(1);
		driver.findElement(locator).sendKeys(fecha);
		tiempoEspera(500);
		captureScreen(rutaCarpeta,null,evidencia);
	}
	
	
}