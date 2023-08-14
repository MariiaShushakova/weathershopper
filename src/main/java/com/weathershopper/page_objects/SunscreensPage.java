package com.weathershopper.page_objects;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import com.weathershopper.elements.Product;

@JPage(url = "/sunscreen", title = "The Best Sunscreens in the World!")
public class SunscreensPage extends WebPage {
    @Css("h2")
    public Label sunscreensHeadline;
    @Css(".text-center.col-4")
    public Elements<Product> sunscreensList;
    @Css(".thin-text.nav-link")
    public Button cartBtn;
    @Css("#cart")
    public Label cartQty;
}
