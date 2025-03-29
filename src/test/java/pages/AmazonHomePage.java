package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class AmazonHomePage extends BasePage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchTextBox;

    public AmazonHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public AmazonSearchResults enterSearchText(String searchText){
        searchTextBox.clear();
        searchTextBox.sendKeys(searchText + Keys.ENTER);
        return new AmazonSearchResults(driver);
    }
}
