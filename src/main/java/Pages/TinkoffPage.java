package Pages;

import Driver.WebDriverManager;
import Helpers.PageUtils;
import Utils.ExchangePage;
import Utils.FieldName;
import data.Currency;
import data.OperationType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static data.Currency.USD;

public class TinkoffPage implements ExchangePage {
    @FindBy(xpath = "//div[contains(text(),'Рубль')]")
    private WebElement pageLoadSign;

    @FieldName("Курс покупки евро")
    private WebElement euroBuy;


    @FieldName("Курс продажи евро")
    private WebElement euroSell;

    @FindAll(@FindBy(xpath = "//div[@class='r3i7IZ']"))
    private List<WebElement> switchers;
    private WebElement switcher;


    @FieldName("Курс покупки доллара")
    private WebElement usdBuy;

    @FieldName("Курс продажи доллара")
    private WebElement usdSell;

    public TinkoffPage() {
        initPage();
    }


    @Override
    public double getCourseDouble(OperationType operationType, Currency currency) {
        switchers.get(1).click();
        WebElement choseUSD = WebDriverManager.getCurrentDriver().findElement(By.xpath("//div[contains(text(), '" + currency.getValue() + "')]"));
        choseUSD.click();
        String xPath;
        if (operationType == OperationType.BUY) {
            xPath = "//div[@style='padding: 8px 32px 16px 0px;']";
        } else {
            xPath = "//div[@class='d2KQOa h2KQOa' and @style='padding: 16px 32px 16px 0px;']";
        }
        List<WebElement> elements = WebDriverManager.getCurrentDriver().findElements(By.xpath(xPath));
        String currentValue = elements.get(0).getText();

        return Double.parseDouble(currentValue.replaceAll(",", ".").trim());
    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isVisible(pageLoadSign);
    }

    public static String getMainURL() {
        return mainURL;
    }

    private static String mainURL = "https://www.tinkoff.ru/about/exchange/";
}
