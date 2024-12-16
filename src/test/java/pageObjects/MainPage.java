package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    private By titleOfDDListFAQ = By.xpath(".//*[text() = 'Вопросы о важном']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToDDListFAQ() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(titleOfDDListFAQ));
    }

    public void clickToComponentDDListFAQ(int numberInList) {
        driver.findElement(By.xpath(String.format("//*[@id='accordion__heading-%d']", numberInList - 1))).click();
    }

    public void waitTextOfComponentDDListFAQ(int numberInList) {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(".//*[@id='accordion__panel-%d']/p", numberInList - 1))));
    }


}
