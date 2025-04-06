package com.example.ttt.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.example.ttt.product.ProductDetails;
import com.example.ttt.product.ProductItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage extends BasePage {

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "list-view")
    private WebElement listViewButton;

    @FindBy(id = "input-sort")
    private WebElement sortDropDown;

    @FindBy(css = ".product-layout.product-list")
    private List<WebElement> productItemsList;

    public void switchToListView() {
        click(listViewButton);
    }

    public void sortByNameAscending() {
        click(sortDropDown);
        sortDropDown.sendKeys(Keys.ARROW_DOWN);
        sortDropDown.sendKeys(Keys.ENTER);
    }

    public List<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productItemsList));
        return productItemsList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<ProductDetails> getAllProductDetails() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productItemsList));
        List<ProductDetails> products = new ArrayList<>();

        for (WebElement item : productItemsList) {
            ProductItem productItem = new ProductItem(item);
            products.add(new ProductDetails(
                productItem.getName(),
                productItem.getImageUrl(),
                productItem.getDescription(),
                productItem.getPrice()
            ));
        }

        return products;
    }
}