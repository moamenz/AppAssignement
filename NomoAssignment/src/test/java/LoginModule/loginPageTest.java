package LoginModule;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.appium.java_client.android.AndroidDriver;


public class loginPageTest {
	File appApk = new File("appFile/Android-MyDemoAppRN.1.3.0.build-244.apk");
	private AndroidDriver driver;
	
	@BeforeClass
	public void setup() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Moamens Emulator");
		caps.setCapability("app", appApk.getAbsolutePath());
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider ="loginUsers")
	public void loginTest(String username, String password) throws InterruptedException {
		
		loginPage login = new loginPage(driver);
		login.navigateToLoginPage();
		login.enterUsername(username);
		login.enterPasswrod(password);
		login.submit();
		
		
		
        if (username.equals("alice@example.com") && password.equals("10203040")) {
        	           
        	// Verify user is locked scenario
            WebElement errorMessage = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sorry, this user has been locked out.\"]"));
            // Assert that errorMessage contains "User locked"
            Assert.assertTrue(errorMessage.getText().contains("Sorry, this user has been locked out."));
            login.clearUsername();
            login.clearPassword();
        } else if (username.equals("1@2.com") && password.equals("f-o-o")) {
            // Verify no match scenario
            WebElement errorMessage = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Provided credentials do not match any user in this service.\"]"));
            // Assert that errorMessage contains "No match"
            Assert.assertTrue(errorMessage.getText().contains("Provided credentials do not match any user in this service"));
            login.clearUsername();
            login.clearPassword();
            
        } else if (username.isEmpty()) {
            // Verify no userName scenario
            WebElement usernameValidation = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Username is required\"]"));
            
            // Assert that errorMessage contains" userName required"
            Assert.assertTrue(usernameValidation.isDisplayed());
            login.clearPassword();

            
         } else if (password.isEmpty()) {
            // Verify no password scenario
            WebElement passwordValidation = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Password is required\"]"));
            // Assert password are required"
            Assert.assertTrue(passwordValidation.isDisplayed());
            login.clearUsername();
    
            
        } else if (password.isEmpty() && username.isEmpty()) {
            // Verify no password  and no userName scenario
           
        	 WebElement errorMessage = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Provided credentials do not match any user in this service.\"]"));
             // Assert that errorMessage contains" userName and password are required"
             Assert.assertTrue(errorMessage.isDisplayed());   
             
            
        }  else {
            // Verify standard login scenario
            WebElement productsPage = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Products\"]"));
            // Assert that user redirected to the product page
            Assert.assertTrue(productsPage.isDisplayed());
        }
		
	}
	
	
	@DataProvider(name = "loginUsers")
    public Object[][] getLoginUsers() {
        return new Object[][]{
            {"alice@example.com", "10203040"}, // LOCKED
            {"1@2.com", "f-o-o"},              // NO_MATCH
            {"", ""},                          // NO_USER_DETAILS
            {"bob@example.com", ""},           // NO_PASSWORD
            {"bob@example.com", "10203040"}    // STANDARD
        };
    }
	
	@AfterClass
	public void close() {
		// Close the driver
        if (driver != null) {
            driver.quit();
        }
	}
}
