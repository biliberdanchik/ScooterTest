package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    private By titleOfDDListImportantQuestions = By.xpath(".//*[text() = 'Вопросы о важном']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToDDListImportantQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(titleOfDDListImportantQuestions));
    }

}
