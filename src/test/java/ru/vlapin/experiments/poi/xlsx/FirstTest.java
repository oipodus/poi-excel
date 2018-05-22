package ru.vlapin.experiments.poi.xlsx;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
    public void start()
        {
    DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
    caps.setCapability("ignoreZoomSetting", true);
    driver = new InternetExplorerDriver(caps);

    //ChromeOptions options = new ChromeOptions();
    //options.addArguments("--headless");
    //WebDriver driver = new ChromeDriver(options);

    }
    @Test
    public void myFirstTest(){


    }

    @After

    public void stop(){

      driver.quit();
      driver = null;
    }

}
