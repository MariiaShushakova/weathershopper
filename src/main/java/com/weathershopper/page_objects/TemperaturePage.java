package com.weathershopper.page_objects;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.XPath;

@JPage(url = "/", title = "Current Temperature")
public class TemperaturePage extends WebPage {

    @Css("h2")
    public Label headline;
    @Css("#temperature")
    public Label temperature;
    @XPath("//button[text()='Buy moisturizers']")
    public Button buyMoisturizers;
    @XPath("//button[text()='Buy sunscreens']")
    public Button buySunscreens;

}
