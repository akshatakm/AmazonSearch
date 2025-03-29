package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;


    public boolean isElementVisible(WebElement element) {
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(15L));

        try {
            this.wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public boolean isElementClickable(WebElement element) {
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10L));
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception var3) {
            return false;
        }
    }
}
