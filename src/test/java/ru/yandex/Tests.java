package ru.yandex;

import Pages.*;
import data.Currency;
import data.OperationType;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tests extends BaseTest {

    @Test
    public void getAndCompareCourses() throws InterruptedException {
        steps.goPage(VTBPage.getMainURL());
        VTBPage vtbPage = new VTBPage();
        Double usdBuyVtb = steps.getExchangeCourse(vtbPage, OperationType.BUY, Currency.USD);
        steps.goPage(TinkoffPage.getMainURL());
        TinkoffPage tinkoffPage = new TinkoffPage();
        Double usdBuyTin = steps.getExchangeCourse(tinkoffPage, OperationType.BUY, Currency.USD);
        steps.goPage(OpenPage.getMainURL());
        OpenPage openPage = new OpenPage();
        Double usdBuyOpen = steps.getExchangeCourse(openPage, OperationType.BUY, Currency.USD);
        steps.goPage(SberPage.getMainURL());
        SberPage sberPage = new SberPage();
        Double usdBuySber = steps.getExchangeCourse(sberPage, OperationType.BUY, Currency.USD);
        System.out.println(usdBuyVtb + " " + usdBuyTin + " " + usdBuyOpen + " " + usdBuySber);
        System.out.println(steps.findLowest(usdBuyVtb, usdBuyTin, usdBuyOpen, usdBuySber));
    }

    @Test
    public void yandexMarketCompare() throws InterruptedException {
        steps.goPage(YandexMarketPage.getMainURL());
        YandexMarketPage page = new YandexMarketPage();
        steps.toVideoCards(page);
        steps.compare(page, "gtx 1060", "rtx 3060");
    }
}
