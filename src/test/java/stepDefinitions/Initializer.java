package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import core.Base;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Initializer extends Base {

	// Before Hooks
	// After Hooks

	@Before
	public void beforeHooks(Scenario scenario) {

		logger.info("Scenario " + scenario.getName() + " Started ");

		String browser = getBrowser();
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup(); // this is equal to System.setproperty()
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "headless":
			// WebDriverManager.phantomjs().setup();
			// driver = new PhantomJSDriver();
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
			break;
		default:
			WebDriverManager.chromedriver().setup(); // this is equal to System.setproperty()
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(getPageLoadTimeOut(), TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(getImpWait(), TimeUnit.SECONDS);

	}
	
	@After
	public void afterHooks(Scenario scenario) {
		
		tearDown();
		logger.info(scenario.getName()+ "    " + scenario.getStatus());
	}

}
