package content;
/*
 * 1.Check header on a page - ON
 * 2.Check social links on page -ON
 * 3.Check tracks on a page -OFF
 * 4.Check artists on a page -ON
 * 5.Check title on a block -ON
 * 6.Check blocks -ON
 * 7.Check albums -ON
 */
import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import page.ContentPage;
import basic.BasicTestCase;

 public class NewMusic extends BasicTestCase {
 private ContentPage contentPage = PageFactory.initElements(getWebDriver(), ContentPage.class);
 String pageUrl =("http://music.day.az/new");

 //Check header on a page
 @Test(priority=21, groups={"Header"}, description="Проверяет ссылки на шапке на <<Новинки>>")
 public void NewMusicHeader () throws Exception {
	 get(pageUrl);
	 	try{
	 		//new music
	 		contentPage.NewMusic.click();
	 		contentPage.NewMusic.isDisplayed();
	 		assertEquals("Новинки",contentPage.NewMusic.getText());
	 		//music
	 		contentPage.Music.isDisplayed();
	 		assertEquals("МУЗЫКА",contentPage.Music.getText());
	 		//azer music
	 		contentPage.Azerbaijanskaya.isDisplayed();
	 		assertEquals("Азербайджанская",contentPage.Azerbaijanskaya.getText());
	 		//my play list
	 		contentPage.PlayList.isDisplayed();
	 		assertEquals("Мой плейлист",contentPage.PlayList.getText());
	 		//upload
	 		contentPage.Upload.isDisplayed();
	 		assertEquals("Загрузить",contentPage.Upload.getText()); 
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
 //Check social links on page
 @Test(priority=22, description="Проверяет отображение соц сетей на <<Айзербайджанская>>")
public void SocialLinks () throws Exception {
	 get(pageUrl);
	 contentPage.checkSocialLinks();
}
 //Check tracks on a page
/* @Test(priority=23, description="Проверяет отображение треков на <<Новинки>>")
 public void CheckTracks () throws Exception {
	 get(pageUrl);
	 contentPage.checkTracks(15);
 }*/
 //Check artists on a page
 @Test(priority=24, description="Проверяет отображение артистов на <<Новинки>>")
 public void CheckArtists () throws Exception {
	 get(pageUrl);
	 contentPage.checkArtists(7);
 }
 //Check title on a block
 @Test(priority=26, description="проверяет отображение и корректоность имен блоков на <<Новинки>>")
 public void CheckTitle () throws Exception {
	 get(pageUrl);
	 try {
	 driver.findElement(By.xpath("//h3[contains(text(),'Новые популярные исполнители')]")).isDisplayed();
	 } catch (NoSuchElementException e) {
		    String errorUrl = driver.getCurrentUrl();
	 		log("Is FAILED by no title on page "+errorUrl);
	 		Reporter.log("Is FAILED by no title on page "+errorUrl);
	 		throw new NoSuchElementException (); 
	 }
 }
 //Check blocks
 @Test(priority=27, description="Проверяет отображение блоков на <<Новинки>>")
 public void CheckBlocks () throws Exception {
	 get(pageUrl);
	 	try{
	 		driver.findElement(By.xpath("//*[@class='aside']//*[@class='section'][1]")).isDisplayed(); //block new pop artists
	 		driver.findElement(By.xpath("//*[@class='aside']//*[@class='section'][2]")).isDisplayed(); // block new albums
	 		driver.findElement(By.xpath("//*[@class='maincontent']//*[@class='section'][1]")).isDisplayed(); // block new tracks
	 		}
	 	catch (NoSuchElementException e) {
	 		String errorUrl = driver.getCurrentUrl();
	 		log("Is FAILED by no block on page "+errorUrl);
	 		Reporter.log("Is FAILED by no block on page "+errorUrl);
	 		throw new NoSuchElementException ();
	 		}
 }
 //Check albums
 @Test(priority=28, description="Проверяет отображение альбомов на <<Новинки>>")
 public void CheckAlbums() throws Exception {
	 get(pageUrl);
	 contentPage.checkAlbums();
 }
 }
 