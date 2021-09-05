package Utils;

import Driver.WebDriverManager;
import Helpers.CustomUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;

public interface Page {
    public boolean isPageLoaded() throws InterruptedException;

    default void initPage() {
        WebDriver driver = WebDriverManager.getCurrentDriver();
        try {
            PageFactory.initElements(driver, this);
            if (!isPageLoaded()) {
                throw new RuntimeException("Страница " + this.getClass().getSimpleName() + " не загружена");
            }
        } catch (WebDriverException | InterruptedException e) {
            CustomUtils.getScreen(driver);
            throw new RuntimeException("Ошибка при загрузки страницы " + this.getClass().getSimpleName() + "\n" + e.toString());
        }
    }

    default WebElement getElement(String name) {
        WebElement element = null;
        Class<?> validationClass = this.getClass();
        do {
            Field[] fields = validationClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == WebElement.class) {
                    field.setAccessible(true);
                    if (field.getAnnotation(FieldName.class) != null) {
                        if (field.getAnnotation(FieldName.class).value().equals(name)) {
                            try {
                                element = (WebElement) field.get(this);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            validationClass = validationClass.getSuperclass();
        } while (element == null && validationClass == null);
        return element;
    }
}
