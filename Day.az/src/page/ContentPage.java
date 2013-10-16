package page;

import static org.testng.AssertJUnit.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ContentPage extends MethodsPage {
	 WebDriverWait wait = new WebDriverWait(driver, 30);
	   public ContentPage(WebDriver driver) {
	    super(driver);
	   }
	   
	//for head links
	@FindBy (xpath = "//*[@id='logo']/div/a") //site logo
	public WebElement Logo;
	   
	@FindBy (xpath = "//*[@class='base-menu']//a") //link "music"
	public WebElement Music;

	@FindBy (xpath = ".//*[@id='wrapper']/div[2]/ul/li[1]/a") //link "new music"
	public WebElement NewMusic;
	   
	@FindBy (xpath = ".//*[@id='wrapper']/div[2]/ul/li[2]/a") //link "kazahskaya"
	public WebElement Azerbaijanskaya;

	@FindBy (xpath = ".//*[@id='wrapper']/div[2]/ul/li[3]/a") //link "play list"
	public WebElement PlayList;

	 @FindBy (xpath = ".//*[@id='wrapper']/div[2]/ul/li[4]/a") //link "download"
	public WebElement Upload;

	 @FindBy (xpath = ".//*[@id='wrapper']/div[2]/ul/li[5]/a") //link "karaoke"
	public WebElement Karaoke;

	 //for logo
	 @FindBy (xpath ="//*[class='left']/li[1]") //link "main page"
	 public WebElement MainPage;

	 @FindBy (xpath =".//*[@id='js-add-to-home-page']/a") //link "make start page"
	 public WebElement MakeStartPage;

	 @FindBy (xpath =".//*[@id='header-new']//li[3]/a") //link "kazah lang"
	 public WebElement KazahVers;  
	   
	   //Social network
	   @FindBy (xpath = "//*[@class='pluso-wrap']/a[1]") //link "Vkontakte"
	   public WebElement Vkontakte;

	   @FindBy (xpath = "//*[@class='pluso-wrap']/a[2]") //link "Odnoklasniki"
	   public WebElement Odnoklasniki;
	  
	   @FindBy (xpath = "//*[@class='pluso-wrap']/a[3]") // link "Facebook"
	   public WebElement Facebook;
	  
	   @FindBy (xpath = "//*[@class='pluso-wrap']/a[4]") // link "Twitter"
	   public WebElement Twitter;
	  
	   @FindBy (xpath = "//*[@class='pluso-wrap']/a[5]") // link "Google"
	   public WebElement Google;
	  
	   @FindBy (xpath = "//*[@class='pluso-wrap']/a[6]") // link "Live Journal"
	   public WebElement LiveJournal;
	   
	  
	   @FindBy (xpath = "//*[@class='pluso-wrap']/a[7]") // link "Tumblr"
	   public WebElement Tumbler;
	  
	   @FindBy (xpath = "//*[@class='pluso-wrap']/a[8]") // link "Mailru"
	   public WebElement Mailru;
	  
	   @FindBy (xpath = "//*[@class='pluso-wrap']/a[9]") // ajax "Pluso"
	   public WebElement Pluso;
	   
	    //for play list
	    @FindBy (xpath = "//*[@class='row']/a[2]")
	    public WebElement DelTrack; // delete track from play list
	    
	    @FindBy (xpath = "//*[@class='advanced_playlist']/li[1]")// 1-track in block
	    public WebElement FirstTrack;
	    
	    @FindBy (xpath = "//*[@class='row']/a[1]") // add track to play list
	    public WebElement AddTrack;
	    
	    @FindBy (xpath = "//*[@class='left']//*[@class='advanced_playlist']/li[1]") // first track in play list
	    public WebElement FirstPlaylistTrack;
	    
	     //for search
	    @FindBy (xpath = "//*[@id='audio_search']") // search field
	    public WebElement SearchField;
	    
	    @FindBy (xpath = "//*[@class='w_100']")
	    public WebElement SearchSubmit;
	      
	      // Method for log function
		  public static void log(String message)
		  {
		  Throwable t = new Throwable();
		  StackTraceElement trace[] = t.getStackTrace();
		  if (trace.length > 1)
		  {
		  StackTraceElement element = trace[1];
		  System.out.format("[%s.%s] %s", element.getClassName(), element.getMethodName(), message).println();
		  }
		  else
		  System.out.format("[no info] %s", message);
		  }
	  
	   // Check social links is present
	   public void checkSocialLinks () throws Exception {
		   try {
	   isElementPresent(Vkontakte);
	   isElementPresent(Odnoklasniki);
	   isElementPresent(Twitter);
	   isElementPresent(Google);
	   isElementPresent(LiveJournal);
	   isElementPresent(Tumbler);
	   isElementPresent(Mailru);
	   isElementPresent(Pluso);
	   isElementPresent(Facebook);
		   }
		  catch (NoSuchElementException e) {
			  	String errorUrl = driver.getCurrentUrl();
				log("FAILED: by no such element error on page "+errorUrl);
				Reporter.log("FAILED: by no such elemnt on page "+errorUrl);
			    throw new NoSuchElementException ();
		  }
	   } 
	    //check tracks is present
	   public void checkTracks (int expected_numb_tracks) throws Exception {
	    int real_numb_tracks = driver.findElements(By.xpath (".//*[@class='advanced_playlist']/li")).size();
	    for (int i=1; i<=real_numb_tracks; i++) {
	     isElementPresent(driver.findElement(By.cssSelector (".row>strong>a")));
	     driver.findElement(By.xpath("//*[@class='advanced_playlist']/li["+i+"]"));
	    }
	    if (expected_numb_tracks != real_numb_tracks) {
	     String errorUrl = driver.getCurrentUrl();
	     log("FAILED: by wrong number of tracks on this page "+errorUrl);
	     Reporter.log("FAILED: by wrong number of tracks on this page "+errorUrl);
	     throw new NullPointerException ();
	    }
	   }   
	    //check genre tags is present
	   public void checkGenres (int expected_numb_genres) throws Exception {
	    int real_numb_genres = driver.findElements(By.xpath (".//*[@class='popular-genres']/li")).size();
	    for (int i=1; i<=real_numb_genres; i++) {
	     isElementPresent(driver.findElement(By.xpath (".//*[@class='popular-genres']/li["+i+"]")));
	    }
	    if (expected_numb_genres !=real_numb_genres) {
	     String errorUrl = driver.getCurrentUrl();
	     log("FAILED: by wrong number of genres on this page "+errorUrl);
	     Reporter.log("FAILED: by wrong number of genres on this page "+errorUrl);
	     throw new NullPointerException();
	    }
	   } 
	   //check artists is present
	   public void checkArtists (int expected_numb_artists) throws Exception {
	    int real_numb_artists = driver.findElements(By.cssSelector(".ordered-list>li")).size();
	    for (int i=1; i<=real_numb_artists; i++) { 
	     isElementPresent(driver.findElement(By.xpath("//*[@class='ordered-list']//li["+i+"]/h4/a")));
	     }
	    if (expected_numb_artists != real_numb_artists) {
	        String errorUrl = driver.getCurrentUrl();
	        log("FAILED: by wrong number of tracks on this page "+errorUrl);
	        Reporter. log("FAILED: by wrong number of tracks on this page "+errorUrl);
	     throw new NullPointerException ();
	    }
	     } 

	   //Testing search
	   public void Search (String word) throws Exception {
		   try {
	   type(SearchField, word ); //clear field and inputs text to field
	   SearchSubmit.click();
	   List<WebElement> allElements = driver.findElements(By.xpath("//*[@class='row']")); // gets list of elements
	   for (WebElement elements: allElements) { //do for all elements
	         String s = elements.getText(); // gets all text of elements list
	         assertTrue(s.contains(word)); // compare text with another text
	   }
		   }
	   catch (AssertionError e) {
		   String errorUrl = driver.getCurrentUrl();
		   log("FAILED: by problems with search "+errorUrl);
		   Reporter.log("FAILED: by problems with search "+errorUrl);
		   throw new AssertionError ();  
	   }
	 }
	   //Play music test
	   public void playMusic () throws Exception {
	   driver.findElement(By.xpath("//*[@class='row']/strong/a")).click();
	   driver.findElement(By.xpath("//*[@class='mainsection']//*[@class='advanced_playlist']/li/a")).click();
	   for(int i=0; i<=35; i++) {
	   String styl = driver.findElement(By.xpath(".//*[@id='progress_slider']/a")).getAttribute("style");   
	   if(styl.equals("left: 0%;")) {    
	   Thread.sleep(1000);
	   } else break;
	   if(i>=30) { //set how long wait
		   String errorUrl = driver.getCurrentUrl();
		   log("FAILED: by track doesnt olay on this page "+errorUrl);
		   Reporter.log("FAILED: by track doesnt olay on this page "+errorUrl);
		   throw new NullPointerException ();
	   }   
	   }
	   }
	   //Check albums on a new music page
	   public void checkAlbums () throws Exception {
	  	 int real_numb = driver.findElements(By.xpath("//*[@class='list']/li")).size();
	  	 for (int i =1; i<=real_numb; i++) {
	  		 isElementPresent(driver.findElement(By.xpath("//*[@class='list']/li["+i+"]")));
	  	 }
	  	 if (real_numb <1) {
	  		 String errorPage = driver.getCurrentUrl();
	  		 log("FAILED: by no albums on this page "+errorPage);
	  		 Reporter.log("FAILED: by no albums on this page "+errorPage);
	  		 throw new NullPointerException();
	  	 }
	   }
}