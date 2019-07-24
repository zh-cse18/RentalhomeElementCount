package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class interntask {

    WebDriver driver;
    String baseUrl = "https://www.rentalhomes.com/property/individual/AB-29131127?utm_source";
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

        Thread.sleep(2000);
        // Click on the start date
        driver.findElement(By.name("date_start")).click();

        WebElement allMonthData = driver.findElement(By.className("month5"));
        List<WebElement> allDay = allMonthData.findElements(By.tagName("td"));

        // select the first date
        for (WebElement Day : allDay) {
            if(Day.getText().equals("26")){
                Day.findElement(By.xpath("//div[contains(text(), '26')]")).click();
            }
        }

        Thread.sleep(6000);
        // select the second date
        for (WebElement Day : allDay) {
            if(Day.getText().equals("28")){
                Day.findElement(By.xpath("//div[contains(text(), '28')]")).click();
            }
        }
        Thread.sleep(6000);
        //Click on Continue Button
        driver.findElement(By.xpath("//button[@class = 'search-button js_redirect_btn2']")).click();
        Thread.sleep(6000);

        // Get all the url
        int totalurl = 0;
        System.out.println("Here the all URL Title And URL \n");
        for(String allUrl :  driver.getWindowHandles())
        {
            driver.switchTo().window(allUrl);
            totalurl +=1;
            System.out.println(totalurl + ". No Url Title = " + driver.getTitle());
            System.out.println("Url = " + driver.getCurrentUrl());

            //Overlay Control
            if(!driver.findElements(By.xpath("//div[@class='IM_overlay_pane IM_overlay_im_pane']")).isEmpty()){
                Thread.sleep(10000);
                driver.findElement(By.xpath("//button[@class='IM_overlay_close']")).click();

            }
            else{
                System.out.println("No overlay found here");
            }
            System.out.println("\n");
        }
    }

    @AfterMethod
    public void afterTest(){
        driver.close();
    }
}

