package Pages;

import Driver.WebDriverManager;
import Helpers.PageUtils;
import Utils.ExchangePage;
import Utils.Page;
import data.Currency;
import data.OperationType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SberPage implements ExchangePage {
    @FindBy(xpath = "//table[@class = 'rates-table-component']//tbody//tr[2]/td[2]")
    private WebElement pageLoadSign;


    @FindBy(xpath = "//table[@class = 'rates-table-component']")
    private WebElement courseTable;


    public SberPage() {
        initPage();
    }


    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(pageLoadSign, "от 1");
    }


    public static String getMainURL() {
        return mainURL;
    }

    private static String mainURL = "https://www.sberbank.ru/ru/quotes/currencies";

    @Override
    public double getCourseDouble(OperationType operationType, Currency currency) {
        String xPath;
        if (operationType == OperationType.SELL) {
            if (currency == Currency.USD) {
                xPath = "//tbody//tr[2]/td[3]";
            } else xPath = "//tbody//tr[7]/td[3]";
        } else {
            if (currency == Currency.USD) {
                xPath = "//tbody//tr[2]/td[4]";
            } else xPath = "//tbody//tr[7]/td[4]";
        }
       WebElement element = courseTable.findElement(By.xpath(xPath));
        String currentValue = element.getText();
        return Double.parseDouble(currentValue.replaceAll(",", "."));
    }
}
