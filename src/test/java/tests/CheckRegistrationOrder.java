package tests;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.MainPage;
import pageObjects.OrderPage;

import java.time.Duration;

public class CheckRegistrationOrder {
    private WebDriver driver;

    @Test
    public void checkPositiveRegistrationOrder() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickToButtonOrderInHeader();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.enterDataCustomer("Иванов", "Иван", "Ломоносова 3а", "Минская", "88005553535");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
