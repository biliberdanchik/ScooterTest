package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    private final By titleOfDDListFAQ = By.xpath(".//*[text() = 'Вопросы о важном']");
    private final By buttonOrderInHeader = By.xpath(".//*[@class='Button_Button__ra12g']");
    private final By buttonOrderOnPage = By.xpath(".//*[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToDDListFAQ() {   //Прокрутить до выпадающего списка с FAQ
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(titleOfDDListFAQ));
    }

    public void clickToComponentDDListFAQ(int numberInList) {   //Нажать на компонент выпадающего списка
        driver.findElement(By.xpath(String.format("//*[@id='accordion__heading-%d']", numberInList - 1))).click();
    }

    public void waitTextOfComponentDDListFAQ(int numberInList) {    //Ожидания появления текста компонента
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(".//*[@id='accordion__panel-%d']/p", numberInList - 1))));
    }

    public void clickToButtonOrderInHeader() {
        driver.findElement(buttonOrderInHeader).click();
    }
}
