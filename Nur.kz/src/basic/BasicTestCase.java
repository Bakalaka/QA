package basic;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import data.UserData;

public class BasicTestCase {
	public String baseUrl = ("http://music.nur.kz/");
	 protected String searchWord = "Armin";
	 protected static WebDriver driver;
	 public UserData user = new UserData("testpasswordx", "test2_nur");
	 @BeforeTest
	 protected static WebDriver getWebDriver() {
		  if (driver == null) {
		  System.setProperty("webdriver.chrome.driver", "/home/vadim/driver/chromedriver");
		  driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		  }
		  return driver;
		 }
	 
	 @AfterTest
	 public void thearDown() throws Exception {
	     driver.close();
	     driver.quit();
	  } 
	 //Log method
	  public static void log(String message)
	  {
	  Throwable t = new Throwable();
	  StackTraceElement trace[] = t.getStackTrace();
	  if (trace.length > 1)
	  {
	  StackTraceElement element = trace[1];
	  System.out.format("[%s.%s] %s", element.getClassName(), element.getMethodName(), message).println();
	  }
	  else
	  System.out.format("[no info] %s", message);
	  }
	  

	 
 }
