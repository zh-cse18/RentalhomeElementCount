package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class fbCreateAccountPageAutomation {

    WebDriver driver;
    String baseUrl = "https://www.facebook.com";
    String webdriverPath = "/home/w3e-06/Downloads/chromedriver";


    @BeforeMethod
    public void  test(){
        System.setProperty("webdriver.chrome.driver", webdriverPath );
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void mainTest() throws InterruptedException {
        driver.get(baseUrl);

        driver.findElement(By.linkText("Sign Up")).click();

        driver.findElement(By.linkText("Forgotten account?")).click();
        driver.findElement(By.id("identify_email")).sendKeys("01746484619");
        driver.findElement(By.name("did_submit")).click();
        WebDriverWait wait=new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));



    }

    @AfterMethod
    public void afterTest(){
//        driver.quit();
    }
}
