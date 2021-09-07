package Steps;

import Pages.YandexMarketPage;
import Utils.ExchangePage;
import Utils.Page;
import data.Currency;
import data.OperationType;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static data.Currency.EURO;
import static data.Currency.USD;

public class Steps {
    private WebDriver driver;

    public Steps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход на страницу; {URL}")
    public void goPage(String url) {
        driver.get(url);
    }

    @Step("Поиск по запросу; {request}")
    public void find(String request, Page page) {
        page.getElement("Поле поиска").sendKeys(request);
        page.getElement("Кнопка поиска").click();
    }
//    Задача 1
    @Step("Получить курс")
    public double getExchangeCourse(ExchangePage page, OperationType operationType, Currency moneyType) {
        return page.getCourseDouble(operationType, moneyType);
    }

    @Step("Определить наименее выгодный курс")
    public Currency findLowest(Double usdBuy, Double usdSell, Double euroBuy, Double euroSell) {
        Double usdMargin = usdBuy - usdSell;
        Double euroMargin = euroBuy - euroSell;
        Currency lowValueCourse;

        if (usdMargin > euroMargin) {
            lowValueCourse = USD;
            Assertions.assertTrue(usdMargin - euroMargin <= 1.0);
        } else {
            lowValueCourse = EURO;
            Assertions.assertTrue(euroMargin - usdMargin <= 1.0);
        }
        return lowValueCourse;
    }

   //Задача 2
    @Step("Переход в раздел видеокарт")
    public void toVideoCards(YandexMarketPage page){
        page.goToVideoCards();
    }

}
