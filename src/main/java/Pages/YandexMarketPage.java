package Pages;

import Driver.WebDriverManager;
import Helpers.PageUtils;
import Utils.FieldName;
import Utils.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class YandexMarketPage implements Page {

    private static String mainURL = "https://market.yandex.ru/";

    @FindBy(xpath = "//span[@class='_2ZRlR']")
    private WebElement catalog;

    @FindBy(xpath = "//span[@class='_1UCDW' and contains(text(),'Электроника')]")
    private WebElement catalogLoadSign;


    @FindBy(xpath = "//span[@class='_1UCDW' and contains(text(),'Компьютеры')]")
    private WebElement computers;

    @FindBy(xpath = "//a[contains(text(),'Видеокарты')]")
    public WebElement videoCards;



    public static String getMainURL() {
        return mainURL;
    }

    public void goToVideoCards() {
        Actions actions = new Actions(WebDriverManager.getCurrentDriver());
        catalog.click();
        if (PageUtils.isElementTextContains(catalogLoadSign, "Электроника")) {
            actions.moveToElement(computers).build().perform();
        }

    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(catalog, "Каталог");
    }

    public YandexMarketPage() {
        initPage();
    }
}
