package ru.yandex;

import Helpers.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver chromeDriver;
    protected Steps steps;

    @BeforeEach
    public void beforeEach() {
        WebDriverManager.in
        chromeDriver = WebDriverManager.getCurrentDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        steps = new Steps(chromeDriver);
    }

    @AfterEach
    public void afterEach() {
        //  WebDriverManager.quitCurrentDriver();
    }
}
}
