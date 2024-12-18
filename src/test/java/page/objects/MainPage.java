package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class MainPage {


    private final WebDriver driver;

    public static final String URL_MAIN_PAGE = "https://qa-scooter.praktikum-services.ru/";
    private static final By TITLE_OF_DDLIST_FAQ = By.xpath(".//*[text() = 'Вопросы о важном']"); //Заголовок FAQ
    public static final String COMPONENT_DDLIST = "//*[@id='accordion__heading-%d']";
    public static final String TEXT_COMPONENT_DDLIST = ".//*[@id='accordion__panel-%d']/p";
    private final By BUTTON_ORDER_IN_HEADER = By.xpath(".//*[@class='Button_Button__ra12g']"); //Кнопка "Заказать" в заголовке страницы
    private final By BUTTON_ORDER_ON_PAGE = By.xpath(".//*[@class='Home_FinishButton__1_cWm']"); //Кнопка "Заказать" в середине страницы


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToDDListFAQ() {   //Прокрутить до выпадающего списка с FAQ
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(TITLE_OF_DDLIST_FAQ));
    }

    public void clickToComponentDDListFAQ(int numberInList) {   //Нажать на компонент выпадающего списка
        driver.findElement(By.xpath(String.format(COMPONENT_DDLIST, numberInList - 1))).click();
    }

    public void waitTextOfComponentDDListFAQ(int numberInList) {    //Ожидания появления текста компонента
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(TEXT_COMPONENT_DDLIST, numberInList - 1))));
    }

    public void clickToButtonNewOrder(String typeButtonNewOrder) {  //Нажатие на кнопку "Заказать"
        if (Objects.equals(typeButtonNewOrder, "buttonInHeader")) { //Если тип кнопки buttonInHeader, то выполняется нажатие на кнопку "Заказать" в заголовке страницы
            driver.findElement(BUTTON_ORDER_IN_HEADER).click();
        } else if (Objects.equals(typeButtonNewOrder, "buttonOnPage")) { //Если тип кнопки buttonOnPage, то выполняется нажатие на кнопку "Заказать" в середине страницы
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(BUTTON_ORDER_ON_PAGE));
            driver.findElement(BUTTON_ORDER_ON_PAGE).click();
        } else {    //Иначе исключение
            throw new RuntimeException("Неизвестный тип кнопки нового заказа: " + typeButtonNewOrder);
        }

    }
}
