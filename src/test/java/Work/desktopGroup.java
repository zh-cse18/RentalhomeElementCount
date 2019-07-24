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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class desktopGroup {
//
//    WebDriver driver;

    List<WebElement> RedirectUrl = new ArrayList<WebElement>();
    String driverLocation = "/home/w3e-06/Downloads/chromedriver";
    String[] allSites= {
            "RBO", "RH", "SOR",
            "BV", "PET", "STY",
            "EXEC", "OAHU", "ALO",
            "BVAU", "AVI", "BVUK",
            "VHR", "RHDE", "MLFR","SR17"
    };

    String jsonFromApi;
    int bookingFromApi = 0 ,homeawayFromApi = 0 ,airbnbFromApi = 0, varboFromApi = 0;

    int airbnbFrompage = 0;
    int bookingFromPage = 0;
    int homeawayFromPage = 0;
    int vrboFromPage = 0;

    int airbnbPercentdiff,homeawayParcentdiff,bookingParcentdiff,vrboParcentdiff;

    String []Url = {
            "https://www.rentbyowner.com/listing?q=paris&rg=13",
            "https://www.rentalhomes.com/listing?q=pariss&rg=13",
            "https://www.selloffrentals.com/listing?q=pariss&rg=13",

            "https://www.bedroomvillas.com/listing?q=pariss&rg=13",
            "https://www.Petfriendly.io/listing?q=pariss&rg=13",
            "https://www.Stays.io/listing?q=pariss&rg=13",

            "https://www.execstays.com/listing?q=pariss&rg=13",
            "https://www.oahu.com/listing?q=pariss&rg=13",
            "https://www.alojamiento.io/listing?q=pariss&rg=13",

            "https://www.bedroomvillas.com.au/listing?q=pariss&rg=13",
            "https://www.alohavillas.io/listing?q=pariss&rg=13",
            "https://www.bedroomvillas.co.uk/listing?q=pariss&rg=13",

            "https://www.vacationhome.rent/listing?q=pariss&rg=13",
            "https://www.rentalhomes24.de/listing?q=pariss&rg=13",
            "https://www.meilleureslocations.fr/listing?q=pariss&rg=13",
            "https://www.Summerrentals.io/listing?q=pariss&rg=13"

    };

    @BeforeMethod
    public void  test(){
//        System.setProperty("webdriver.chrome.driver", driverLocation );
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();

    }
    @Test
    public void  OkHttpClient() throws FileNotFoundException {

        for(int k = 0,i = 0;i < allSites.length; i++, k++) {

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

                        JSONObject json = (JSONObject) parser.parse(jsonFromApi);
                        String setwithratio = json.get("ratio_list").toString();

                        JSONObject ratio = (JSONObject) parser.parse(setwithratio);
                        String booking13 = ratio.get("13").toString();

                        JSONObject booking = (JSONObject) parser.parse(booking13);
                        String homeaway1 = booking.get("12").toString();

                        JSONObject home = (JSONObject) parser.parse(homeaway1);


                        bookingFromApi = Integer.parseInt(booking.get("11").toString());
                        airbnbFromApi = Integer.parseInt(booking.get("16").toString());

                        homeawayFromApi = Integer.parseInt(home.get("1").toString());
                        System.out.println(homeaway1);
                        varboFromApi = Integer.parseInt(home.get("2").toString());

                        System.out.println( i+1 +" : " + allSites[i]);



//                        driver.get(Url[k]);
//                        try{
//                            driver.findElement(By.className("apply-btn")).click();}
//                        catch ( Exception e){
//
//                        }
//
//                        RedirectUrl = driver.findElements(By.xpath("//*[@class='js-individual-link optimizely-view-button']"));
//
//                        airbnbFrompage = 0;
//                        bookingFromPage = 0;
//                        homeawayFromPage = 0;
//                        vrboFromPage = 0;
//
//                        for (int j = 0; j < RedirectUrl.size(); j++) {
//
//                            if (RedirectUrl.get(j).getAttribute("href").contains("HA-") && RedirectUrl.get(j).getAttribute("href").contains("sf=1")) {
//                                homeawayFromPage++;
//                            }
//                            if (RedirectUrl.get(j).getAttribute("href").contains("HA-") && RedirectUrl.get(j).getAttribute("href").contains("sf=2")) {
//                                vrboFromPage++;
//                            }
//                            if (RedirectUrl.get(j).getAttribute("href").contains("BC-")) {
//                                bookingFromPage++;
//                            }
//                            if (RedirectUrl.get(j).getAttribute("href").contains("AB-")) {
//                                airbnbFrompage++;
//                            }
//                        }
//
//                        System.out.println( i+1 +" : " + allSites[i]);
//                        airbnbPercentdiff = (airbnbFrompage*100)/48 - airbnbFromApi;
//                        homeawayParcentdiff =(homeawayFromPage*100)/48 - homeawayFromApi;
//                        bookingParcentdiff = (bookingFromPage*100)/48 - bookingFromApi;
//                        vrboParcentdiff = (vrboFromPage*100)/48 - varboFromApi;
//
//                        if(airbnbPercentdiff>5 ||airbnbPercentdiff < -5) {
//                            printer.println("|"+ allSites[i] +"           |  Airbnb      |     1st     |   "+(airbnbFrompage*100)/48 +"         |    "+ airbnbFromApi +"      | "+airbnbPercentdiff +"        |");
//                            printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");
//                        }
//
//                        if(bookingParcentdiff>5  ||bookingParcentdiff < -5) {
//                            printer.println("|" + allSites[i] + "           |  Booking     |     1st     |   " + (bookingFromPage * 100) / 48 + "         |    " + bookingFromApi + "      | " + bookingParcentdiff + "        |");
//                            printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");
//                        }
//                        if(homeawayParcentdiff>5 ||homeawayParcentdiff < -5) {
//                            printer.println("|"+ allSites[i] +"           |  HomeAway     |     1st     |   "+(homeawayFromPage*100)/48 +"         |    "+ homeawayFromApi +"      | "+homeawayParcentdiff +"        |");
//                            printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");
//                        }
//
//                        if(vrboParcentdiff>5  ||vrboParcentdiff < -5) {
//                            printer.println("|" + allSites[i] + "           |  Vrbo       |     1st     |   " + (vrboFromPage * 100) / 48 + "         |    " + varboFromApi + "      | " + vrboParcentdiff + "        |");
//                            printer.println("| :------------ |--------------| :-----------| ----------: |------------|------------|");
//                        }




                    } catch (ParseException e) {

                    } catch (Exception e) {

                    }


                } catch (IOException e) {

                }
            }
        }
//        printer.close();
    }


    @AfterMethod
    public void afterTest(){


    }
}
