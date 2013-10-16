package content;
/* 1.Check links on header for current page -ON
 * 2.Check social links on page -ON
 * 3.Check tracks on a page -ON
 * 4.Check genres on a page -ON
 * 5.Check artists on a page -ON
 * 6.Check title on a block -ON
 * 7.Check blocks on a page -ON
 * 8.Testing search -ON
 */
import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import basic.BasicTestCase;
import page.ContentPage;

public class KazahMusic extends BasicTestCase {
	private ContentPage contentPage = PageFactory.initElements(getWebDriver(), ContentPage.class);
	String pageUrl = "http://music.nur.kz/audio_kz";
	//Check links on header for current page
	@Test(priority=32,groups={"Header"}, description="Проверяет ссылки на шапке на <<Казахская>>")
	public void CheckHeader () throws Exception {
		driver.get(pageUrl);
			try{
				contentPage.Kazahskaya.click();
				contentPage.Kazahskaya.isDisplayed();
				//music
				contentPage.Music.isDisplayed();
				assertEquals("МУЗЫКА",contentPage.Music.getText());
				//new music
				contentPage.NewMusic.isDisplayed();
				assertEquals("Новинки",contentPage.NewMusic.getText());
				//my play list
				contentPage.PlayList.isDisplayed();
				assertEquals("Мой плейлист",contentPage.PlayList.getText());
				//upload
				contentPage.Upload.isDisplayed();
				assertEquals("Загрузить",contentPage.Upload.getText()); 
				//karaoke
				contentPage.Karaoke.isDisplayed();   
				assertEquals("Караоке",contentPage.Karaoke.getText());
				}

			catch (NoSuchElementException e) {
				String errorUrl = driver.getCurrentUrl();
				log("FAILED: by no such element on page "+errorUrl);
				Reporter.log("FAILED: by no such element on page "+errorUrl);
				throw new NoSuchElementException ();
				}
			catch (AssertionError e) {
				String errorUrl = driver.getCurrentUrl();
				log("FAILED: by assertion error on page "+errorUrl);
				Reporter.log("FAILED: by assertion error on page "+errorUrl);
				throw new AssertionError ();
				}
	 }
	 //Check social links on page
	 @Test(priority=33, description="Проверяет блок соц сетей на <<Казахская>>")
	public void CheckSocialLinks () throws Exception {
		 driver.get(pageUrl);
		 contentPage.checkSocialLinks();
	 }
	 //Check tracks on a page
	@Test(priority=34, description="Проверяет отображение треков на <<Казахская>>")
	public void CheckTracks () throws Exception {
		 driver.get(pageUrl);
		 contentPage.checkTracks(10);
	 }
	 //Check genres on a page
	@Test(priority=35, description="Проверяет отображение жанров на <<Казахская>>")
	public void CheckGenres () throws Exception {
		 driver.get(pageUrl);
		 contentPage.checkGenres(24);
	 }
	 //Check artists on a page
	@Test(priority=36,description="Проверяет отображение артистов на <<Казахская>>")
	public void CheckArtists () throws Exception {
		 driver.get(pageUrl);
		 contentPage.checkArtists(15);
	 }
	 //Check title on a blocks
	@Test(priority =37, description="Проверяет отображение и корректность имен блоков на <<Казахская>>")
	public void CheckTitle () throws Exception {
		  driver.get(pageUrl);
		  	try {
		  		driver.findElement(By.xpath("//h3[contains(text(),'Топ-Исполнители Казахстана')]")).isDisplayed();
		  		driver.findElement(By.xpath("//h3[contains(text(),'Популярные жанры')]")).isDisplayed();
		  		driver.findElement(By.xpath("//h3[contains(text(),'Топ-10 казахских песен за неделю')]")).isDisplayed();
		  		}
			 catch (NoSuchElementException e) {
				   String errorUrl = driver.getCurrentUrl();
				   log("FAILED: by no title on page "+errorUrl);
				   Reporter.log("FAILED: by no title on page "+errorUrl);
				   throw new NoSuchElementException ();
			  }
	  } 
	  //Check blocks on a page
	@Test(priority =38, description="Проверяет отображение блоков на <<Казахская>>")
	public void CheckBlocks () throws Exception {
		  driver.get(pageUrl);
		  	try{
		  		driver.findElement(By.xpath("//*[@class='maincontent']//*[@class='section'][1]")).isDisplayed();//block Pop genres
		  		driver.findElement(By.xpath("//*[@class='maincontent']//*[@class='section'][2]")).isDisplayed();
		  		driver.findElement(By.xpath("//*[@class='maincontent']//*[@class='section'][3]")).isDisplayed();//block top kazah tracks of the weeck
		  		driver.findElement(By.xpath("//*[@class='aside']//*[@class='section']")).isDisplayed();//block top kazah artists
		  		}
			 catch (NoSuchElementException e) {
				   String errorUrl = driver.getCurrentUrl();
				   log("FAILED: by no block on page "+errorUrl);
				   Reporter.log("FAILED: by no block on page "+errorUrl);
				   throw new NoSuchElementException ();
			  }
	  }
	  //Testing search
	@Test(priority=39, description="Тестирует поиск на <<Казахская>>")
	public void Search () throws Exception {
		driver.get(pageUrl);
	 	contentPage.Search(searchWord);  
	 }

}
