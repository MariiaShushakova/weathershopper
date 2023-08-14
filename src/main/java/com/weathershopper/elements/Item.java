package com.weathershopper.elements;

import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.XPath;

public class Item extends Element {
    @XPath("//td[1]")
    public Label name;
    @XPath("//td[2]")
    public Label price;
}
