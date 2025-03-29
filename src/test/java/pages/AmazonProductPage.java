package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AmazonProductPage extends BasePage{

    WebDriver driver;
    public AmazonProductPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean addToCart() throws InterruptedException {
        boolean addedToCart = false;
        Actions action = new Actions(driver);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        int tabid = tabs.size() - 1;
        driver.switchTo().window(tabs.get(tabid));
        Thread.sleep(3000);

        //List of button elements as in the DOM, there is another hidden add to cart button that is not clickable.
        List<WebElement> addToCartBtn = driver.findElements(By.xpath("//*[@id=\"add-to-cart-button\"]"));
        int i;
        for(i=0; i< addToCartBtn.size(); i++){
            if(addToCartBtn.get(i).getAttribute("type").equals("submit")){
                break;
            }
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn.get(i));
        if(isElementClickable(addToCartBtn.get(i))) {
            addToCartBtn.get(i).click();
        }
        Thread.sleep(5000);
        List<WebElement> addedToCartAlert = driver.findElements((By.className("a-alert-heading")));
        for(i=0; i<addedToCartAlert.size();i++){
            if(addedToCartAlert.get(i).getText().equals("Added to cart")){
                break;
            }
        }
        addedToCart = isElementVisible(addedToCartAlert.get(i));
        System.out.println(addedToCartAlert.get(i).getText());
        driver.close();
        driver.switchTo().window(tabs.get(0));
        return addedToCart;
    }
}
