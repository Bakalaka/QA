package content;

import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import page.ContentPage;
import basic.BasicTestCase;

public class Playlist extends BasicTestCase {
	 private ContentPage contentPage = PageFactory.initElements(getWebDriver(), ContentPage.class);
	 String pageUrl = "http://music.day.az/user-993963";
   //Check links on header for current page
   @Test(priority = 31, groups={"Playlist"}, description="Проверяет ссылки на шапке <<Мой плейлист>>")
   public void CheckHeader () throws Exception {
		 get(pageUrl);
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
		 		//azer music
		 		contentPage.Azerbaijanskaya.isDisplayed();
		 		assertEquals("Азербайджанская",contentPage.Azerbaijanskaya.getText());
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
	 @Test(priority=32,groups={"Playlist"}, description="Проверяет отображение соц сетей на <<Мой плейлист>>")
	public void SocialLinks () throws Exception {
	  get(pageUrl);
	  contentPage.checkSocialLinks();
}     //Play track
	 
	 @Test(priority=33, groups={"Playlist"}, description="Проверяет играет ли трек на <<Мой плейлист>>")
		public void PlayTrack () throws Exception {
			 driver.get(baseUrl);
			 contentPage.AddTrack.click();
			 get(pageUrl);
			 driver.findElement(By.xpath("//*[@class='advanced_playlist']/li/a[2]")).click();
	          try {
	        	  driver.findElement(By.xpath("//*[@class='advanced_playlist']/li/a[1]")).isDisplayed();
	          } 
	          catch (Exception e) {
	        	  String errorUrl = driver.getCurrentUrl();
				  log("FAILED: by track doesnt olay on this page "+errorUrl);
				  Reporter.log("FAILED: by track doesnt olay on this page "+errorUrl);
				  throw new NullPointerException ();
	          }
			 	for(int i=0; i<=35; i++) {
			 		String styl = driver.findElement(By.xpath(".//*[@id='progress_slider']/a")).getAttribute("style");   
			 			if(styl.equals("left: 0%;")) {    
			 				Thread.sleep(1000);
			 			}else break;
			 				if(i>=60) { //set how long wait
			 					String errorUrl = driver.getCurrentUrl();
			 					log("FAILED: by track doesnt olay on this page "+errorUrl);
			 					Reporter.log("FAILED: by track doesnt olay on this page "+errorUrl);
			 					throw new NullPointerException ();
			 					}   
		}
	 }
		//Clean playlist
		@Test(priority = 34,groups={"Playlist"}, description="Очищает плейлист от треков")
		public void CleanPlaylist () throws Exception {
			 get(pageUrl);
			 int tracks = driver.findElements(By.xpath("//*[@class='advanced_playlist']/li")).size();
			 	if (tracks>=1){
			 		for (int i=1; i<=tracks; i++) {
			 			Thread.sleep(1000);
			 			driver.findElement(By.xpath("//*[@class='advanced_playlist']/li["+i+"]//*[@class='row']/a[2]")).click();
			 			}	
			 		}
		}
		@Test(priority = 35,groups={"Playlist"}, description="Добавляет и удаляет треки в плейлисте")
		public void AddAndDelete () {
			  AddAndDelTrack(contentPage.Music);
			  AddAndDelTrack(contentPage.NewMusic);
			  AddAndDelTrack(contentPage.Azerbaijanskaya);
			 }
		 //Method for PlaylistFunc
		public void AddAndDelTrack (WebElement link){
		  (link).click();
		  String TrackTitle = contentPage.FirstTrack.getAttribute("audio_name");// gets title of first track on a selected page, and return it to string
		  contentPage.AddTrack.click();// add "FirstTrack" to play list
		  contentPage.PlayList.click();// go to play list page
		  String PlaylistTrackTitle = contentPage.FirstPlaylistTrack.getAttribute("audio_name");// gets title of first track in  a play list
		  try {
		  assertEquals(TrackTitle,PlaylistTrackTitle); //compares first track from play list with first track from selected page
		  }
		  catch (AssertionError e) {
			   String errorUrl = driver.getCurrentUrl();
			   log("FAILED: by assertion error on page "+errorUrl);
			   Reporter.log("FAILED: by assertion error on page "+errorUrl);
			   throw new AssertionError ();		 		
			 	}  
		  contentPage.DelTrack.click();// delete added track
		 }
}
