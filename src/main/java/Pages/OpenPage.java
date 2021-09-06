package Pages;

import Utils.ExchangePage;
import Utils.FieldName;
import Utils.Page;
import data.Currency;
import data.OperationType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenPage implements ExchangePage {

    @FieldName("Курс обмена")
    @FindBy(xpath = "//*[@class='main-page-exchange main-page-info__card']")
    private WebElement exchangeRates;
    @FindAll(@FindBy(xpath = ".//tbody/tr[contains(@class,'header')]/td"))
    private List<WebElement> tableHeaders;
    @FindAll(@FindBy(xpath = ".//tbody/tr[contains(@class,'row')]"))
    private List<WebElement> tableRows;



    List<Map<String, String>> collectERates = new ArrayList<>();

    private static String mainURL = "https://www.open.ru/";

    public OpenPage() {
        initPage();
    }

    public List<Map<String, String>> getCollectERates() {
        for (WebElement tableRow : tableRows) {
            Map<String, String> collectRow = new HashMap<>();
            for (int j = 0; j < tableHeaders.size(); j++) {
                collectRow.put(
                        tableHeaders.get(j).getText(),
                        tableRow.findElement(By.xpath(".//td[" + (j + 1) + "]")).getText()
                );
            }
            collectERates.add(collectRow);
        }
        return collectERates;
    }


    @Override
    public boolean isPageLoaded() {
        return true;
    }

    public static String getMainURL() {
        return mainURL;
    }

    @Override
    public double getCourseDouble(OperationType operationType, Currency currency) {
        if (operationType == OperationType.SELL) {
            if (currency == Currency.USD) {
               return Double.parseDouble(
                       collectERates.stream()
                               .filter(x->x.get("Валюта обмена").contains("USD"))
                               .findFirst()
                               .get().get("Банк продает").replace(",","."));
            } else return Double.parseDouble(collectERates.stream()
                    .filter(x->x.get("Валюта обмена").contains("EUR"))
                    .findFirst()
                    .get().get("Банк продает").replace(",","."));
        } else {
            if (currency == Currency.USD) {
                return Double.parseDouble(collectERates.stream()
                        .filter(x->x.get("Валюта обмена").contains("USD"))
                        .findFirst()
                        .get().get("Банк покупает").replace(",","."));
            } else return Double.parseDouble(collectERates.stream()
                    .filter(x->x.get("Валюта обмена").contains("EUR"))
                    .findFirst()
                    .get().get("Банк покупает").replace(",","."));
        }
    }
}
