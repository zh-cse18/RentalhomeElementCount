package Practice;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


public class Practice {

        public static int eid = 0;
        public static int tid = 0;
        public static boolean flag_Home = true;
        public static boolean flag_details = true;
        public static boolean flag_listing = true;
        public static String oztokenpopUp;
        public static String oztokenAmenities;
        @SuppressWarnings("Duplicates")
        public String URL, Node;
        public WebDriver driver;
        public String oztokenAmenitiesHigh;
        public String oztokenAmenitiesLow;
        public PrintWriter writer;
        protected ThreadLocal<RemoteWebDriver> threadDriver = null;
        Class<?> clazz;
        Object config;
        String nodeUrl;
        String haRentalhomes = "8111003";
        String RENTALHOMES = "rentalhomes";
        String bcRentalhomes = "1482827";



        @BeforeMethod
        public void setUp1() throws Exception {
            String driverLocation="/home/w3e-06/Downloads/chromedriver";
            System.setProperty("webdriver.chrome.driver", driverLocation );
            driver = new ChromeDriver();


        }

        @Test()
        public void RentalhomesDetailsVrboCheck() throws InterruptedException, IOException, NoSuchFieldException, IllegalAccessException {
            driver.manage().window().maximize();
            driver.get("https://www.rentalhomes.com/canada");
            Dimension screenRes = new Dimension(1105, 885);
            driver.manage().window().setSize(screenRes);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(3000);


            driver.findElement(By.xpath(".//button[@class='accept_gdpr']")).click();
            Thread.sleep(2000);
            Thread.sleep(1000);
            try {
                driver.findElement(By.xpath("//a[@title='Next Page']"));
                driver.findElement(By.xpath("//a[@title='Next Page']")).click();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Thread.sleep(3000);
        }



//    @Test
//    public void mainTest() throws InterruptedException {
//        driver.get(   "https://www.rentalhomes.com/listing?q=paris");
//        driver.findElement(By.className("apply-btn")).click();
//
//
//        RedirectUrl = driver.findElements(By.xpath("//*[@class='js-individual-link optimizely-view-button']"));
//
//        for(int i=0;i<RedirectUrl.size();i++){
//
//
//            if (RedirectUrl.get(i).getAttribute("href").contains("HA-") && RedirectUrl.get(i).getAttribute("href").contains("sf=1")) {
//                homeaway++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("HA-") && RedirectUrl.get(i).getAttribute("href").contains("sf=2")) {
//                vrbo++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("BC-")) {
//                booking1++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("AB-")) {
//                airbnb++;
//            }
//        }
//
//
//        System.out.println("\n1 : AirBnb sites parcentege = "+(airbnb*100)/48 + "------------- Api percentenge"   + airbnbOK );
//        System.out.println("\n2 : HomeaAway sites  percentege= "+ (homeaway*100)/48 + "---------- Api percentenge"   + homeawayOK);
//        System.out.println("\n3 : Booking sites  percentenge= " +(booking1*100)/48 + "----------- Api percentenge" + bookingOk);
//        System.out.println("\n4 : Vrbo sites  percentenge= " + (vrbo*100)/48+ " %-------------------------------------");
//
//        airbnb=0;
//        booking1=0;
//        homeaway=0;
//        vrbo=0;
//
//
//
//
////        Dimension screenRes = new Dimension(1105, 885);
////        driver.manage().window().setSize(screenRes);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//
//
//        driver.findElement(By.xpath(".//button[@class='accept_gdpr']")).click();
//
//        Thread.sleep(2000);
//        Thread.sleep(1000);
//        try{
////            driver.findElement(By.xpath("//a[@title='Next Page']")).click();
//            driver.findElement(By.xpath("//a[@data-page='6']")).click();
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//        Thread.sleep(3000);
//
//
//
//        RedirectUrl = driver.findElements(By.xpath("//*[@class='js-individual-link optimizely-view-button']"));
//
//        for(int i=0;i<RedirectUrl.size();i++){
//
//
//            if (RedirectUrl.get(i).getAttribute("href").contains("HA-") && RedirectUrl.get(i).getAttribute("href").contains("sf=1")) {
//                homeaway++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("HA-") && RedirectUrl.get(i).getAttribute("href").contains("sf=2")) {
//                vrbo++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("BC-")) {
//                booking1++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("AB-")) {
//                airbnb++;
//            }
//        }
////        System.out.println("\n1 : AirBnb sites number = "+airbnb);
////        System.out.println("\n2 : HomeaAway sites number ="+ homeaway);
////        System.out.println("\n3 : Booking sites number =" + booking1);
////        System.out.println("\n4 : Vrbo sites number =" + vrbo);
//
//
//
//        System.out.println("\n1 : AirBnb sites parcentege = "+(airbnb*100)/48 + "------------- Api percentenge"   + airbnbOK );
//        System.out.println("\n2 : HomeaAway sites  percentege= "+ (homeaway*100)/48 + "---------- Api percentenge"   + homeawayOK);
//        System.out.println("\n3 : Booking sites  percentenge= " +(booking1*100)/48 + "----------- Api percentenge" + bookingOk);
//        System.out.println("\n4 : Vrbo sites  percentenge= " + (vrbo*100)/48+ " %");
//
//    }

//    public void firstPageData(){
//        driver.get(   "https://www.rentalhomes.com/listing?q=paris");
//        driver.findElement(By.className("apply-btn")).click();
//
//
//        RedirectUrl = driver.findElements(By.xpath("//*[@class='js-individual-link optimizely-view-button']"));
//
//        for(int i=0;i<RedirectUrl.size();i++){
//
//
//            if (RedirectUrl.get(i).getAttribute("href").contains("HA-") && RedirectUrl.get(i).getAttribute("href").contains("sf=1")) {
//                homeaway++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("HA-") && RedirectUrl.get(i).getAttribute("href").contains("sf=2")) {
//                vrbo++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("BC-")) {
//                booking1++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("AB-")) {
//                airbnb++;
//            }
//        }
//
//
//        System.out.println("\n1 : AirBnb sites parcentege = "+(airbnb*100)/48 + "------------- Api percentenge"   + airbnbOK );
//        System.out.println("\n2 : HomeaAway sites  percentege= "+ (homeaway*100)/48 + "---------- Api percentenge"   + homeawayOK);
//        System.out.println("\n3 : Booking sites  percentenge= " +(booking1*100)/48 + "----------- Api percentenge" + bookingOk);
//        System.out.println("\n4 : Vrbo sites  percentenge= " + (vrbo*100)/48+ " %-------------------------------------");
//
//        airbnb=0;
//        booking1=0;
//        homeaway=0;
//        vrbo=0;
//
//    }
//
//
//
//    public void secondPageClickandData(){
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//
//
//        driver.findElement(By.xpath(".//button[@class='accept_gdpr']")).click();
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        try{
////            driver.findElement(By.xpath("//a[@title='Next Page']")).click();
//            driver.findElement(By.xpath("//a[@data-page='6']")).click();
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//
//
//
//        RedirectUrl = driver.findElements(By.xpath("//*[@class='js-individual-link optimizely-view-button']"));
//
//        for(int i=0;i<RedirectUrl.size();i++){
//
//
//            if (RedirectUrl.get(i).getAttribute("href").contains("HA-") && RedirectUrl.get(i).getAttribute("href").contains("sf=1")) {
//                homeaway++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("HA-") && RedirectUrl.get(i).getAttribute("href").contains("sf=2")) {
//                vrbo++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("BC-")) {
//                booking1++;
//            }
//            if (RedirectUrl.get(i).getAttribute("href").contains("AB-")) {
//                airbnb++;
//            }
//        }
////        System.out.println("\n1 : AirBnb sites number = "+airbnb);
////        System.out.println("\n2 : HomeaAway sites number ="+ homeaway);
////        System.out.println("\n3 : Booking sites number =" + booking1);
////        System.out.println("\n4 : Vrbo sites number =" + vrbo);
//
//
//
//        System.out.println("\n1 : AirBnb sites parcentege = "+(airbnb*100)/48 + "------------- Api percentenge"   + airbnbOK );
//        System.out.println("\n2 : HomeaAway sites  percentege= "+ (homeaway*100)/48 + "---------- Api percentenge"   + homeawayOK);
//        System.out.println("\n3 : Booking sites  percentenge= " +(booking1*100)/48 + "----------- Api percentenge" + bookingOk);
//        System.out.println("\n4 : Vrbo sites  percentenge= " + (vrbo*100)/48+ " %");
//
//
//
//    }

//    @Test
//    public  void firstAndSecondPage(){
//
//        firstPageData();
//        secondPageClickandData();
//    }
    }

