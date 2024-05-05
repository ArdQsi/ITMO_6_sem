import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForumTest {

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
    public void clickForum() throws InterruptedException {
        driver.get("https://www.multitran.com/");
        WebElement from = driver.findElement(By.xpath("/html/body/div/div[5]/form/select[1]"));
        from.sendKeys("Русский");
        WebElement to = driver.findElement(By.xpath("/html/body/div/div[5]/form/select[2]"));
        to.sendKeys("Английский");
        WebElement btn = driver.findElement(By.xpath("/html/body/div/div[4]/a[2]"));
        btn.click();
        Thread.sleep(2000);
        WebElement header = driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/table/tbody/tr[2]/td[3]/a/span"));
        assertEquals("✎ Создать тему", header.getText());
    }
}
