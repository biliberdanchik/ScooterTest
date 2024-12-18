package tests;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.MainPage;
import pageObjects.OrderPage;

import javax.swing.*;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

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
        objOrderPage.clickButtonNext();
        objOrderPage.enterDataRent("27.12.2024", "трое суток", "black", "тест123");
        objOrderPage.clickButtonOrder();

        objOrderPage.clickButtonConfirmationOrder();

        WebElement formSuccessfulOrder = driver.findElement(By.xpath(".//*[@class='Order_Modal__YZ-d3']"));
        boolean isFormSuccessfulOrderDisplayed = formSuccessfulOrder.isDisplayed();
        assertTrue("Форма с информацией об успешном оформлении заказа не отображается", isFormSuccessfulOrderDisplayed);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
