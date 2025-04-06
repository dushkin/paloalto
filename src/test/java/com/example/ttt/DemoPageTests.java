package com.example.ttt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.example.ttt.pages.DemoPage;
import com.example.ttt.pages.ResultsPage;
import com.example.ttt.product.ProductDetails;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoPageTests {
    public static WebDriver driver;
    private DemoPage demoPage;
    private ResultsPage resultsPage;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(new ChromeOptions());
    }

    @BeforeEach
    public void beforeEach() {
        driver.get(DemoPage.URL);
        demoPage = new DemoPage(driver);
        resultsPage = new ResultsPage(driver);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    public void testListViewSortedByNameAsc() {
        demoPage.searchFor("ipod");
        resultsPage.switchToListView();
        resultsPage.sortByNameAscending();

        List<String> actualNames = resultsPage.getProductNames();
        
        //prepare a sorted list of the actual products.
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        //They should be equal...
        Assertions.assertEquals(expectedNames, actualNames, "Items are not sorted alphabetically.");
    }

    @Test
    public void minMaxTest() {
        demoPage.searchFor("ipod");
        resultsPage.switchToListView();

        List<ProductDetails> products = resultsPage.getAllProductDetails();

        ProductDetails minPricedProduct = products.stream().min(Comparator.comparingDouble(p -> p.price)).orElse(null); //null - in case no products in list
        ProductDetails maxPricedProduct = products.stream().max(Comparator.comparingDouble(p -> p.price)).orElse(null);

        System.out.println("Minimum Price iPod:");
        System.out.println(minPricedProduct != null ? minPricedProduct : "None");

        System.out.println("\nMaximum Price iPod:");
        System.out.println(maxPricedProduct != null ? maxPricedProduct : "None");
    }
}
