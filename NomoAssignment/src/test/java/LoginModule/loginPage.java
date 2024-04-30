package LoginModule;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;
public class loginPage {
	AndroidDriver driver;
	
	By sidemenu = By.className("android.widget.ImageView");
	By loginPageTab = By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]");
	By usernameField = By.xpath("//android.widget.EditText[@content-desc=\"Username input field\"]");
	By passwordField = By.xpath("//android.widget.EditText[@content-desc=\"Password input field\"]");
	By loginButton = By.xpath("//android.view.ViewGroup[@content-desc=\"Login button\"]");
	
	public loginPage(AndroidDriver driver) {
		this.driver = driver;
    }
	
	public void navigateToLoginPage() throws InterruptedException {
		driver.findElement(sidemenu).click();
		driver.findElement(loginPageTab).click();
		Thread.sleep(6, Duration.seconds);
	}
	
	public void enterUsername(String username) {
		
		driver.findElement(usernameField).sendKeys(username);
	}
	
	public void enterPasswrod(String passwrod) {
		driver.findElement(passwordField).sendKeys(passwrod);
	}
	
	public void submit() {
		
		driver.findElement(loginButton).click();
		
	}
	
	public void clearUsername() {
		driver.findElement(usernameField).clear();  //
		 //
	}
	public void clearPassword() {
		
		driver.findElement(passwordField).clear();  //
	}
	
	

}
