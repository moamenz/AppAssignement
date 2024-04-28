package LoginModule;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class loginPageTest {
	File appApk = new File("appFile/Android-MyDemoAppRN.1.3.0.build-244.apk");
	
	@BeforeClass
	public void setup() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Moamens Emulator");
		caps.setCapability("app", appApk.getAbsolutePath());
		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider ="loginUsers")
	public void loginTest(String username, String password) {
		
		loginPage login = new loginPage();
		login.navigateToLoginPage();
		login.enterUsername(username);
		login.enterPasswrod(password);
		login.submit();
		
		/* // Verify login result based on the scenario
        if (username.equals("alice@example.com") && password.equals("10203040")) {
            // Verify user is locked scenario
            WebElement errorMessage = driver.findElement(By.id("errorMessage"));
            // Assert that errorMessage contains "User locked"
            // Assert.assertTrue(errorMessage.getText().contains("User locked"));
        } else if (username.equals("1@2.com") && password.equals("f-o-o")) {
            // Verify no match scenario
            WebElement errorMessage = driver.findElement(By.id("errorMessage"));
            // Assert that errorMessage contains "No match"
            // Assert.assertTrue(errorMessage.getText().contains("No match"));
        } else if (username.isEmpty() || password.isEmpty()) {
            // Verify no user details or no password scenario
            WebElement errorMessage = driver.findElement(By.id("errorMessage"));
            // Assert that errorMessage contains "Username and password are required"
            // Assert.assertTrue(errorMessage.getText().contains("Username and password are required"));
        } else {
            // Verify standard login scenario
            WebElement welcomeMessage = driver.findElement(By.id("welcomeMessage"));
            // Assert that welcomeMessage is displayed
            // Assert.assertTrue(welcomeMessage.isDisplayed());
        }*/
		
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
}
