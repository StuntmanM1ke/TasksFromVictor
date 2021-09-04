package Helpers;

import Driver.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class PageUtils {
    public static boolean isVisible (WebElement element) {
        return element.isDisplayed();
    }
    public static void WaitUntilElementBeVisible(WebElement element){
        if (isVisible(element)) return;
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(visibilityOf(element));
    }
    public static void waitUntilElementBeClickable(WebElement element) {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean isClickable(WebElement element) {
        try {
            waitUntilElementBeClickable(element);
        } catch (TimeoutException e) {
            System.out.println("не кликабл");
            return false;
        }
        return true;
    }

    public static boolean isElementNotExist(WebElement element) {
        int timer = 0;
        WebDriverManager.getCurrentDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            while (isVisible(element) && timer < 10) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer++;
            }
            return false;
        } catch (NoSuchElementException e) {
            return true;
        } finally {
            WebDriverManager.getCurrentDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
    }

    public static void waitUntilAttributeWillBe(WebElement element, String attribute, String value) {
        new WebDriverWait(WebDriverManager.getCurrentDriver(), Constants.DEFAULT_TIMEOUT)
                .until((ExpectedCondition<Boolean>) driver -> element.getAttribute(attribute).contains(value));
    }
}
