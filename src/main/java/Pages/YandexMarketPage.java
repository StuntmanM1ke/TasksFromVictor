package Pages;

import Driver.WebDriverManager;
import Helpers.PageUtils;
import Utils.FieldName;
import Utils.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YandexMarketPage implements Page {
    Actions actions = new Actions(WebDriverManager.getCurrentDriver());

    private static String mainURL = "https://market.yandex.ru/";

    @FindBy(xpath = "//span[@class='_2ZRlR']")
    private WebElement catalog;

    @FindBy(xpath = "//span[@class='_1UCDW' and contains(text(),'Электроника')]")
    private WebElement catalogLoadSign;


    @FindBy(xpath = "//span[@class='_1UCDW' and contains(text(),'Компьютеры')]")
    private WebElement computers;

    @FindBy(xpath = "//a[contains(text(),'Видеокарты')]")
    private WebElement videoCards;

    @FindBy(id = "header-search")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class = '_23p69 _3D8AA']")
    private WebElement sortButton;

    private List<WebElement> results;

    public static String getMainURL() {
        return mainURL;
    }

    public void findAndSort(String request) throws InterruptedException {
        searchField.clear();
        searchField.sendKeys(request);
        searchField.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        sortButton.click();
        Thread.sleep(3000);
    }

    public void goToVideoCards() {

        catalog.click();
        if (PageUtils.isElementTextContains(catalogLoadSign, "Электроника")) {
            actions.moveToElement(computers).build().perform();
        }
    }

    public WebElement getFirstFromList(String request) {
        results = WebDriverManager.getCurrentDriver().findElements(By.xpath("//article"));
        results.removeIf(result -> !result.getText().contains(request.toUpperCase()));
        return results.get(0);

    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(catalog, "Каталог");
    }

    public YandexMarketPage() {
        initPage();
    }
}
