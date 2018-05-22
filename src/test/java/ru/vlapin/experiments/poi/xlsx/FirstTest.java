package ru.vlapin.experiments.poi.xlsx;

import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
class FirstTest {

    WebDriver driver;
//    WebDriverWait wait;

    @BeforeEach
    void start() {
//        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
//        caps.setCapability("ignoreZoomSetting", true);
//        driver = new InternetExplorerDriver(caps);

        //noinspection SpellCheckingInspection
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        driver = new ChromeDriver(new ChromeOptions()
                .addArguments("--headless"));
    }

    @Test
    void myFirstTest() {
        //login
//        driver.get("https://anm.test.russianpost.ru/#/mailin");
        driver.get("http://google.com");
        //set implicit wait
        driver.findElement(By.name("userName")).sendKeys("test_emul_5");
        // driver.findElement(By.name("password")).sendKeys("Qwerty321$$");
        driver.findElement(By.cssSelector("button.id-page__button.id-page__main-button.ng-binding")).click();
        driver.findElement(By.cssSelector("button.id-page__button.id-page__main-button.ng-binding")).click();
        //driver.findElement(By.Id("russianpost_left_menu_panel_mailin_link")).Click();
        driver.findElement(By.name("trackId")).sendKeys("RA663464783CN");
        //wait.timeout.seconds.equals(3);
        driver.findElement(By.name("weight")).sendKeys("999999");

    }

    @AfterEach
    void stop() {
        driver.quit();
//        driver = null;
    }

}
