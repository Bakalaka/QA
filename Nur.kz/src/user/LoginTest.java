package user;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.support.PageFactory;


import page.LoginPage;
import basic.BasicTestCase;

@Listeners(basic.ScreenShotOnFailure.class)
public class LoginTest extends BasicTestCase {
	private LoginPage loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
	String  pageUrl ="http://music.nur.kz/";
	@Test(groups = {"Login"},priority=1)
	public void LogIn () throws Exception {
	driver.get(pageUrl);
	loginPage.loginAs(user);
	assertTrue(loginPage.isLogedIn());
	}

}
