package tests;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.MainPage;
import pageObjects.OrderPage;

import static org.junit.Assert.assertTrue;
import static pageObjects.MainPage.URL_MAIN_PAGE;

@RunWith(Parameterized.class)
public class RegistrationOrderTest {

    public static final By SUCCESSFUL_ORDER_FORM = By.xpath(".//*[@class='Order_Modal__YZ-d3']");
    private WebDriver driver;

    private final String typeButtonNewOrder;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String colorScooter;
    private final String comment;

    public RegistrationOrderTest(String typeButton, String name, String surname, String address, String metroStation, String phoneNumber, String deliveryDate, String rentalPeriod, String colorScooter, String comment) {
        this.typeButtonNewOrder = typeButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.colorScooter = colorScooter;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDataToFillIn() {
        return new Object[][] {
                {"buttonInHeader","Иван", "Иванов", "Мичуринский проспект, д. 34, кв. 12", "Профсоюзная", "88005553535", "21.12.2024", "сутки", "black", "Тестовый комментарий 1"},
                {"buttonOnPage", "Петр", "Петров", "Профсоюзная улица, д. 124, кв. 43", "Раменки", "+79998887766", "02.01.2025", "трое суток", "grey", "Тестовый комментарий 2"},
        };
    }

    @Test
    public void checkPositiveRegistrationOrder() { //Проверка позитивного сценария заказа самоката
        driver = new ChromeDriver();

        driver.get(URL_MAIN_PAGE);
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickToButtonNewOrder(typeButtonNewOrder);

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.enterDataCustomer(name, surname, address, metroStation, phoneNumber);
        objOrderPage.clickButtonNext();
        objOrderPage.enterDataRent(deliveryDate, rentalPeriod, colorScooter, comment);
        objOrderPage.clickButtonOrder();

        objOrderPage.clickButtonConfirmationOrder();

        WebElement formSuccessfulOrder = driver.findElement(SUCCESSFUL_ORDER_FORM);
        boolean isFormSuccessfulOrderDisplayed = formSuccessfulOrder.isDisplayed();
        assertTrue("Форма с информацией об успешном оформлении заказа не отображается", isFormSuccessfulOrderDisplayed);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
