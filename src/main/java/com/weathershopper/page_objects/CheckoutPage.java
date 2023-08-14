package com.weathershopper.page_objects;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import com.weathershopper.elements.Item;

@JPage(url = "/cart", title = "Cart Items!")
public class CheckoutPage extends WebPage {
    @Css("h2")
    public Label checkoutHeadline;
    @Css(".table tr")
    public Elements<Item> items;
    @Css("#total")
    public Label total;
    @Css(".stripe-button-el")
    public Button payWithCard;


    public static int getPrice(Label price){
        return Integer.parseInt(price.getText().split(" ")[2]);
    }
}
