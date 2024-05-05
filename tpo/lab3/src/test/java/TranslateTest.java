import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslateTest {

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
    public void translateWord() throws InterruptedException {
        driver.get("https://www.multitran.com/");
        WebElement btn = driver.findElement(By.xpath("/html/body/div/div[4]/a[1]"));
        btn.click();
        Thread.sleep(2000);
        WebElement from = driver.findElement(By.xpath("/html/body/div/div[5]/form/select[1]"));
        from.sendKeys("Русский");
        WebElement to = driver.findElement(By.xpath("/html/body/div/div[5]/form/select[2]"));
        to.sendKeys("Английский");
        WebElement input = driver.findElement(By.xpath("/html/body/div/div[5]/form/span/input"));
        input.sendKeys("Собака");
        WebElement enter = driver.findElement(By.xpath("/html/body/div/div[5]/form/input[3]"));
        enter.click();
        WebElement header = driver.findElement(By.xpath("/html/body/div/div[5]/table[1]/tbody/tr[3]/td[2]/a[1]"));
        assertEquals("dog", header.getText());
    }
}