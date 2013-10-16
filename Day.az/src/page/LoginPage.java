package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import data.UserData;

public class LoginPage extends MethodsPage{
	@FindBy (linkText = "Выйти") //link "exit"
	 public WebElement linkLogOut;

	 @FindBy (linkText = "Войти") //link "enter"
	 public WebElement linkLogin;
	 @FindBy(name = "login")
	 public WebElement fieldLogin;
	 
	 @FindBy(id = "p1")
	 public WebElement fieldPassword;
	 
	 @FindBy(id = "p2")
	 public WebElement fieldPassword2;
	 
	 @FindBy(css = "fieldset > input.submit")
	 public WebElement buttonSubmit;
	  
	  public LoginPage(WebDriver driver) {
	   super(driver);
	  }
	  
	  public void loginAs (UserData user) {
	  
	   type(fieldLogin, user.login );
	   fieldPassword.click();
	   type(fieldPassword2, user.password);
	   buttonSubmit.click();
	  }
	  
	  public boolean isLogedOut() {
	   if (isElementPresent(linkLogin)) {
	    return true;
	   }
	   else {
	    return false;
	   }
	  }
	  public boolean isLogedIn() {
	   return isElementPresent(linkLogOut);
	  }

}
