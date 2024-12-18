package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private static final By FORM_INFO_CUSTOMER = By.xpath(".//*[@class='Order_Form__17u6u']");
    private static final By FIELD_NAME = By.xpath(".//*[contains(@placeholder, 'Имя') and @class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private static final By FIELD_SURNAME = By.xpath(".//*[contains(@placeholder, 'Фамилия') and @class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private static final By FIELD_ADDRESS = By.xpath(".//*[contains(@placeholder, 'Адрес') and @class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private static final By FIELD_PHONE_NUMBER = By.xpath(".//*[contains(@placeholder, 'Телефон') and @class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private static final By DD_LIST_METRO_STATION  = By.xpath(".//*[@class='select-search__input']");
    private static final String METRO_STATION = ".//*[@class='Order_Text__2broi' and text()='%s']";
    private static final By BUTTON_NEXT = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private static final By DELIVERY_DATE = By.xpath(".//*[contains(@placeholder, 'Когда привезти самокат')]");
    private static final By DD_LIST_RENTAL_PERIOD = By.xpath(".//*[@class='Dropdown-placeholder']");
    private static final String RENTAL_PERIOD = ".//*[text()='%s']";
    private static final String CHECKBOX_COLOR = ".//*[@id='%s']";
    private static final By FIELD_COMMENT = By.xpath(".//*[contains(@placeholder, 'Комментарий') and @class='Input_Input__1iN_Z Input_Responsible__1jDKN']");
    private static final By BUTTON_ORDER = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private static final By FORM_CONFIRMATION = By.xpath(".//*[@class='Order_Modal__YZ-d3']");
    private static final By BUTTON_ORDER_CONFIRMATION_YES = By.xpath(".//*[text()='Да']");
    //private static final By BUTTON_ORDER_CONFIRMATION_NO = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text()='Нет']");
    private static final By FORM_SUCCESSFUL_ORDER = By.xpath(".//*[@class='Order_ModalHeader__3FDaJ']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterDataCustomer(String name, String surname, String address, String metroStation, String phoneNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(FORM_INFO_CUSTOMER));
        driver.findElement(FIELD_NAME).sendKeys(name);
        driver.findElement(FIELD_SURNAME).sendKeys(surname);
        driver.findElement(FIELD_ADDRESS).sendKeys(address);
        driver.findElement(DD_LIST_METRO_STATION).click();
        driver.findElement(By.xpath(String.format(METRO_STATION, metroStation))).click();
        driver.findElement(FIELD_PHONE_NUMBER).sendKeys(phoneNumber);

    }

    public void clickButtonNext() {
        driver.findElement(BUTTON_NEXT).click();
    }

    public void enterDataRent(String deliveryDate, String rentalPeriod, String colorScooter, String comment) {
        driver.findElement(DELIVERY_DATE).sendKeys(deliveryDate);
        driver.findElement(By.xpath(".//*[text()='Про аренду']")).click(); //Костыль? Нажать на заголовок, что бы закрыть календарь
        driver.findElement(DD_LIST_RENTAL_PERIOD).click();
        driver.findElement(By.xpath(String.format(RENTAL_PERIOD, rentalPeriod))).click();
        driver.findElement(By.xpath(String.format(CHECKBOX_COLOR, colorScooter))).click();
        driver.findElement(FIELD_COMMENT).sendKeys(comment);
    }

    public void clickButtonOrder() {
        driver.findElement(BUTTON_ORDER).click();
    }

    public void clickButtonConfirmationOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(FORM_CONFIRMATION));
        driver.findElement(BUTTON_ORDER_CONFIRMATION_YES).click();
    }





}
