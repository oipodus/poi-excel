package ru.vlapin.experiments.poi.xlsx;

import lombok.experimental.FieldDefaults;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static lombok.AccessLevel.PROTECTED;

@FieldDefaults(level = PROTECTED)
class TestBase {

    static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }
        val caps = new DesiredCapabilities();
//        caps.setCapability(FirefoxDriver.MARIONETTE, false);
//        driver = new FirefoxDriver(caps);
        driver = new ChromeDriver();
        tlDriver.set(driver);
//        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null; }));
    }

    @AfterEach
    public void stop() {
   //     driver.quit();
   //     driver = null;
    }
}
