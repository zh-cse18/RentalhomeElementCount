package Work;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobileAllSitesListingPageContentCompare {



    List<WebElement> RedirectUrl = new ArrayList<WebElement>();
    String driverLocation = "/home/w3e-06/Downloads/chromedriver";
    String[] allSites = {"RBO", "RH", "SOR",
            "BV", "PET", "STY",
            "EXEC", "OAHU", "ALO",
            "BVAU", "AVI", "BVUK",
            "VHR", "RHDE", "MLFR","SR17"};

    String jsonFromApi;

    int bookingFromApi = 0 ,homeawayFromApi = 0 ,airbnbFromApi = 0;
    int airbnbFrompage = 0;
    int bookingFromPage = 0;
    int homeawayFromPage = 0;
    int vrboFromPage=0;

    int airbnbPercentdiff,homeawayParcentdiff,bookingParcentdiff;

    String []Url={
            "https://www.rentbyowner.com/listing?q=paris",
            "https://www.rentalhomes.com/listing?q=paris",
            "https://www.selloffrentals.com/listing?q=paris",

            "https://www.bedroomvillas.com/listing?q=paris",
            "https://www.Petfriendly.io/listing?q=paris",
            "https://www.Stays.io/listing?q=paris",

            "https://www.execstays.com/listing?q=paris",
            "https://www.oahu.com/listing?q=paris",
            "https://www.alojamiento.io/listing?q=paris",

            "https://www.bedroomvillas.com.au/listing?q=paris",
            "https://www.alohavillas.io/listing?q=paris",
            "https://www.bedroomvillas.co.uk/listing?q=paris",

            "https://www.vacationhome.rent/listing?q=paris",
            "https://www.rentalhomes24.de/listing?q=paris",
            "https://www.meilleureslocations.fr/listing?q=paris",
            "https://www.Summerrentals.io/listing?q=paris"

    };



    @BeforeMethod
    public void  test(){


    }
    @Test
    public void  OkHttpClient() throws FileNotFoundException {

        System.setProperty("webdriver.chrome.driver", driverLocation );

        Map<String, String> mobileEmulation = new HashMap<String, String>();

        mobileEmulation.put("deviceName", "Nexus 5");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver driver = new ChromeDriver(chromeOptions);

        PrintWriter printer=new PrintWriter("Mobilecompare.txt");
        printer.println("| SiteName      |   Partner    | Page No     | Page Ratio  |  Api Ratio | Difference |");
        printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");


        for(int k=0,i=0;i<allSites.length;i++,k++) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://stw.stays.io/v3/get-sts-config/?site="+ allSites[i])
                    .get()
                    .addHeader("authorization", "Basic c3RzOiNzdHNsZWZ0dHJhdmVsIw==")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "af5ec5a0-1ac5-ca22-2b44-e2fb21979e10")
                    .build();
            Response response;

            {
                try {
                    response = client.newCall(request).execute();

                    jsonFromApi = response.body().string();

                    JSONParser parser = new JSONParser();
                    try {

                        JSONObject jsonOb = (JSONObject) parser.parse(jsonFromApi);
                        String setwithratio = jsonOb.get("set_with_ratio").toString();

                        JSONObject ratioOb = (JSONObject) parser.parse(setwithratio);
                        String Stringratio = ratioOb.get("ratio").toString();

                        JSONObject brand_defined_sets_ratioOb = (JSONObject) parser.parse(Stringratio);
                        String Strbrand_defined_sets_ratio = brand_defined_sets_ratioOb.get("brand_defined_sets_ratio").toString();

                        JSONObject defaultOb = (JSONObject) parser.parse(Strbrand_defined_sets_ratio);
                        String strdefault1 = defaultOb.get("default").toString();

                        JSONObject desktopOb = (JSONObject) parser.parse(strdefault1);
                        String desk1 = desktopOb.get("mobile").toString();

                        JSONObject bookingOb = (JSONObject) parser.parse(desk1);

                        bookingFromApi = Integer.parseInt(bookingOb.get("11").toString());
                        airbnbFromApi = Integer.parseInt(bookingOb.get("16").toString());
                        homeawayFromApi = Integer.parseInt(bookingOb.get("12").toString().substring(6, 8));

//
////                        System.out.println(bookingFromApi);
////                        System.out.println(airbnbFromApi);
//                        System.out.println(homeawayFromApi);

                        driver.get(Url[k]);
                        try{
                            driver.findElement(By.className("apply-btn")).click();}
                        catch ( Exception e){

                        }

                        RedirectUrl = driver.findElements(By.xpath("//*[@class='js-individual-link optimizely-view-button']"));

                        for (int j = 0; j < RedirectUrl.size(); j++) {


                            if (RedirectUrl.get(j).getAttribute("href").contains("HA-") && RedirectUrl.get(j).getAttribute("href").contains("sf=1")) {
                                homeawayFromPage++;
                            }

                            if (RedirectUrl.get(j).getAttribute("href").contains("BC-")) {
                                bookingFromPage++;
                            }
                            if (RedirectUrl.get(j).getAttribute("href").contains("AB-")) {
                                airbnbFrompage++;
                            }
                        }

                        System.out.println( i+1 +" : " +allSites[i]);
                        System.out.println("=========1st Page=======");
                        airbnbPercentdiff= (airbnbFrompage*100)/24 - airbnbFromApi;
                        homeawayParcentdiff =(homeawayFromPage*100)/24 - homeawayFromApi;
                        bookingParcentdiff= (bookingFromPage*100)/24 - bookingFromApi;

                        if(airbnbPercentdiff>5 ||airbnbPercentdiff < -5) {
                            printer.println("|"+ allSites[i] +"           |  Airbnb      |     1st     |   "+(airbnbFrompage*100)/24+"         |    "+ airbnbFromApi +"      | "+airbnbPercentdiff +"        |");
                            printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");
                        }

                        if(bookingParcentdiff>5  ||bookingParcentdiff < -5) {
                            printer.println("|" + allSites[i] + "           |  Booking     |     1st     |   " + (bookingFromPage * 100) / 24 + "         |    " + bookingFromApi + "      | " + bookingParcentdiff + "        |");
                            printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");
                        }
                        if(homeawayParcentdiff>5 ||homeawayParcentdiff < -5) {
                            printer.println("|"+ allSites[i] +"           |  HomeAway     |     1st     |   "+(homeawayFromPage*100)/24 +"         |    "+ homeawayFromApi +"      | "+homeawayParcentdiff +"        |");
                            printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");
                        }
                        airbnbFrompage=0;
                        bookingFromPage=0;
                        homeawayFromPage=0;
                        vrboFromPage=0;



//                        Dimension screenRes = new Dimension(1105, 885);
//                        driver.manage().window().setSize(screenRes);
                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");


                        try{
                            driver.findElement(By.xpath(".//button[@class='accept_gdpr']")).click();
                            driver.findElement(By.xpath("//a[@data-page='2']")).click();
                        }
                        catch (Exception e) {

                        }

                        RedirectUrl = driver.findElements(By.xpath("//*[@class='js-individual-link optimizely-view-button']"));

                        for(int m=0;m<RedirectUrl.size();m++){


                            if (RedirectUrl.get(m).getAttribute("href").contains("HA-") && RedirectUrl.get(m).getAttribute("href").contains("sf=1")) {
                                homeawayFromPage++;
                            }

                            if (RedirectUrl.get(m).getAttribute("href").contains("BC-")) {
                                bookingFromPage++;
                            }
                            if (RedirectUrl.get(m).getAttribute("href").contains("AB-")) {
                                airbnbFrompage++;
                            }
                        }

                        Thread.sleep(20000 );

                        System.out.println(" \n====== 2nd Page=========");
                        airbnbPercentdiff= (airbnbFrompage*100)/24 - airbnbFromApi;
                        homeawayParcentdiff =(homeawayFromPage*100)/24 - homeawayFromApi;
                        bookingParcentdiff= (bookingFromPage*100)/24 - bookingFromApi;

                        if(airbnbPercentdiff>5 ||airbnbPercentdiff < -5) {
                            printer.println("|"+ allSites[i] +"           |  Airbnb      |     2nd     |   "+(airbnbFrompage*100)/24+"         |    "+ airbnbFromApi +"      | "+airbnbPercentdiff +"        |");
                            printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");
                        }

                        if(bookingParcentdiff>5  ||bookingParcentdiff < -5) {
                            printer.println("|" + allSites[i] + "           |  Booking     |     2nd     |   " + (bookingFromPage * 100) / 24 + "         |    " + bookingFromApi + "      | " + bookingParcentdiff + "        |");
                            printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");
                        }
                        if(homeawayParcentdiff>5 ||homeawayParcentdiff < -5) {
                            printer.println("|"+ allSites[i] +"           |  HomeAway     |     2nd     |   "+(homeawayFromPage*100)/24 +"         |    "+ homeawayFromApi +"      | "+homeawayParcentdiff +"        |");
                            printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");
                        }


                        airbnbFrompage=0;
                        bookingFromPage=0;
                        homeawayFromPage=0;
                        vrboFromPage=0;


                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        printer.close();
    }


    @AfterMethod
    public void afterTest(){


    }

}
