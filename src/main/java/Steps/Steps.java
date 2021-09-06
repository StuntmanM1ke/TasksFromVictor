package Steps;

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

    @Step("Получить курс")
    public double getExchangeCourse(ExchangePage page, OperationType operationType, Currency moneyType) {
        return page.getCourseDouble(operationType, moneyType);
    }

    @Step("Определить наименее выгодный курс")
    public Currency findLowest(double USDBuy, double USDSell, double EUROBuy, double EUROSell) {
        double USDMargin = USDBuy - USDSell;
        double EUROMargin = EUROBuy - EUROSell;
        Currency lowValueCourse;

        if (USDMargin > EUROMargin) {
            lowValueCourse = USD;
            Assertions.assertTrue(USDMargin - EUROMargin <= 1.0);
        } else {
            lowValueCourse = EURO;
            Assertions.assertTrue(EUROMargin - USDMargin <= 1.0);
        }
        return lowValueCourse;
    }
}
