package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.utils.Waits;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    WebDriver driver;

    private @FindBy(css = ".orangehrm-demo-credentials")    WebElement credentialsLbl;
    private @FindBy(css = "[name=username]")                WebElement usernameInp;
    private @FindBy(css = "[name=password]")                WebElement passwordInp;
    private @FindBy(css = ".orangehrm-login-button")         WebElement submitBtn;

    private final String pattern1 = "\\nPassword\\s:\\s";
    private final String pattern2 = ":\\s";
    private final String URL = "https://opensource-demo.orangehrmlive.com/";

    public void visit() {
        this.driver.navigate().to(URL);
        Waits.waitForElementToBeVisible(this.driver, this.credentialsLbl);
    }

    public String getUsernameFromLabel() {
        return this.credentialsLbl.getText().split(pattern1)[0].split(pattern2)[1];
    }

    public String getPasswordFromLabel() {
        return credentialsLbl.getText().split(pattern1)[1];
    }

    public void enterUsername() {
        this.usernameInp.sendKeys(this.getUsernameFromLabel());
    }

    public void enterPassword() {
        this.passwordInp.sendKeys(this.getPasswordFromLabel());
    }

    public void clickSubmit() {
        this.submitBtn.click();
    }

    public void performLogin() throws InterruptedException {
        this.enterUsername();
        this.enterPassword();
        this.clickSubmit();
        Waits.waitForElementToBeVisible(this.driver, By.xpath("//li/a/span[text()=\"Time\"]"));
    }
}