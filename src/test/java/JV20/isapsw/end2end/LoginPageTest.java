package JV20.isapsw.end2end;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginPageTest {
    private WebDriver driver;

    private String base;

    @BeforeEach
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\2\\IdeaProjects\\ISA-projekat\\src\\test\\java\\JV20\\isapsw\\resources\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("marionette",true);
        this.driver = new ChromeDriver(chromeOptions);

        driver.manage().window().maximize();
        this.base = "http://localhost:8080";
    }

    @Test
    public void loginTest(){
        setUp();

        driver.navigate().to(base+"/prijava.html");

        (new WebDriverWait(driver, 100))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).sendKeys("lekar");

        (new WebDriverWait(driver, 100))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        driver.findElement(By.id("password")).sendKeys("marina");

        (new WebDriverWait(driver, 100))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("prijava_btn")));
        driver.findElement(By.id("prijava_btn")).click();

        driver.navigate().to(base+"/pocetna.html");

    }

    @Test
    public void RegistrationTest(){
        setUp();

        driver.navigate().to(base+"/registracija.html");

        (new WebDriverWait(driver, 1000))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("first_name")));
        driver.findElement(By.id("first_name")).sendKeys("Stefan");

        (new WebDriverWait(driver, 1000))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("last_name")));
        driver.findElement(By.id("last_name")).sendKeys("Sumar");

        (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        driver.findElement(By.id("email")).sendKeys("test.mejl365@gmail.com");

        (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("jbo")));
        driver.findElement(By.id("jbo")).sendKeys("120");

        (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).sendKeys("stefansumar");

        (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        driver.findElement(By.id("password")).sendKeys("marina");

        (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("password_confirm")));
        driver.findElement(By.id("password_confirm")).sendKeys("marina");

        (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.presenceOfElementLocated(By.id("birthday")));
        driver.findElement(By.id("birthday")).sendKeys("23/04/1997");

        driver.navigate().to(base+"/afterReg.html");
    }


}
