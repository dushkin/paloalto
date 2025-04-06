package com.example.ttt.product;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductItem {
    
    public ProductItem(WebElement rootElement) {
        PageFactory.initElements(rootElement, this); //Required in page objects classes
    }

    @FindBy(css = ".caption h4 a")
    private WebElement nameElement;

    @FindBy(css = ".image img")
    private WebElement imageElement;

    @FindBy(css = ".caption p")
    private WebElement descriptionElement;

    @FindBy(css = "p.price")
    private WebElement priceElement;

    public String getName() {
        return nameElement.getText();
    }

    public String getImageUrl() {
        return imageElement.getAttribute("src");
    }

    public String getDescription() {
        return descriptionElement.getText();
    }

    public double getPrice() {
        String priceText = priceElement.getText();

        //priceText may be a complex string, so I need to extract the value from it.
        //Try to match a string starting by $ followed by digits, dots and/or commas
        //The required value will be captured in the group by the () in the expression
        Pattern pattern = Pattern.compile("\\$([\\d,.]+)");
        Matcher matcher = pattern.matcher(priceText);
        return matcher.find() ? Double.parseDouble(matcher.group(1).replace(",", "")) : 0.0;
    }
}