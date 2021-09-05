package ru.yandex;

import Pages.TinkoffPage;
import Pages.YandexPage;
import data.Currency;
import data.OperationType;
import org.junit.jupiter.api.Test;

public class Tests extends BaseTest {

    @Test
    public void getAndCompareCourses() {
        steps.goPage(TinkoffPage.getMainURL());
        TinkoffPage page = new TinkoffPage();
        double usdBUY = steps.getExchangeCourse(page, OperationType.BUY, Currency.USD);
        double usdSELL = steps.getExchangeCourse(page, OperationType.SELL, Currency.USD);
        double euroBUY = steps.getExchangeCourse(page, OperationType.BUY, Currency.EURO);
        double euroSELL = steps.getExchangeCourse(page, OperationType.SELL, Currency.EURO);
        System.out.println(steps.findLowest(usdBUY,usdSELL,euroBUY,euroSELL));
    }
}
