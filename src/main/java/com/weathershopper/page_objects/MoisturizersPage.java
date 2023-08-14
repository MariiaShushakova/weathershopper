package com.weathershopper.page_objects;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import com.weathershopper.elements.Product;


@JPage(url = "/moisturizer", title = "The Best Moisturizers in the World!")
public class MoisturizersPage extends WebPage {
    @Css("h2")
    public Label moisturizersHeadline;
    @Css(".text-center.col-4")
    public Elements<Product> products;
    @Css(".thin-text.nav-link")
    public Button cartBtn;
    @Css("#cart")
    public Label cartQty;
}
