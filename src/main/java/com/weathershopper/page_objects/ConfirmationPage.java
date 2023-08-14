package com.weathershopper.page_objects;

import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;

@JPage(url = "/confirmation", title = "Confirmation")
public class ConfirmationPage extends WebPage {
    @Css("h2")
    public Label paymentSuccess;
    @Css(".text-justify")
    public Label textJustify;
}
