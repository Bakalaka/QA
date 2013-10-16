package content;
/* 1.Check links on header for current page -ON
 * 2.Check social links on page -ON
 * 3.Check tracks on a page -ON
 * 4.Check genres on a page -ON
 * 5.Check artists on a page -ON
 * 6.Check title on a block -ON
 * 7.Check blocks on a page -ON
 * 8.Testing search -ON
 * 9.Check albums on a page -ON
 */
import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.ContentPage;
import basic.BasicTestCase;

public class NewMusic extends BasicTestCase {
 private ContentPage contentPage = PageFactory.initElements(getWebDriver(), ContentPage.class);
 String pageUrl =("http://music.nur.kz/new");
 @Test(priority=20,groups={"Header"},description="Проверяет ссылки на шапке <<Новинки>>")
 public void CheckHeader () throws Exception {
	 driver.get(pageUrl);
	 	try{
	 		//new music
	 		contentPage.NewMusic.click();
	 		contentPage.NewMusic.isDisplayed();
	 		assertEquals("Новинки",contentPage.NewMusic.getText());
	 		//music
	 		contentPage.Music.isDisplayed();
	 		assertEquals("МУЗЫКА",contentPage.Music.getText());
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
	 	catch (AssertionError e) {
	 		String errorUrl = driver.getCurrentUrl();
	 		log("FAILED: by assertion error on page "+errorUrl);
	 		throw new AssertionError ();
    		 }
 }
 //Check social links on page
 @Test(priority=21,description="Проверят отображение блока соц сетей на <<Новинки>>")
 public void CheckSocialLinks () throws Exception {
	 driver.get(pageUrl);
	 contentPage.checkSocialLinks();
 }
 //Check tracks on a page
 @Test(priority=22,description="Проверяет отображение треков на <<>Новинки>")
 public void CheckTracks () throws Exception {
	 driver.get(pageUrl);
	 contentPage.checkTracks(15);
 }
 //Check artists on a page
 @Test(priority=23, description="Проверяет отображение артистов на <<Новинки>>")
 public void CheckArtists () throws Exception {
	 driver.get(pageUrl);
	 contentPage.checkArtists(7);
 }
 //Check title on a block
 @Test(priority=24,description="Проверяет отображение и корректность имен блоков на <<Новинки>>")
 public void CheckTitle () throws Exception {
	 driver.get(pageUrl);
	 driver.findElement(By.xpath("//h3[contains(text(),'Новые популярные исполнители')]")).isDisplayed();
 }
 //Check blocks on a page
 @Test(priority=25, description="Проверяет отображение блоков на <<Новинки>>")
 public void CheckBlocks () throws Exception {
	 driver.get(pageUrl);
	 	try{
	 		driver.findElement(By.xpath("//*[@class='aside']//*[@class='section'][1]")).isDisplayed(); //block new pop artists
	 		driver.findElement(By.xpath("//*[@class='aside']//*[@class='section'][2]")).isDisplayed(); // block new albums
	 		driver.findElement(By.xpath("//*[@class='maincontent']//*[@class='section'][1]")).isDisplayed(); // block new tracks
	 		}
	 	catch (NoSuchElementException e) {
	 		String errorUrl = driver.getCurrentUrl();
	 		log("FAILED: by no block on page "+errorUrl);
	 		throw new NoSuchElementException ();
     		}
 }
 //Search test
 @Test(priority=26, description="Тестируем поиск на <<Новинки>>")
 public void Search () throws Exception {
	 driver.get(pageUrl);
	 contentPage.Search(searchWord);  
 }
 //Check albums
@Test(priority=27,description="Проверяет отображения альбомов на <<Новинки>>")
public void CheckAlbums () throws Exception {
	  driver.get(pageUrl);
	  contentPage.checkAlbums();	
}
}