package content;
/*
 * 1.Check links on header for current page -ON
 * 2.Check social links on page - ON
 * 3.Check tracks on a page -OFF
 * 4.Check genres on a page - ON
 * 5.Check artists on a page -ON
 * 6.Check title on a block -ON
 * 7.Check blocks on a page -ON
 * 8.Play music on a page -ON
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
	 String pageUrl = "http://music.day.az/";
	 
	 //Check links on header for current page
	@Test(priority = 1, groups={"Header"}, description="Проверяет ссылки на шапке на <<Музыка>>")
    public void CheckHeader () throws Exception {
		 get(pageUrl);
		 	try{
		 		//music
		 		contentPage.Music.click();
		 		contentPage.Music.isDisplayed();
		 		assertEquals("МУЗЫКА",contentPage.Music.getText());
		 		//new music
		 		contentPage.NewMusic.isDisplayed();
		 		assertEquals("Новинки",contentPage.NewMusic.getText());
		 		//kazahs music
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
	 @Test(priority=2, description="Проверяет отображение соц сетей на <<Айзербайджанская>>")
	public void SocialLinks () throws Exception {
		 get(pageUrl);
		 contentPage.checkSocialLinks();
}
	 //Check genres on a page
	@Test(priority=3, description="Проверяет отображение жанров на <<Музыка>>")
	public void CheckGenres () throws Exception {
	  get(pageUrl);
	  contentPage.checkGenres(24);
}
	 //Check artists on a page
	@Test(priority=4, description="Проверяет отображение артистов на <<Музыка>>")
	public void CheckArtists () throws Exception {
	  get(pageUrl);
	  contentPage.checkArtists(15);
}
	 //Check title on a block
	@Test(priority=5, description="Проверяет отображение и корректоность названий блоков на <<Музыка>>")
	public void CheckTitles () throws Exception {
		  get(pageUrl);
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
		  catch (AssertionError e) {
			    String errorUrl = driver.getCurrentUrl();
			  	log("FAILED: by bad title on block "+errorUrl);
			  	Reporter.log("FAILED: by bad title on block "+errorUrl);
			  	throw new AssertionError ();  
		  }
}
	 //Check blocks on a page
	@Test(priority=6, description="Проверяет отображение блоков на <<Музыка>>")
	public void CheckBlocks () throws Exception {
		  get(pageUrl);
		  	try{
		  		driver.findElement(By.xpath("//*[@class='maincontent']//*[@class='section'][1]")).isDisplayed();//block top 10 tracks of the week
		  		driver.findElement(By.xpath("//*[@class='maincontent']//*[@class='section'][2]")).isDisplayed();//block pop genres
		  		driver.findElement(By.xpath("//*[@class='aside']//*[@class='section']")).isDisplayed();//block top artists
		  		}
		  catch (NoSuchElementException e) {
		  		String errorUrl = driver.getCurrentUrl();
		  		log("Is FAILED by no block on page "+errorUrl);
		  		Reporter.log("Is FAILED by no block on page "+errorUrl);
		  		throw new NoSuchElementException ();
		  		}
}
	 //Play music on a page
	@Test(priority=7, description="Проверяет играет ли музыка на странице скачки треков на <<Музыка>>")
	public void PlayMusic () throws Exception {
		 get(pageUrl);
		 contentPage.playMusic();
}
}