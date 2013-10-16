package basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
 
 
public class ScreenShotOnFailure extends TestListenerAdapter {
	
	Calendar cal = Calendar.getInstance();
	 SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); 
	 SimpleDateFormat time = new SimpleDateFormat("HH-mm-ss");
     @Override
	 public void onTestFailure(ITestResult result) {
	  	WebDriver driver = BasicTestCase.getWebDriver(); //getting webdriver
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //taking screenshot to temporary directory
		String destDir = ("surefire-reports/html/fail-screenshots/"); //path of new directory of screenshot
		new File(destDir).mkdirs(); //creating this directory
		String destFile = date.format(Calendar.getInstance().getTime())+"/"
		+result.getTestClass().getName()+"_"+result.getName()
		+"_"+time.format(Calendar.getInstance().getTime()) + ".png"; // info for name of screenshot
        //copying screenshot from temporary directory to our created directory (name of dir=name of dest dir+file name) 
        try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.setEscapeHtml(false); // setting escape html to string false
		System.out.println("FAILED: "+result.getTestClass().getName()+" Test:"+result.getName()); //print info about error
		//printing link with image 200x200 to report file
		Reporter.log("<a href=fail-screenshots/"+destFile +">"
		+ "<img src=fail-screenshots/" + destFile + " height='200' width='200'/></a>");
	} 
}