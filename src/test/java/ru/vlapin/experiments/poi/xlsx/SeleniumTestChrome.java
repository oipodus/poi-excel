package ru.vlapin.experiments.poi.xlsx;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import java.util.concurrent.*;
import java.io.File;

public class SeleniumTestChrome {
    private static WebDriver driver;

    @BeforeEach
    public void setup() {
        File file = new File("C:\\Tools\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://anm.test.russianpost.ru/#/mailin");
    }
    @Test
    public void userLogin() {
        WebElement loginField = driver.findElement(By.name("userName"));
        loginField.sendKeys("test_emul_5");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("12345");
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
