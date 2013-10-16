package page;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class MethodsPage {
	protected WebDriver driver;
	 
	 public MethodsPage(WebDriver driver) {
	  this.driver = driver;
	 }
	 
	 public void type (WebElement webElement, String text ) {
	  webElement.clear();
	  webElement.sendKeys(text);
	  
	 }
	 
	 public boolean isElementPresent(WebElement element) {
	  try{
	  element.isDisplayed();
	  return true;
	  } 
	  catch (NoSuchElementException e) {
	  return false;
	  } 
	 }

}
