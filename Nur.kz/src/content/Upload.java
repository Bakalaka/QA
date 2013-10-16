package content;

import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import page.ContentPage;
import basic.BasicTestCase;

public class Upload extends BasicTestCase {
	 private ContentPage contentPage = PageFactory.initElements(getWebDriver(), ContentPage.class);
	 String pageUrl = "http://music.nur.kz/upload";
	 //Check header on a page
	 @Test(priority=41, groups={"Upload"}, description="Проверяет ссылки на шапке на <<Загрузить>>")
	 public void CheckHeader () throws Exception {
		 driver.get(pageUrl);
		 	try{
		 		//upload
		 		contentPage.Upload.click();
		 		contentPage.Upload.isDisplayed();
		 		assertEquals("Загрузить",contentPage.Upload.getText()); 
		 		//new music
		 		contentPage.NewMusic.click();
		 		contentPage.NewMusic.isDisplayed();
		 		assertEquals("Новинки",contentPage.NewMusic.getText());
		 		//music
		 		contentPage.Music.isDisplayed();
		 		assertEquals("МУЗЫКА",contentPage.Music.getText());
		 		//azer music
		 		contentPage.Kazahskaya.isDisplayed();
		 		assertEquals("Азербайджанская",contentPage.Kazahskaya.getText());
		 		//my play list
		 		contentPage.PlayList.isDisplayed();
		 		assertEquals("Мой плейлист",contentPage.PlayList.getText());
		 		}
		 	catch (NoSuchElementException e){
		 		String errorUrl = driver.getCurrentUrl();
		 		log("Is FAILED by no suchelement on page "+errorUrl);
		 		Reporter.log("Is FAILED by no such elemnt on page "+errorUrl);
		 		throw new NoSuchElementException ();		 		
		 	}
		 	catch (AssertionError e) {
		 		String errorUrl = driver.getCurrentUrl();
		 		log("Is FAILED by assertion error on page "+errorUrl);
		 		Reporter.log("Is FAILED by assertion error on page "+errorUrl);
		 		throw new AssertionError ();
	    	   }
	 }
	 @Test(priority=42, groups={"Upload"},description="Проверяет есть ли кнопка *загрузить* на <<Загрузить>>")
	 public void CheckBtn () throws Exception {
		 driver.get(pageUrl);
		 try {
			 driver.findElement(By.xpath("//*[contains(@id,'SWFUpload_0')]")).isDisplayed();
		 }
		 catch (ElementNotVisibleException e) {
			   String errorUrl = driver.getCurrentUrl();
		 		log("FAILED: by element not visible on page "+errorUrl);
		 		Reporter.log("FAILED by element not visible on page "+errorUrl);
		 		throw new ElementNotVisibleException ("");
		 }
	 }
}
