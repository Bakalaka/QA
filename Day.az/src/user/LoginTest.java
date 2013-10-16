package user;

import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.LoginPage;
import basic.BasicTestCase;
public class LoginTest extends BasicTestCase {
	private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
	String  pageUrl ="http://music.day.az/";
	@Test(priority=1, groups={"Login"}, description="Тестируем логин")
	public void LogIn () throws Exception {
	driver.get(pageUrl);
	loginPage.loginAs(user);
	assertTrue(loginPage.isLogedIn());
	}

}

