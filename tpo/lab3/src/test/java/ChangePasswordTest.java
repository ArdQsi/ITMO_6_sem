import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangePasswordTest {

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
    public void changePasswordWithErrorEmail() throws InterruptedException {
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
        WebElement change = driver.findElement(By.xpath("//a[contains(text(),'Изменить пароль')]"));
        change.click();
        WebElement loginput = driver.findElement(By.xpath("/html/body/div/div[5]/div[1]/form/table/tbody/tr[2]/td[2]/input"));
        loginput.sendKeys("Hello");
        WebElement inputPassword = driver.findElement(By.xpath("/html/body/div/div[5]/div[1]/form/table/tbody/tr[3]/td[2]/div[1]/input"));
        inputPassword.sendKeys("1");
        WebElement inputPasswordTwo = driver.findElement(By.xpath("/html/body/div/div[5]/div[1]/form/table/tbody/tr[4]/td[2]/input"));
        inputPassword.sendKeys("1");
        WebElement save = driver.findElement(By.xpath("/html/body/div/div[5]/div[1]/form/table/tbody/tr[5]/td[1]/input"));
        save.click();
        WebElement header = driver.findElement(By.xpath("/html/body/div/div[5]/span"));
        assertEquals("Адрес электронной почты содержит ошибки", header.getText());
    }
}