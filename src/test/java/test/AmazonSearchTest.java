package test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AmazonHomePage;
import pages.AmazonProductPage;
import pages.AmazonSearchResults;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class AmazonSearchTest extends BaseTest{
    AmazonHomePage amazonHomePage;
    AmazonSearchResults amazonSearchResults;
    AmazonProductPage amazonProductPage;

    @Test(dataProvider = "DataInput")
    public void search(String brandName) throws InterruptedException {
        amazonHomePage = new AmazonHomePage(driver);
        amazonSearchResults = amazonHomePage.enterSearchText("smartwatches");
        amazonSearchResults.setBrand(brandName);
        amazonSearchResults.setPriceRange();
        amazonSearchResults.sortProducts("Price: High to Low");
        amazonProductPage = amazonSearchResults.gotoHighestPriceProduct();
        Assert.assertTrue(amazonProductPage.addToCart());
    }

    @DataProvider(name="DataInput")
    public String[] fetchData() {
        String brandList[] = new String[]{"Noise","Fire-Boltt","boAt"};
        for(int i=0; i<brandList.length; i++){
            System.out.println(brandList.toString());
        }
        return brandList;

    }
}
