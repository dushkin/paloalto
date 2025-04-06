package com.example.ttt.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DemoPage extends BasePage {
    public static final String URL = "https://tutorialsninja.com/demo";

    public DemoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "search")
    private WebElement searchInput;

    public void searchFor(String term) {
        type(searchInput, term + Keys.ENTER);
    }
}
