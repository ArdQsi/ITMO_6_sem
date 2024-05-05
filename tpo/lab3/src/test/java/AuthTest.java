import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {
    WebDriver driver;

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
    public void successfulAuth() {
        driver.get("https://www.multitran.com/");
        WebElement btn = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/a[1]"));
        btn.click();
        WebElement login = driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[1]/td[2]/input"));
        login.sendKeys("Ard");
        WebElement password = driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[2]/td[2]/input"));
        password.sendKeys("grrkxYSTJ6!KL2Y");
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[3]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[4]/td/input")).click();
        WebElement header = driver.findElement(By.xpath("//a[contains(text(),'Ard')]"));
        assertEquals("Ard", header.getText());
    }

    @Test
    public void logout() {
        driver.get("https://www.multitran.com/");
        WebElement btn = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/a[1]"));
        btn.click();
        WebElement login = driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[1]/td[2]/input"));
        login.sendKeys("Ard");
        WebElement password = driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[2]/td[2]/input"));
        password.sendKeys("grrkxYSTJ6!KL2Y");
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[3]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[4]/td/input")).click();
        WebElement header = driver.findElement(By.xpath("//a[contains(text(),'Выход')]"));
        assertEquals("Выход", header.getText());
    }

    @Test
    public void failedAuth() {
        driver.get("https://www.multitran.com/");
        WebElement btn = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/a[1]"));
        btn.click();
        WebElement login = driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[1]/td[2]/input"));
        login.sendKeys("Ard");
        WebElement password = driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[2]/td[2]/input"));
        password.sendKeys("wrongPassword");
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[3]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[4]/td/input")).click();
        WebElement header = driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/table/tbody/tr[1]/td/strong/span"));
        assertEquals("Неверный пароль", header.getText());
    }
}
