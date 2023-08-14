package com.weathershopper.ui.e2e;

import com.weathershopper.data.Constant;
import com.weathershopper.data.CreditCard;
import com.weathershopper.page_objects.CheckoutPage;
import com.weathershopper.elements.Product;
import com.weathershopper.ui.TestInit;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.weathershopper.WeatherShopperSite.*;
import static com.weathershopper.elements.Product.*;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

public class PurchaseTests extends TestInit {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseTests.class);
    private HashMap<String, Integer> expectedPurchase = new HashMap<>();
    CreditCard visa = new CreditCard("visa");

    @BeforeMethod(alwaysRun = true)
    public void beforeTests() {
        temperaturePage.headline.waitDisplayed();
    }

    @Test
    public void verifyPurchaseTest() {
        int weather = Integer.parseInt(temperaturePage.temperature.getText().split(" ")[0]);

        if(weather < 19){
            temperaturePage.buyMoisturizers.click();
            Assert.assertEquals(moisturizersPage.moisturizersHeadline.getText(), "Moisturizers");

            Product firstPurchase = findTheCheapestProduct("Aloe");
            firstPurchase.add.click();
            moisturizersPage.cartBtn.waitDisplayed();
            Assert.assertEquals(moisturizersPage.cartQty.getText(), "1 item(s)");

            findTheCheapestProduct("Almond").add.click();
            moisturizersPage.cartBtn.waitDisplayed();
            Assert.assertEquals(moisturizersPage.cartQty.getText(), "2 item(s)");
            moisturizersPage.cartBtn.click();

        }else if(weather > 34){
            temperaturePage.buySunscreens.click();
            Assert.assertEquals(sunscreensPage.sunscreensHeadline.getText(), "Sunscreens");

            Product firstPurchase = findTheCheapestProduct("SPF-50");
            firstPurchase.add.click();
            sunscreensPage.cartBtn.waitDisplayed();
            Assert.assertEquals(sunscreensPage.cartQty.getText(), "1 item(s)");

            findTheCheapestProduct("SPF-30").add.click();
            sunscreensPage.cartBtn.waitDisplayed();
            Assert.assertEquals(sunscreensPage.cartQty.getText(), "2 item(s)");
            sunscreensPage.cartBtn.click();

        }else {
            logger.warn("Test is over, temperature = [{}C]", weather);
            throw new SkipException("");
        }

        checkoutPage.checkoutHeadline.waitDisplayed();
        Assert.assertEquals(checkoutPage.items.size()-1, 2); //-1 header
        Assert.assertEquals(fillInActualPurchaseMap(), expectedPurchase);
        Assert.assertEquals(CheckoutPage.getPrice(checkoutPage.total), countTotal(expectedPurchase));

        checkoutPage.payWithCard.click();
        fillInPaymentIFrame();

        confirmationPage.paymentSuccess.waitDisplayed();
        Assert.assertEquals(confirmationPage.textJustify.getText(), Constant.SUCCESSFUL_MESSAGE);
    }

    private Product findTheCheapestProduct(String ingredient){
        ArrayList<Product> chosenProductsByIngredient = new ArrayList<>();

        for (Product product : moisturizersPage.products) {
            if(containsIgnoreCase(product.name.getText(), ingredient)){
                chosenProductsByIngredient.add(product);
            }
        }

        int min = getProductPrice(chosenProductsByIngredient.get(0));
        Product resultProduct = chosenProductsByIngredient.get(0);

        for (Product product : chosenProductsByIngredient) {
            int productPrice = getProductPrice(product);
            if(min > productPrice) {
                min = productPrice;
                resultProduct = product;
            }
        }
        expectedPurchase.put(resultProduct.name.getText(), min);
        logger.warn("Product - [{}], price - [{}]", resultProduct.name.getText(), min);
        return resultProduct;
    }

    private HashMap<String, Integer> fillInActualPurchaseMap(){
        HashMap<String, Integer> actualPurchase = new HashMap<>();
        for (int i = 1; i < checkoutPage.items.size(); i++) {
            actualPurchase.put(checkoutPage.items.get(i).name.getText(), Integer.valueOf(checkoutPage.items.get(i).price.getText()));
        }
        return actualPurchase;
    }

    private int countTotal(HashMap<String, Integer> map){
        int sum = 0;
        for (Integer i : map.values()) {
            sum += i;
        }
        return sum;
    }
    private void fillInPaymentIFrame(){
        driver.switchTo().frame(driver.findElement(By.cssSelector(".stripe_checkout_app")));
        driver.findElement(By.cssSelector(".bodyView")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys(visa.email);
        driver.findElement(By.cssSelector("#card_number")).click();
        for (Character ch:visa.cardNum.toCharArray()) {
            driver.findElement(By.cssSelector("#card_number")).sendKeys(ch.toString());
        }
        driver.findElement(By.cssSelector("#cc-exp")).click();
        for (Character ch:visa.expData.toCharArray()) {
            driver.findElement(By.cssSelector("#cc-exp")).sendKeys(ch.toString());
        }
        driver.findElement(By.cssSelector("#cc-csc")).sendKeys(visa.cvc);
        driver.findElement(By.cssSelector("#billing-zip")).sendKeys(visa.zipCode);
        driver.findElement(By.cssSelector("#submitButton")).click();
    }
}
