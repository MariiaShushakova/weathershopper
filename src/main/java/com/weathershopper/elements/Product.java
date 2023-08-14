package com.weathershopper.elements;

import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.XPath;


public class Product extends Element {
    @Css(".font-weight-bold")
    public Label name;
    @Css(".btn-primary")
    public Button add;
    @XPath("//p[contains(text(),'Price')]")
    public Label price;

    public static int getProductPrice(Product m){
        String[] bits = m.price.getText().split(" ");
        return Integer.parseInt(bits[bits.length - 1]);
    }
}
