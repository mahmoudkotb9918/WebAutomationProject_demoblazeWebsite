package demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Cart {
    WebDriver driver = null;
    @BeforeTest
    public void OpenChrome() throws InterruptedException
    {
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.out.println(chromePath);
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }
    @Test
    public void Login() throws InterruptedException {
        driver.navigate().to("https://www.demoblaze.com/index.html");
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("Alaa Abdellatif");
        driver.findElement(By.id("loginpassword")).sendKeys("1234567890");
        driver.findElement(By.cssSelector("button[onclick=\"logIn()\"]")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.demoblaze.com/index.html");
    }
    @Test
    public void Cart() throws InterruptedException
    {
        //Press on Cart
        driver.findElement(By.id("cartur")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.demoblaze.com/cart.html");

        //Press on Place Order
        driver.findElement(By.cssSelector("button[data-toggle=\"modal\"]")).click();

        //Fill in the data
        driver.findElement(By.id("name")).sendKeys("Alaa Abdellatif");
        driver.findElement(By.id("country")).sendKeys("Egypt");
        driver.findElement(By.id("city")).sendKeys("Alexandria");
        driver.findElement(By.id("card")).sendKeys("1234567890123456");
        driver.findElement(By.id("month")).sendKeys("September");
        driver.findElement(By.id("year")).sendKeys("1998");

        //Press on Purchase
        driver.findElement(By.cssSelector("button[onclick=\"purchaseOrder()\"]")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("button[onclick=\"purchaseOrder()\"]")).isDisplayed());

        //Press on OK
        driver.findElement(By.cssSelector("button[onclick=\"purchaseOrder()\"]")).click();
    }
    @AfterTest
    public void CloseDriver () throws InterruptedException
    {
        Thread.sleep(3000);
        driver.quit();
    }
}
