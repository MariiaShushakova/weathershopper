package com.weathershopper.ui;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.weathershopper.WeatherShopperSite;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

public class TestInit extends TestNGBase {
    public String hubUrl;
    public RemoteWebDriver driver;
    public static final String BASE_URI = "https://weathershopper.pythonanywhere.com/";

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws IOException {
        initDriver();
        driver.get(BASE_URI);
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Current Temperature"));
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        WebSettings.getDriver().close();
    }

    private void initDriver() throws IOException {
        //TODO local
//        System.setProperty("webdriver.edge.driver", "src\\main\\resources\\msedgedriver.exe");
//        driver = new EdgeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);

        //TODO docker
        hubUrl = "http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("115.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        driver = new RemoteWebDriver(URI.create(hubUrl).toURL(), capabilities);
        driver.manage().window().maximize();

        WebSettings.initFromProperties();
        WebSettings.useDriver(() -> driver);
        WebSite.init(WeatherShopperSite.class);
    }
}
