import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactsTest {

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
    public void clickContacts() throws InterruptedException {
        driver.get("https://www.multitran.com/");
        WebElement btn = driver.findElement(By.xpath("/html/body/div/div[4]/a[5]"));
        btn.click();
        Thread.sleep(2000);
        WebElement header = driver.findElement(By.xpath("/html/body/div/div[5]/table/tbody/tr/td/table/tbody/tr[1]/td[1]"));
        assertEquals("Виктория Орлова", header.getText());
    }
}
