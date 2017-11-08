package SamplePackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTest {
 
	public WebDriver driver;
	
	@BeforeTest
	public void setUp()
	{
		driver = new ChromeDriver();
		
		driver.get("https://transportnsw.info/trip#/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	}

  @Test
  public void TripTest() throws Exception {
	  
	    driver.findElement(By.id("search-input-From")).sendKeys("North Sydney");
	    
	    //driver.findElement(By.cssSelector(selector)("By.cssSelector("#suggestion-"+whichBox+"-0"")).click();
	   WebDriverWait wait1 = new WebDriverWait(driver,20);
	   WebElement ele1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#suggestion-"+ "From" +"-0")));
	    
        driver.findElement(By.id("search-input-To")).sendKeys("Town Hall");
        
        WebDriverWait wait2 = new WebDriverWait(driver,20);
 	   WebElement ele2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#suggestion-"+ "To" +"-0")));
        
      
        driver.findElement(By.id("search-button")).click();
       
        List<WebElement> tripsList = driver.findElements(By.cssSelector(".row"));  
        
        int trips = tripsList.size();
        System.out.println(trips);
        
        if (trips>0){
        	Assert.assertTrue(trips>0,"Trips returned is more than 1");
        }
        	else
        	{
        		Assert.assertFalse(trips==0, "No trips returned");
        	}
        }
  
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
