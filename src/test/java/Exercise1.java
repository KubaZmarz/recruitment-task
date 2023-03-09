import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.pages.LoginPage;
import ui.utils.Waits;

import java.time.Duration;

public class Exercise1 {
    WebDriver driver;
    final String TIME_PERIOD = "2022-08-15 - 2022-08-21";
    final By TIME_LIST_BTN_XPATH_SLC = By.xpath("//div[contains(text(),'" + TIME_PERIOD + "')]/../..//button");
    final By CHAT_BUBBLE_ICON_CSS_SLC = By.cssSelector(".bi-chat-dots-fill");
    final By TEXTAREA_CSS_SLC = By.cssSelector("[placeholder=\"Comment here\"]");
    final String ASSERTION_TEXT = "Leadership Development";

    @BeforeTest
    public void setup() {
        this.driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        LoginPage page = new LoginPage(driver);
        page.visit();
        page.performLogin();

        // Won't be using PO, as there is only literally 1 click on page/component
        this.driver.findElement(By.xpath("//li/a/span[text()=\"Time\"]")).click();
        Waits.waitForElementToBeVisible(this.driver, TIME_LIST_BTN_XPATH_SLC);
        this.driver.findElement(By.xpath("TIME_LIST_BTN_XPATH_SLC")).click();
        Waits.waitForElementToBeVisible(this.driver, CHAT_BUBBLE_ICON_CSS_SLC);
        this.driver.findElement(CHAT_BUBBLE_ICON_CSS_SLC).click();

        new WebDriverWait(this.driver, Duration.ofSeconds(5)) // wait for el
                .until(ExpectedConditions.textToBePresentInElementValue(TEXTAREA_CSS_SLC, ASSERTION_TEXT)); // assert
    }

    @AfterTest
    public void cleanUp() {
        driver.close();
    }
}
