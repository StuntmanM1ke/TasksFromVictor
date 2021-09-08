package Steps;

import Driver.WebDriverManager;
import Helpers.CustomUtils;
import Pages.YandexMarketPage;
import Utils.ExchangePage;
import Utils.Page;
import data.Currency;
import data.ExchangeRate;
import data.OperationType;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

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
    public double getExchangeCourse(ExchangePage page, OperationType operationType, Currency moneyType) throws InterruptedException {
        return page.getCourseDouble(operationType, moneyType);
    }

    @Step("Определить наименее выгодный курс")
    public Double findLowest(Double vtb, Double tin, Double open, Double sber) {
        ArrayList<ExchangeRate> list = new ArrayList<>();
        list.add(new ExchangeRate("VTB", vtb));
        list.add(new ExchangeRate("Tinkoff", tin));
        list.add(new ExchangeRate("OPEN", open));
        list.add(new ExchangeRate("Sber", sber));
        Collections.sort(list, ExchangeRate::compareTo);
        Allure.step("Самый не выгодный курс = " +list.get(3).getRate() + " " +list.get(3).getBank());
        Allure.step("Самый выгодный курс = " +list.get(0).getRate() + " " +list.get(0).getBank());
        return list.get(3).getRate();
    }

   //Задача 2
    @Step("Переход в раздел видеокарт")
    public void toVideoCards(YandexMarketPage page){
        page.goToVideoCards();
    }
    @Step("Поиск, сортировка")
    public WebElement getCheapestOne(YandexMarketPage page, String request) throws InterruptedException {
        page.findAndSort(request);
        return page.getFirstFromList(request);
    }
    @Step("Получение наиболее дешевых карт и сравнение их стоимости")
    public void compare(YandexMarketPage page, String requestOne, String requestTwo) throws InterruptedException {
        Double priceOfOne;
        Double priceOfTwo;
        WebElement firstCard;
        WebElement secondCard;
        firstCard = getCheapestOne(page, requestOne);
        priceOfOne = Double.parseDouble(firstCard.findElement(By.xpath("./div[5]//div[1]//div")).getText().replaceAll("₽", "").replaceAll(" ", ""));
        secondCard = getCheapestOne(page, requestTwo);
        priceOfTwo = Double.parseDouble(secondCard.findElement(By.xpath("./div[5]//div[1]//div")).getText().replaceAll("₽", "").replaceAll(" ", ""));
        Allure.step("Цена первой видеокарты = " + priceOfOne + ", цена второй = " + priceOfTwo);
        Assertions.assertTrue(priceOfOne < priceOfTwo);
    }
}
