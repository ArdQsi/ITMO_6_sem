import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChooseLanguageTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void chooseLanguage() throws InterruptedException {
        driver.get("https://www.multitran.com/");
        WebElement btn = driver.findElement(By.xpath("//a[contains(text(),'Russian')]"));
        btn.click();
        Thread.sleep(3000);
        WebElement language = driver.findElement(By.xpath("/html/body/div/div[5]/table[1]/tbody/tr[1]/td/a"));
        language.click();
        WebElement header = driver.findElement(By.xpath("//a[contains(text(),'English')]"));
        assertEquals("English", header.getText());
    }
}