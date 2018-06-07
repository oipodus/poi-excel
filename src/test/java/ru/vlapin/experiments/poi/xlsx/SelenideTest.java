package ru.vlapin.experiments.poi.xlsx;

import com.codeborne.selenide.Configuration;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    static String userName;
    static String password;
    static String urlPath;

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
        Configuration.browser = "ie";

    }

    @BeforeEach
    void setup() {

        open(urlPath);
    }
    //тест проверяет ограничения по весу реализованные в задаче https://youtrack.tools.russianpost.ru/issue/EASRD-2948
    @Test
    public void checkWeightLimitForRPO() {
        Map<String, List<String>> sheet = ExcelReader.toListMap("/Weights_Limits.xlsx", "Sheet2");
    //    String temp = sheet.get("weight1");
        $("#userName").setValue(userName);
        $("#password").setValue(password);
        $(By.xpath("//button[text()='Вход']")).click();
        for (int i = 0; i < sheet.get("typeOfRPO").size(); i++) {
            $(By.name("trackId")).setValue(sheet.get("typeOfRPO").get(i));
            $("#txtWeight").setValue(sheet.get("maxWeight").get(i));
            $(".text-danger.ng-scope").shouldHave(visible, text("Неправильный вес."));
            $("#txtWeight").setValue(sheet.get("maxWeight").get(i));
        }
        $("#receiverindex").setValue("190000");
        $(".text-danger.ng-scope").shouldNotBe(visible);
    }

    @AfterEach
    void TearDown() {

    }


}
