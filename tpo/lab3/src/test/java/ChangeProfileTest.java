import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeProfileTest {

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
    public void changeProfile() throws InterruptedException {
        driver.get("https://www.multitran.com/");
        WebElement btn = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/a[1]"));
        btn.click();
        Thread.sleep(5000);
        WebElement login = driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[1]/td[2]/input"));
        login.sendKeys("Ard");
        WebElement password = driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[2]/td[2]/input"));
        password.sendKeys("grrkxYSTJ6!KL2Y");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[3]/td/input")).click();
        driver.findElement(By.xpath("//*[@id=\"start\"]/div[5]/form/table/tbody/tr[4]/td/input")).click();
        Thread.sleep(5000);
        WebElement profile = driver.findElement(By.xpath("//a[contains(text(),'Ard')]"));
        profile.click();
        WebElement change = driver.findElement(By.xpath("//a[contains(text(),'Изменить данные профиля')]"));
        change.click();
        WebElement textarea = driver.findElement(By.xpath("/html/body/div/div[5]/form/table/tbody/tr[1]/td[2]/textarea"));
        textarea.sendKeys("Hello");
        WebElement inputPassword = driver.findElement(By.xpath("/html/body/div/div[5]/form/table/tbody/tr[2]/td[2]/input"));
        inputPassword.sendKeys("grrkxYSTJ6!KL2Y");
        WebElement save = driver.findElement(By.xpath("/html/body/div/div[5]/form/table/tbody/tr[4]/td[2]/input"));
        save.click();
        WebElement header = driver.findElement(By.xpath("/html/body/div/div[5]/div[1]/span"));
        assertEquals("Запись сохранена", header.getText());
    }
}