package testNG;

import org.testng.annotations.*;

import ClaseBase.ClaseBase;
import PageObjects.PageObjectAlerts;
import PageObjects.PageObjectCrearCuenta;
import PageObjects.PageObjectHomeTQA;
import PageObjects.PageObjectInicio;
import PageObjects.PageObjectWidgets;
import PageObjects.PageObjectHomeMT;
import PageObjects.PageObjectRegisterMT;
import utilidades.ExcelUtilidades;
import utilidades.GenerarReportePDF;
import utilidades.MyScreenRecorder;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.*;

public class RunTestNG {

	// CREACION DE OBJETO TIPO WEBDRIVER
	private WebDriver driver;
	// OBJETO PAGINA PPAL
	PageObjectInicio paginaPppal;
	// OBJETO PAGINA CREAR CUENTA
	PageObjectCrearCuenta paginaCrearCuenta;
	
	// OBJETOS TOOLS QA
	PageObjectHomeTQA homePage;
	PageObjectAlerts alertsPage;
	PageObjectWidgets widgetsPage;
	
	PageObjectHomeMT homePageMT;
	PageObjectRegisterMT registerPageMT;
		
	ClaseBase claseBase;
	GenerarReportePDF generarReportePDF;
	String outputFilePath;
	int execute=0;
	
	String logoML, logoTQA, logoMerT;
	String [][] fieldMT = null;

