package Pages;

import Helpers.PageUtils;
import Utils.ExchangePage;
import Utils.Page;
import data.Currency;
import data.OperationType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SberPage implements ExchangePage {
    @FindBy(xpath = "//table[@class = 'rates-table-component']//tbody//tr[2]/td[2]")
    private WebElement pageLoadSign;

    @FindBy(xpath = "//table[@class = 'rates-table-component']//tbody//tr[2]/td[3]")
    private WebElement pageTestSign;

    List<Map<String, String>> collectERates = new ArrayList<>();


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
        return 0;
    }
}
