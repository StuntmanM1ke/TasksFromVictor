package Pages;

import Utils.FieldName;
import Utils.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YandexPage implements Page {

    @FieldName("Поле поиска")
    @FindBy(xpath = "//div[@class = 'search2__input']//input[contains(@class, 'input__control')]")
    public WebElement searchField;

    @FieldName("Кнопка поиска")
    @FindBy(xpath = "//div[@class = 'search2__button']//button[contains(@class, 'button')]")
    public WebElement searchButton;

    @Override
    public boolean isPageLoaded() {
        return true;
    }

    public YandexPage() {
        initPage();
    }
}