	@BeforeClass
	@Parameters({ "rutaImagenPeriferia", "rutaImagenML", "rutaImagenTQA","rutaImagenMerT","outputFile" })
	public void beforeClass(@Optional("default") String rutaImagenPeriferia,
							@Optional("default") String rutaImagenML,
							@Optional("default") String rutaImagenTQA,
							@Optional("default") String rutaImagenMerT,
							@Optional("default") String outputFile)
			throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "/driver/chromedriver.exe");

		// INSTANCIAR CLASE BASE
		claseBase = new ClaseBase(driver);

		// INSTANCIAR LA CLASES PAGEOBJECTS
		paginaPppal = new PageObjectInicio(driver);

		// ASIGNAMOS LAS OPCIONES Y LA CONFIGURACION DE NAVEGADOR
		// A LA VARIABLE DRIVER
		driver = ClaseBase.chromeDriverConnection();

		// INSTANCIAR CLASE PG OBJ CREAR CUENTA
		paginaCrearCuenta = new PageObjectCrearCuenta(driver);

		// INSTANCIAR LA CLASE REPORTES
		generarReportePDF = new GenerarReportePDF();
		
		
		//LOGO A EJECUTAR
		logoML= rutaImagenML;
		logoTQA= rutaImagenTQA;
		logoMerT= rutaImagenMerT;
		outputFilePath = outputFile;
		
		
		// INSTANCIAR PAGEOBJECTS TOOLSQA
		homePage = new PageObjectHomeTQA(driver);
		alertsPage = new PageObjectAlerts(driver);
		widgetsPage = new PageObjectWidgets(driver);
		
		// INSTANCIAR LA CLASES PAGEOBJECTS
		homePageMT = new PageObjectHomeMT(driver);
		registerPageMT = new PageObjectRegisterMT(driver);

	}

	@DataProvider(name = "datosMercadoLibre1")
	public Object[][] data1() {
		Object[][] arreglo = ExcelUtilidades.getTableArray("./data/prueba.xlsx", "MLSeleccionarP");
		return arreglo;
	}
	
	@DataProvider(name = "datosMercadoLibre2")
	public Object[][] data2() {
		Object[][] arreglo = ExcelUtilidades.getTableArray("./data/prueba.xlsx", "MLCrearCta");
		return arreglo;
	}
	
	@DataProvider(name = "dataAlertsTQA")
	public Object[][] data3() {
		Object[][] arreglo = ExcelUtilidades.getTableArray("./data/prueba.xlsx", "AlertsTQA");
		return arreglo;
	}	
	
	@DataProvider(name = "dataWidgetsTQA")
	public Object[][] data4() {
		Object[][] arreglo = ExcelUtilidades.getTableArray("./data/prueba.xlsx", "WidgetsTQA");
		return arreglo;
	}	
	
	
	@DataProvider(name = "dataMercuryTours")
	public Object[][] data5() {
		Object[][] arreglo = ExcelUtilidades.getTableArray("./data/prueba.xlsx", "MercuryTours");
		System.out.println(arreglo[0][2]);
		return arreglo;
	}
	
	@Test(priority = 3, dataProvider = "datosMercadoLibre1")
	public void seleccionaProducto(String ejecutar, String evidencia, String producto, String email, String url)
			throws Exception {

		if (ejecutar.equals("SI")) {
			try {
				// OBTENER EL NOMBRE DEL METODO A EJECUTAR
				String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
				// CREAR CARPETA PARA ALMACENAMIENTO DE IMAGENES
				File rutaCarpeta = claseBase.crearCarpeta(outputFilePath, nomTest);
				if (evidencia.contains("SI")) {
					//ESTABLECE RUTA IMAGEN
					generarReportePDF.setRutaImagen(logoML);
					// INICIA GRABACION
					MyScreenRecorder.startRecording(nomTest, rutaCarpeta);
					// INICIA CREACION DE REPORTE PDF
					generarReportePDF.crearPlantilla(nomTest, rutaCarpeta);
					// ACCEDE A LA URL
					paginaPppal.urlAcceso(url);
					// ACCEDER AL METODO DE BUSCAR PRODUCTO
					paginaPppal.buscarProducto(producto, rutaCarpeta, evidencia);
					// ACCEDE AL METODO DE CREAR CUENTA
					paginaCrearCuenta.crearCuenta(email, rutaCarpeta, evidencia);
					// FINALIZA LA GRABACIÓN DEL VIDEO
					MyScreenRecorder.stopRecording();
					// FINALIZA CREACION DE REPORTE PDF
					generarReportePDF.cerrarPlantilla();
				}else {
					// ACCEDE A LA URL
					paginaPppal.urlAcceso(url);
					// ACCEDER AL METODO DE BUSCAR PRODUCTO
					paginaPppal.buscarProducto(producto, rutaCarpeta, evidencia);
					// ACCEDE AL METODO DE CREAR CUENTA
					paginaCrearCuenta.crearCuenta(email, rutaCarpeta, evidencia);
				}
				execute=1;

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			// OBTENER EL NOMBRE DEL METODO A EJECUTAR
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("EL TEST"+nomTest+" NO SE VA A EJECUTAR");
		}

	}
	
	
	@Test(priority=4,dataProvider = "datosMercadoLibre2")
	public void crearCuentaDesdeInicio(String ejecutar, String evidencia, String email, String url)
			throws Exception {

		if (ejecutar.equals("SI")) {
			try {
				// OBTENER EL NOMBRE DEL METODO A EJECUTAR
				String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
				// CREAR CARPETA PARA ALMACENAMIENTO DE IMAGENES
				File rutaCarpeta = claseBase.crearCarpeta(outputFilePath, nomTest);
				
				if (evidencia.contains("SI")) {
					//ESTABLECE RUTA IMAGEN
					generarReportePDF.setRutaImagen(logoML);
					// INICIA GRABACION
					MyScreenRecorder.startRecording(nomTest, rutaCarpeta);
					// INICIA CREACION DE REPORTE PDF
					generarReportePDF.crearPlantilla(nomTest, rutaCarpeta);
					// ACCEDE A LA URL
					paginaPppal.urlAcceso(url);
					//ACCEDER AL METODO DE LA PRUEBA INICIAL
					paginaPppal.crearCuentaLink(rutaCarpeta, evidencia, execute);
					//ACCEDE AL METODO DE CREAR CUENTA
					paginaCrearCuenta.crearCuenta(email, rutaCarpeta, evidencia);
					// FINALIZA LA GRABACIÓN DEL VIDEO
					MyScreenRecorder.stopRecording();
					// FINALIZA CREACION DE REPORTE PDF
					generarReportePDF.cerrarPlantilla();
					
				}else {
					// ACCEDE A LA URL
					paginaPppal.urlAcceso(url);
					//ACCEDER AL METODO DE LA PRUEBA INICIAL
					paginaPppal.crearCuentaLink(rutaCarpeta, evidencia, execute);
					//ACCEDE AL METODO DE CREAR CUENTA
					paginaCrearCuenta.crearCuenta(email, rutaCarpeta, evidencia);
				}
				execute=2;

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			// OBTENER EL NOMBRE DEL METODO A EJECUTAR
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("EL TEST"+nomTest+" NO SE VA A EJECUTAR");
		}

	}
	
	@Test(priority=1 ,dataProvider = "dataAlertsTQA")
	public void alertsTQA(String ejecutar, String evidencia, String url, String nombre)
			throws Exception {

		if (ejecutar.equals("SI")) {
			try {
				// OBTENER EL NOMBRE DEL METODO A EJECUTAR
				String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
				// CREAR CARPETA PARA ALMACENAMIENTO DE IMAGENES
				File rutaCarpeta = claseBase.crearCarpeta(outputFilePath, nomTest);
				
				if (evidencia.contains("SI")) {
					//ESTABLECE RUTA IMAGEN
					generarReportePDF.setRutaImagen(logoTQA);
					// INICIA GRABACION
					MyScreenRecorder.startRecording(nomTest, rutaCarpeta);
					// INICIA CREACION DE REPORTE PDF
					generarReportePDF.crearPlantilla(nomTest, rutaCarpeta);
					
					// ACCEDE A LA URL
					homePage.urlAcceso(url);
					// ACCEDER AL METODO DE INGRESAR A PESTAÑA ALERTAS
					homePage.ingresarAlertas(rutaCarpeta,evidencia);
					alertsPage.ingresoAlertas(rutaCarpeta,evidencia);
					alertsPage.pruebaBotonAlertas(rutaCarpeta,evidencia);
					alertsPage.pruebaBotonAlertaTimer(rutaCarpeta,evidencia);
					alertsPage.pruebaBotonAlertaConf(rutaCarpeta,evidencia);
					alertsPage.pruebaAlertaPrompt(rutaCarpeta,evidencia,nombre);
					
					// FINALIZA LA GRABACIÓN DEL VIDEO
					MyScreenRecorder.stopRecording();
					// FINALIZA CREACION DE REPORTE PDF
					generarReportePDF.cerrarPlantilla();
					
				}else {
					// ACCEDE A LA URL
					homePage.urlAcceso(url);
					// ACCEDER AL METODO DE INGRESAR A PESTAÑA ALERTAS
					homePage.ingresarAlertas(rutaCarpeta,evidencia);
					alertsPage.ingresoAlertas(rutaCarpeta,evidencia);
					alertsPage.pruebaBotonAlertas(rutaCarpeta,evidencia);
					alertsPage.pruebaBotonAlertaTimer(rutaCarpeta,evidencia);
					alertsPage.pruebaBotonAlertaConf(rutaCarpeta,evidencia);
					alertsPage.pruebaAlertaPrompt(rutaCarpeta,evidencia,nombre);
				}
				execute=3;

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			// OBTENER EL NOMBRE DEL METODO A EJECUTAR
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("EL TEST"+nomTest+" NO SE VA A EJECUTAR");
		}

	}
	
	@Test(priority = 2 ,dataProvider = "dataWidgetsTQA")
	public void widgetsTQA(String ejecutar, String evidencia, String url)
			throws Exception {

		if (ejecutar.equals("SI")) {
			try {
				// OBTENER EL NOMBRE DEL METODO A EJECUTAR
				String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
				// CREAR CARPETA PARA ALMACENAMIENTO DE IMAGENES
				File rutaCarpeta = claseBase.crearCarpeta(outputFilePath, nomTest);
				
				if (evidencia.contains("SI")) {
					//ESTABLECE RUTA IMAGEN
					generarReportePDF.setRutaImagen(logoTQA);
					// INICIA GRABACION
					MyScreenRecorder.startRecording(nomTest, rutaCarpeta);
					// INICIA CREACION DE REPORTE PDF
					generarReportePDF.crearPlantilla(nomTest, rutaCarpeta);
					
					// ACCEDE A LA URL
					homePage.urlAcceso(url);
					// ACCEDER AL METODO DE BUSCAR PRODUCTO
					homePage.ingresarWidgets(rutaCarpeta,evidencia);
					widgetsPage.ingresoDatePicker(rutaCarpeta,evidencia);
					widgetsPage.selectDate(rutaCarpeta,evidencia);
					widgetsPage.selectDateNTime(rutaCarpeta,evidencia);
					
					// FINALIZA LA GRABACIÓN DEL VIDEO
					MyScreenRecorder.stopRecording();
					// FINALIZA CREACION DE REPORTE PDF
					generarReportePDF.cerrarPlantilla();
					
				}else {
					// ACCEDE A LA URL
					homePage.urlAcceso(url);
					// ACCEDER AL METODO DE BUSCAR PRODUCTO
					homePage.ingresarWidgets(rutaCarpeta,evidencia);
					widgetsPage.ingresoDatePicker(rutaCarpeta,evidencia);
					widgetsPage.selectDate(rutaCarpeta,evidencia);
					widgetsPage.selectDateNTime(rutaCarpeta,evidencia);
				}
				execute=4;

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			// OBTENER EL NOMBRE DEL METODO A EJECUTAR
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("EL TEST "+nomTest+" NO SE VA A EJECUTAR");
		}

	}
	
	
	@Test(priority = 5, dataProvider = "dataMercuryTours")
	//public void registroMercuryTours(Object array)
	//public void registroMercuryTours(String ejecutar, String evidencia, String url, String[][] array)
	public void registroMercuryTours(String ejecutar, String evidencia, String url, String field1, String field2,
									String field3, String field4, String field5, String field6, String field7, 
									String field8, String field9, String field10, String field11)
			throws Exception {		
		/*java.lang.String ejecutar = array[0][0];
		java.lang.String evidencia = array[0][1];
		java.lang.String url = array[0][2];
		java.lang.String field = array[0][3];*/
		if (ejecutar.equals("SI")) {
			try {
				
				//Object[][] arregloMT = ExcelUtilidades.getTableArray("./data/prueba.xlsx", "MercuryTours");
				//String fieldMT[][] = String.valueOf(arregloMT[][]);
				
				// OBTENER EL NOMBRE DEL METODO A EJECUTAR
				String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
				// CREAR CARPETA PARA ALMACENAMIENTO DE IMAGENES
				File rutaCarpeta = claseBase.crearCarpeta(outputFilePath, nomTest);
				if (evidencia.contains("SI")) {
					//ESTABLECE RUTA IMAGEN
					generarReportePDF.setRutaImagen(logoMerT);
					// INICIA GRABACION
					MyScreenRecorder.startRecording(nomTest, rutaCarpeta);
					// INICIA CREACION DE REPORTE PDF
					generarReportePDF.crearPlantilla(nomTest, rutaCarpeta);
					// ACCEDE A LA URL
					homePageMT.urlAcceso(url);
					// ACCEDER AL METODO DE LA PAGINA PRINCIPAL
					homePageMT.ingresarRegister(rutaCarpeta,evidencia);
					//ACCEDER AL METODO DE REGISTER
					registerPageMT.registro(rutaCarpeta,evidencia,field1,field2,field3,field4,field5,field6,field7,field8,field9,field10,field11);				
					// FINALIZA LA GRABACIÓN DEL VIDEO
					MyScreenRecorder.stopRecording();
					// FINALIZA CREACION DE REPORTE PDF
					generarReportePDF.cerrarPlantilla();
				}else {
					// ACCEDE A LA URL
					homePageMT.urlAcceso(url);
					// ACCEDER AL METODO DE LA PAGINA PRINCIPAL
					homePageMT.ingresarRegister(rutaCarpeta,evidencia);
					//ACCEDER AL METODO DE REGISTER
					registerPageMT.registro(rutaCarpeta,evidencia,field1,field2,field3,field4,field5,field6,field7,field8,field9,field10,field11);
				}
				execute=1;

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			// OBTENER EL NOMBRE DEL METODO A EJECUTAR
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
			System.out.println("EL TEST"+nomTest+" NO SE VA A EJECUTAR");
		}

	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
