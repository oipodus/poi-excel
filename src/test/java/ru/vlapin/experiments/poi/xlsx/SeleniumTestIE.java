package ru.vlapin.experiments.poi.xlsx;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@FieldDefaults(level = PRIVATE)
class SeleniumTestIE {
    static WebDriver driver;
    static String userName;
    static String password;
    static String urlPath;
    static Wait<WebDriver> wait;

    @BeforeAll
    @SneakyThrows
    static void setupAll() {
        val properties = new Properties();
        @Cleanup val inputStream = SeleniumTestIE.class.getResourceAsStream("/credentials.properties");
        properties.load(inputStream);
        userName = properties.getProperty("userName");
        password = properties.getProperty("password");
        urlPath = properties.getProperty("urlPath");

        // указываем путь к драйверу
        File file = new File("C:\\Tools\\IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        //запускаем браузер
        driver = new InternetExplorerDriver();
        // максимизируем окно
        driver.manage().window().maximize();
        //дополнительные настройки ожидания, для долго загружаемых элементов

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        //настраиваем ожидание элемента по умолчанию
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //переходим на страницу сайта

    }

    @BeforeEach
    void setup() {

        driver.navigate().to(urlPath);
    }

    @Test
    public void userLogin() {
        Map<String, List<String>> sheet = ExcelReader.toListMap("/Weights_Limits.xlsx", "Sheet1");
        WebElement loginField = driver.findElement(By.name("userName"));
        loginField.clear();
        loginField.sendKeys(userName);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Вход']"));
        loginButton.click();
        WebElement trackId = driver.findElement(By.name("trackId"));
        trackId.sendKeys(sheet.get("shpi1").toString());

        WebElement weight = wait.until(driver -> driver.findElement(By.name("weight")));
        weight.sendKeys(sheet.get("weight1").toString());
        WebElement messageAboutLimitExceeded = wait.until(driver -> driver.findElement(By.cssSelector(".text-danger.ng-scope")));
        assertThat(messageAboutLimitExceeded.getText(), is("Неправильный вес."));
    }

    @AfterEach
    void TearDown() {
        if (driver != null)
            driver.quit();
    }

}
