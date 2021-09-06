package Pages;

import Helpers.PageUtils;
import Utils.ExchangePage;
import data.Currency;
import data.OperationType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VTBPage implements ExchangePage {
    @FindBy(xpath = "//span[@id='select2-method-container']")
    private WebElement pageLoadSign;

    @FindBy(xpath = "//div[@class='base-table__inner']")
    private WebElement courseTable;


    public VTBPage() {
        initPage();
    }

    public static String getMainURL() {
        return mainURL;
    }

    private static String mainURL = "https://www.vtb.ru/personal/platezhi-i-perevody/obmen-valjuty/";

    @Override
    public double getCourseDouble(OperationType operationType, Currency currency) {
        String xPath;
        if (operationType == OperationType.SELL) {
            if (currency == Currency.USD) {
                xPath = "//tbody//tr[2]/td[2]";
            } else xPath = "//tbody//tr[3]/td[2]";
        } else {
            if (currency == Currency.USD) {
                xPath = "//tbody//tr[2]/td[3]";
            } else xPath = "//tbody//tr[3]/td[3]";
        }
        WebElement element = courseTable.findElement(By.xpath(xPath));
        String currentValue = element.getText();
        return Double.parseDouble(currentValue.replaceAll(",", "."));
    }

    @Override
    public boolean isPageLoaded()  {
        return PageUtils.isElementTextContains(pageLoadSign, "В офисе (наличные)");
    }
}
