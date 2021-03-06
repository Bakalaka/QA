package content;
/*
 * 1.Check links on header for current page -ON
 * 2.Check social links on page
 * 3.Adds and deletes tracks in play list
 */
import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import basic.BasicTestCase;
import page.ContentPage;
public class Playlist extends BasicTestCase {
	private ContentPage contentPage = PageFactory.initElements(getWebDriver(), ContentPage.class);
	String pageUrl = "http://music.nur.kz/user-4130233";
	//Check links on header for current page
	@Test(priority=52,groups={"Playlist"}, description="Проверяет ссылки на шапке на <<Мой плейлист>>")
	public void CheckHeader () throws Exception {
		driver.get(pageUrl);
			try{
				//my play list
				contentPage.PlayList.click();
				contentPage.PlayList.isDisplayed();
				assertEquals("Мой плейлист",contentPage.PlayList.getText());
				//music
				contentPage.Music.isDisplayed();
				assertEquals("МУЗЫКА",contentPage.Music.getText());
				//new music
				contentPage.NewMusic.isDisplayed();
				assertEquals("Новинки",contentPage.NewMusic.getText());
				//kazahs music
				contentPage.Kazahskaya.isDisplayed();
				assertEquals("Казахская",contentPage.Kazahskaya.getText());
				//upload
				contentPage.Upload.isDisplayed();
				assertEquals("Загрузить",contentPage.Upload.getText()); 
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
	@Test(priority=53,groups={"Playlist"}, description="Проверяет блок соц сетей на <<Мой плейлист>>")
	public void CheckSocialLinks () throws Exception {
		 driver.get(pageUrl);
		 contentPage.checkSocialLinks();
	 }
	@Test(priority=54,groups={"Playlist"},description="Проверяет играет ли музыка на <<Мой плейлист>>")
	public void PlayTrack () throws Exception {
		 driver.get(baseUrl);
		 contentPage.AddTrack.click();
		 driver.get(pageUrl);
		 driver.findElement(By.xpath("//*[@class='advanced_playlist']/li/a[2]")).click();
		 	for(int i=0; i<=35; i++) {
		 		String styl = driver.findElement(By.xpath(".//*[@id='progress_slider']/a")).getAttribute("style");   
		 			if(styl.equals("left: 0%;")) {    
		 				Thread.sleep(1000);
		 			}else break;
		 				if(i>=60) { //set how long wait
		 					String errorUrl = driver.getCurrentUrl();
		 					log("FAILED: by track doesnt play on this page "+errorUrl);
		 					Reporter.log("FAILED: by track doesnt play on this page "+errorUrl);
		 					throw new NullPointerException ();
		 					}   
	}
 }
	//Adds and deletes tracks in play list
	@Test(priority = 55,groups={"Playlist"},description="Проверяет добавление и удаление треков на <<Мой плейлист>>")
	public void AddAndDeleteTrack () throws Exception {
		 driver.get(pageUrl);
		 int tracks = driver.findElements(By.xpath("//*[@class='advanced_playlist']/li")).size();
		 	if (tracks>=1){
		 		for (int i=1; i<=tracks; i++) {
		 			Thread.sleep(1000);
		 			driver.findElement(By.xpath("//*[@class='advanced_playlist']/li["+i+"]//*[@class='row']/a[2]")).click();
		 			}	
		 		}
	  AddAndDelTrack(contentPage.Music);
	  AddAndDelTrack(contentPage.NewMusic);
	  AddAndDelTrack(contentPage.Kazahskaya);
	  AddAndDelTrack(contentPage.Karaoke); 
	 }
	 //Method for PlaylistFunc
	public void AddAndDelTrack (WebElement link){
	  (link).click();
	  String TrackTitle = contentPage.FirstTrack.getAttribute("audio_name");// gets title of first track on a selected page, and return it to string
	  contentPage.AddTrack.click();// add "FirstTrack" to play list
	  contentPage.PlayList.click();// go to play list page
	  String PlaylistTrackTitle = contentPage.FirstPlaylistTrack.getAttribute("audio_name");// gets title of first track in  a play list
	  assertEquals(TrackTitle,PlaylistTrackTitle); //compares first track from play list with first track from selected page
	  contentPage.DelTrack.click();// delete added track
	 }
}
