package ru.vlapin.experiments.poi.xlsx;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class FirstTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeEach
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
    private void myFirstTest(){
      //login
      driver.get("https://anm.test.russianpost.ru/#/mailin");
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

    private void stop(){

      driver.quit();
      driver = null;
    }

}
