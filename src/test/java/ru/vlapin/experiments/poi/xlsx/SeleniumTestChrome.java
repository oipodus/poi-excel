package ru.vlapin.experiments.poi.xlsx;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SeleniumTestChrome {
    private static WebDriver driver;
    static String userName;
    static String password;
    static String urlPath;

    @BeforeAll
    @SneakyThrows
    public void setup() {
        val properties = new Properties();
        @Cleanup val inputStream = SeleniumTestIE.class.getResourceAsStream("/credentials.properties");
        properties.load(inputStream);
        userName = properties.getProperty("userName");
        password = properties.getProperty("password");
        urlPath = properties.getProperty("urlPath");

        File file = new File("C:\\Tools\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(urlPath);
    }
    @Test
    public void userLogin() {
        WebElement loginField = driver.findElement(By.name("userName"));
        loginField.sendKeys(userName);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Вход']"));
        loginButton.click();
        WebElement trackId = driver.findElement(By.name("TrackId"));
        trackId.sendKeys("11928524498911");
        WebElement forma7p = driver.findElement(By.cssSelector(".pull-right printer-lbl ng-scope)"));
        forma7p.click();
        //  String mailUser = profileUser.getText();
       // Assertions.("autotestorgua@ukr.net", mailUser);
    }
    @AfterEach
    public void tearDown() {
        WebElement menuUser = driver.findElement(By.cssSelector(".login-button__menu-icon"));
        menuUser.click();
        WebElement logoutButton = driver.findElement(By.id("login__logout"));
        logoutButton.click();
        driver.quit();
    }
}
