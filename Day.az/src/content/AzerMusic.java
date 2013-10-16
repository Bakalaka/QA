package content;
/*
 * 1.Check header on page -ON
 * 2.Check social links on page - ON
 * 3.Check tracks on a page - OFF
 * 4.Check genres on a page - ON
 * 5.Check artists on a page - ON
 * 6.Check title on a block - ON
 * 7.Check blocks on a page -ON
 */
import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import page.ContentPage;
import basic.BasicTestCase;
public class AzerMusic extends BasicTestCase {
	private ContentPage contentPage = PageFactory.initElements(getWebDriver(), ContentPage.class);
	String pageUrl = ("http://music.day.az/audio_az");
	
	//Check header on page
	@Test(priority=11, groups={"Header"}, description="Проверяет ссылки на шапке на <<Азербайджанская>>")
	public void CheckHeader () throws Exception {
		  get(pageUrl);
		  	try {
		  		//azer music
		  		contentPage.Azerbaijanskaya.click();
		  		contentPage.Azerbaijanskaya.isDisplayed();
		  		assertEquals("Азербайджанская",contentPage.Azerbaijanskaya.getText());
		  		//music
		  		contentPage.Music.isDisplayed();
		  		assertEquals("МУЗЫКА",contentPage.Music.getText());
		  		//new music
		  		contentPage.NewMusic.isDisplayed();
		  		assertEquals("Новинки",contentPage.NewMusic.getText());
		  		//playlist
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
		  			Reporter.log("Is FAILED by no such elemnt on page "+errorUrl);
		  			throw new AssertionError ();
			 		}
		
}
	 //Check social links on page
	 @Test(priority=12, description="Проверяет отображение соц сетей на <<Айзербайджанская>>")
	 public void SocialLinks () throws Exception {
		 get(pageUrl);
		 contentPage.checkSocialLinks();
}
	 //Check genres on a page
	 @Test(priority=13, description="Проверяет отображение жанров на <<Айзербайджанская>>")
	 public void CheckGenres () throws Exception {
		 get(pageUrl);
		 contentPage.checkGenres(25);
}
	 //Check artists on a page
	 @Test(priority=14, description="Проверяет отображение артистов на <<Айзербайджанская>>")
	 public void CheckArtists () throws Exception {
		 get(pageUrl);
		 contentPage.checkArtists(15);
}
	 //Check title on a block
	 @Test(priority=15, description="Проверяет отображение и корректность названий блоков на <<Айзербайджанская>>")
	 public void CheckTitles () throws Exception {
		 get(pageUrl);
		 try{
			 driver.findElement(By.xpath("//h3[contains(text(),'Топ-Исполнители Азербайджана')]")).isDisplayed();
			 driver.findElement(By.xpath("//h3[contains(text(),'Популярные жанры')]")).isDisplayed();
			 driver.findElement(By.xpath("//h3[contains(text(),'Топ-10 азербайджанских песен за неделю')]")).isDisplayed();
		   	}
	    catch (NoSuchElementException e) {
	    	String errorUrl = driver.getCurrentUrl();
	    	log("FAILED: by no title on block "+errorUrl);
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
	 //Check blocks on a page
	 @Test(priority=16, description="Проверяет отображение блоков на <<Айзербайджанская>>")
	 public void CheckBlocks () throws Exception {
		 get(pageUrl);
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
}
