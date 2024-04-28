package LoginModule;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class loginPage {
	
	@AndroidFindBy (className = "android.widget.ImageView") public WebElement sideMenu;
	@AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\\\"menu item log in\\\"]") public WebElement loginPageTab;
	@AndroidFindBy (xpath = "//android.widget.EditText[@content-desc=\\\"Username input field\\\"]") public WebElement usernameField;
	@AndroidFindBy (xpath = "//android.widget.EditText[@content-desc=\\\"Password input field\\\"]") public WebElement passwordField;
	@AndroidFindBy (xpath = "(//android.widget.TextView[@text=\\\"Login\\\"])[2]") public WebElement loginButton;
	
	
	public void navigateToLoginPage() {
		sideMenu.click();
		loginPageTab.click();
	}
	
	public void enterUsername(String username) {
		
		usernameField.sendKeys(username);
	}
	
	public void enterPasswrod(String passwrod) {
		passwordField.sendKeys(passwrod);
	}
	
	public void submit() {
		
		loginButton.click();
	}
	
	

}
