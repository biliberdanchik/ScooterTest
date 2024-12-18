package tests;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.objects.MainPage;

import static org.junit.Assert.assertEquals;
import static page.objects.MainPage.TEXT_COMPONENT_DDLIST;
import static page.objects.MainPage.URL_MAIN_PAGE;

@RunWith(Parameterized.class)
public class DDListFAQTest {
    private WebDriver driver;

    private final int numberInList; //Номер компонента
    private final String expectedText;  //Ожидаемый текст


    public DDListFAQTest(int numberInList, String expectedText) {
        this.numberInList = numberInList;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] getDataToCheckDDList() {
        return new Object[][] {
                {1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void checkTextInDDListFAQ() {   //Проверка текста пунктов в выпадающем списке Вопросы о важном
        driver = new ChromeDriver();
        driver.get(URL_MAIN_PAGE);
        MainPage objMainPage = new MainPage(driver);

        objMainPage.scrollToDDListFAQ();
        objMainPage.clickToComponentDDListFAQ(numberInList);
        objMainPage.waitTextOfComponentDDListFAQ(numberInList);

        String actualText = driver.findElement(By.xpath(String.format(TEXT_COMPONENT_DDLIST, numberInList - 1))).getText(); //Фиксируем фактический результат
        assertEquals(String.format("Текст элемента №%d выпадающего списка не соответствует требованиям", numberInList), expectedText, actualText); //Сравнение ожидаемого и фактического текста

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}

