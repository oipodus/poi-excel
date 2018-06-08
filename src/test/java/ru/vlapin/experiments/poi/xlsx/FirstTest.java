package ru.vlapin.experiments.poi.xlsx;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
class FirstTest {
    static String userName;
    static String password;
    static String urlPath;
    WebDriver driver;
    @BeforeAll
    @SneakyThrows
    void start() {
        val properties = new Properties();
        @Cleanup val inputStream = SeleniumTestIE.class.getResourceAsStream("/credentials.properties");
        properties.load(inputStream);
        userName = properties.getProperty("userName");
        password = properties.getProperty("password");
        urlPath = properties.getProperty("urlPath");
//        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
//        caps.setCapability("ignoreZoomSetting", true);
//        driver = new InternetExplorerDriver(caps);
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver(new ChromeOptions()
                .addArguments("--headless"));
    }
    @Test
    void myFirstTest() {
        driver.findElement(By.name("userName")).sendKeys(userName);
        // driver.findElement(By.name("password")).sendKeys(password);
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
