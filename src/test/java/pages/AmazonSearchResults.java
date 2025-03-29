package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AmazonSearchResults extends BasePage{

    WebDriver driver;
    @FindBy(className = "a-list-item")
    List<WebElement> brandFilter;
    @FindBy(id = "p_36/range-slider_slider-item_lower-bound-slider")
    WebElement priceLowRangeMarker;
    @FindBy(id = "p_36/range-slider_slider-item_upper-bound-slider")
    WebElement priceMaxRangeMarker;
    @FindBy(id = "s-result-sort-select")
    WebElement sortDropdown;

    public AmazonSearchResults(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean setBrand(String brandName){
        boolean brandSelected = false;
        for(int i=0; i < brandFilter.size(); i++){
            if(brandFilter.get(i).getText().equals(brandName)){
                brandFilter.get(i).click();
                brandSelected = true;
                break;
            }
        }
        return brandSelected;
    }

    public void setPriceRange() throws InterruptedException {
        Actions action = new Actions(driver);
        Thread.sleep(1000);
        System.out.println("setting price range");
        System.out.println(priceLowRangeMarker.getAttribute("ariaValueText"));

        String minValueString = priceLowRangeMarker.getAttribute("ariaValueText");
        int minValue = rangeValueExtractor(minValueString);

        //max range calculator
        String maxValueString = priceMaxRangeMarker.getAttribute("ariaValueText");
        int maxValue = rangeValueExtractor(maxValueString);

        //Move min value to 1000
        while(minValue<1000 && minValue !=1000){
            priceLowRangeMarker.sendKeys(Keys.RIGHT);
            minValueString = driver.findElement(By.id("p_36/range-slider_slider-item_lower-bound-slider")).getAttribute("ariaValueText");
            minValue = rangeValueExtractor(minValueString);
        }

        while (maxValue>5000 && maxValue!=5000){
            priceMaxRangeMarker.sendKeys(Keys.LEFT);
            maxValueString = driver.findElement(By.id("p_36/range-slider_slider-item_upper-bound-slider")).getAttribute("ariaValueText");
            maxValue = rangeValueExtractor(maxValueString);
        }
        driver.findElement(By.className("a-button-input")).click();
    }

    private int rangeValueExtractor(String rangeValue){
        int i=0;
        String ValueString = "";
        while(i<rangeValue.length()){
            if(rangeValue.charAt(i)>= '0' && rangeValue.charAt(i) <= '9' ){
                ValueString = ValueString + rangeValue.charAt(i);
            }
            i++;
        }
        int Value = Integer.parseInt(ValueString);
        return Value;
    }

    public void sortProducts(String sortBy){
        Select sortDropdown = new Select(this.sortDropdown);
        sortDropdown.selectByIndex(2);
        List<WebElement> sortOptions = sortDropdown.getOptions();
        for(int i =0; i < sortOptions.size();i++){
            if(sortOptions.get(i).getText().equals(sortBy)){
                sortDropdown.selectByIndex(i);
                break;
            }
        }
    }

    public AmazonProductPage gotoHighestPriceProduct() throws InterruptedException {
        Thread.sleep(3000);
        WebElement firstProduct = driver.findElement(By.cssSelector("img[class='s-image']"));
        firstProduct.click();
        return new AmazonProductPage(driver);
    }
}
