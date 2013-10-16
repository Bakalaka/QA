package content;
/* 1.Check links on header for current page -ON
 * 2.Check social links on page -ON
 * 3.Check tracks on a page -ON
 * 4.Check genres on a page -ON
 * 5.Check artists on a page -ON
 * 6.Check title on a block -ON
 * 7.Check blocks on a page -ON
 * 8.Testing search -ON
 * 9.Testing music is playing -OFF
 */
import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import page.ContentPage;
import basic.BasicTestCase;

public class Music extends BasicTestCase{
	private ContentPage contentPage = PageFactory.initElements(getWebDriver(), ContentPage.class);
	String pageUrl = "http://music.nur.kz/";
	//Check links on header for current page
	@Test(priority=2,groups={"Header"},description="Проверяет ссылки на шапке на <<Музыка>>")
	public void CheckHeader () throws Exception {
		driver.get(pageUrl);
			try{
				contentPage.Music.click();
				//music
				contentPage.Music.isDisplayed();
				assertEquals("МУЗЫКА",contentPage.Music.getText());
				//new music
				contentPage.NewMusic.isDisplayed();
				assertEquals("Новинки",contentPage.NewMusic.getText());
				//kazahs music
				contentPage.Kazahskaya.isDisplayed();
				assertEquals("Казахская",contentPage.Kazahskaya.getText());
				//my play list
				contentPage.PlayList.isDisplayed();
				assertEquals("Мой плейлист",contentPage.PlayList.getText());
				//upload
				contentPage.Upload.isDisplayed();
				assertEquals("Загрузить",contentPage.Upload.getText()); 
				}
			  catch (NoSuchElementException e) {
					String errorUrl = driver.getCurrentUrl();
					log("FAILED: by assertion error on page "+errorUrl);
					Reporter.log("FAILED: by assertion error on page "+errorUrl);
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
	@Test(priority=3, description="Проверяет блок соц сетей на <<Музыка>>")
	public void CheckSocialLinks () throws Exception {
		 driver.get(pageUrl);
		 contentPage.checkSocialLinks();
	 }
	 //Check tracks on a page
	@Test(priority=4, description="Проверяет отображение треков на <<Музыка>>")
	public void CheckTracks () throws Exception {
		 driver.get(pageUrl);
		 contentPage.checkTracks(10);
	 }
	 //Check genres on a page
	@Test(priority=5, description="Проверяет отображение жанров на <<Музыка>>")
	public void CheckGenres () throws Exception {
		 driver.get(pageUrl);
		 contentPage.checkGenres(24);
	 }
	 //Check artists on a page
	@Test(priority=6, description="Проверяет отображение артистов на <<Музыка>>")
	public void CheckArtists () throws Exception {
		 driver.get(pageUrl);
		 contentPage.checkArtists(15);
	 }
	 //Check title on a block
	@Test(priority=7,description="Проверяет отображение м корректность имен блоков на <<Музыка>>")
	public void checkTitles () throws Exception {
		 driver.get(pageUrl);
		 	try{
		 		driver.findElement(By.xpath("//h3[contains(text(),'Топ-исполнители')]")).isDisplayed();
		 		driver.findElement(By.xpath("//h3[contains(text(),'Популярные жанры')]")).isDisplayed();
		 		driver.findElement(By.xpath("//h3[contains(text(),'Топ-10 треков за неделю')]")).isDisplayed();
		 		}
		 	catch (NoSuchElementException e) {
		 		String errorUrl = driver.getCurrentUrl();
		 		log("FAILED: by no title on block "+errorUrl);
		 		Reporter.log("FAILED: by no title on block "+errorUrl);
		 		throw new NoSuchElementException ();
		 		}
	  }
	 //Check blocks on a page
	@Test(priority=8, description="Проверяет отображение блоков на <<Музыка>>")
	public void CheckBlocks () throws Exception {
		 driver.get(pageUrl);
		 	try{
		 		driver.findElement(By.xpath("//*[@class='maincontent']//*[@class='section'][1]")).isDisplayed();//block top 10 tracks of the week
		 		driver.findElement(By.xpath("//*[@class='maincontent']//*[@class='section'][2]")).isDisplayed();//block pop genres
		 		driver.findElement(By.xpath("//*[@class='aside']//*[@class='section']")).isDisplayed();//block top artists
	 }
		 	catch (NoSuchElementException e) {
		 		String errorUrl = driver.getCurrentUrl();
		 		log("FAILED: by no block on page "+errorUrl);
		 		Reporter.log("FAILED: by no block on page "+errorUrl);
		 		throw new NoSuchElementException ();
		 		}
	 }
	 //Testing search
	@Test(priority=9, description="Тестирует поиск на <<Музыка>>")
	public void Search () throws Exception {
		 driver.get(pageUrl);
		 contentPage.Search(searchWord);  
	 }
	 //Testing music is playing
	/*@Test(priority=10, description="Проверяет играет ли музыка на <<Музыка>>")
	public void PlayMusic () throws Exception {
	  	driver.get(pageUrl);
	  	obj.playMusic();
	 }*/
}
