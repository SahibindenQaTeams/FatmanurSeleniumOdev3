package com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import logs.LogsTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class Odev3{

    private static final Logger logger = LogManager.getLogger(LogsTest.class);
    protected WebDriver driver;

    @Test
    public void Case1() throws Exception {
        try {
            System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
            driver = new ChromeDriver();

            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
            driver.manage().timeouts().setScriptTimeout(1, TimeUnit.MINUTES);

            driver.get("https://sahibinden.com");
            logger.info("Sahibinden sitesine gidilir");

            List<Cookie> cookieList = new ArrayList<>();
            cookieList.add(new Cookie("testBox", "187", ".sahibinden.com", "/", null));
            cookieList.add(new Cookie("tbSite", "x", ".sahibinden.com", "/", null));
            cookieList.forEach(cookie -> driver.manage().addCookie(cookie));

            driver.manage().window().maximize();
            logger.info("Sayfa maximize edildi");

            driver.navigate().refresh();

            LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
            localStorage.setItem("feature_discovery_data",
                    "{\"add-to-favorites\":{\"count\":1,\"displayedAt\":1666694107010},\"extended\":true}");

            SessionStorage sessionStorage = ((WebStorage) driver).getSessionStorage();
            sessionStorage.setItem("feature_discovery_displayed", "true");
            logger.info("Onur Buldu aramay?? kaydet feature discovery kapat??ld??");

            logger.info("T??m ??erezleri kabul et butonuna bas??ld??");
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();


            Thread.sleep(5000);
            //JavascriptExecutor js = (JavascriptExecutor) driver;
            //js.executeScript("document.getElementById('close-button').click()");
            logger.warn("??lk sayfada a????lan yeni reklam kapat??lamad?? !!!");
            Thread.sleep(5000);

            driver.findElement(By.cssSelector("#container > div.homepage > div > aside > div:nth-child(1) > nav > ul.categories-left-menu > li.category-3517 > ul > li:nth-child(1) > a")).click();
            logger.info("Otomobil kategorisine t??klan??ld??");
            driver.findElement(By.cssSelector("#container > div > div.categoryPageLeft > div:nth-child(1) > div.uiInlineBoxContent.category-list > a")).click();
            logger.info("T??m otomobil kategorisine t??klan??ld??");

            Thread.sleep(5000);

            if (!driver.findElements(By.cssSelector("#searchResultsTable > tbody > tr:nth-child(1) > td.searchResultsLargeThumbnail")).isEmpty()) {
                driver.findElement(By.cssSelector("#searchResultsTable > tbody > tr:nth-child(1) > td.searchResultsTitleValue.mini-column > a.classifiedTitle"));
                logger.info("Arama sonu??ta ilk sat??r var olup olmad??????n??n kontrol edildi");

            }
            else {
                logger.error("Arama sonu??ta ilan bulunamad??");
                throw new Exception("??lan bulunamad??");
            }

            String ilanBasligi = driver.findElement(By.cssSelector("#searchResultsTable > tbody > tr:nth-child(1) > td.searchResultsTitleValue.mini-column > a.classifiedTitle")).getText();
            logger.info("??lk sat??rdaki ilan??n ilan ba??l?????? bilgisi al??nd??");
            String km = driver.findElement(By.cssSelector("#searchResultsTable > tbody > tr:nth-child(1) > td:nth-child(7)")).getText();
            logger.info("??lk sat??rdaki ilan??n km bilgisi al??nd??");
            String fiyat = driver.findElement(By.cssSelector("#searchResultsTable > tbody > tr:nth-child(1) > td.searchResultsPriceValue")).getText();
            logger.info("??lk sat??rdaki ilan??n fiyat bilgisi al??nd??");

            System.out.println("??lan Ba??l?????? : " + ilanBasligi);
            System.out.println("KM: " + km);
            System.out.println("Fiyat: " + fiyat);

            driver.findElement(By.cssSelector("#searchResultsTable > tbody > tr:nth-child(1) > td.searchResultsPriceValue")).click();
            logger.info("??lk sat??rdaki ilana t??klan??ld??");
            String detayIlanBasligi = driver.findElement(By.cssSelector("#classifiedDetail > div > div.classifiedDetailTitle > h1")).getText();
            logger.info("??lan detaydaki ilan basl?????? bilgisi al??nd??");
            String detayIlanKm = driver.findElement(By.cssSelector("#classifiedDetail > div > div.classifiedDetailContent > div.classifiedInfo > ul > li:nth-child(9) > span")).getText();
            logger.info("??lan detaydaki ilan km bilgisi al??nd??");
            driver.findElement(By.id("price-icon-wrapper")).click();
            logger.info("??lan fiyat tarih??esine t??klan??ld??");
            Thread.sleep(5000);
            String detayIlanFiyat = driver.findElement(By.id("realPriceTemplate")).getText();
            logger.info("??lan fiyat tarih??esindeki fiyat bilgisi al??nd??");

            System.out.println("??lan detay ilan ba??l?????? : " + detayIlanBasligi);
            System.out.println("??lan detay KM : " + detayIlanKm);
            System.out.println("??lan detay fiyat: " + detayIlanFiyat);

            logger.info("Aramadaki ilk sat??rdaki ilan basl?????? ile ilan detaydaki ilan basl?????? kontrol edildi");
            System.out.println(ilanBasligi.equals(detayIlanBasligi));
            logger.info("Aramadaki ilk sat??rdaki km bilgisi ile ilan detaydaki km bilgisi kontrol edildi");
            System.out.println(km.equals(detayIlanKm));
            logger.info("Aramadaki ilk sat??rdaki fiyat bilgisi ile ilan detaydaki fiyat bilgisi kontrol edildi");
            System.out.println(fiyat.equals(detayIlanFiyat));

            String ilanNo = driver.findElement(By.id("classifiedId")).getText();
            logger.info("??lan detaydaki ilan no bilgisi al??nd??");

            String urlIlanNo = driver.getCurrentUrl().toString();
            logger.info("Url bilgisinin hepsi al??nd??");

            String parts[] = urlIlanNo.split("/");
            String newIlanNo = parts[4];
            String newIlanNo2[] = newIlanNo.split("-");
            String urldekiilanno = newIlanNo2[newIlanNo2.length - 1];
            logger.info("Url'deki ilan no bilgisi al??nd??");

            System.out.println("??lan no : " + ilanNo);
            System.out.println("Url ilan no : " + urldekiilanno);
            logger.info("??lan detaydaki ilan no bilgisi ile urldeki ilan no bilgisi kontrol edildi");
            System.out.println(ilanNo.equals(urldekiilanno));

            driver.close();
        }
        catch(Exception e) {

            logger.info("Error Message :" + e);
            String imageBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] imageByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            FileUtils.copyFile(imageFile, new File("/users/fatmanur.aycil/Desktop/Case1Test.png"));
            logger.info("Fail eden alan??n ekran g??r??nt??s?? al??nd??");

            driver.close();
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void Case2(Integer no) throws InterruptedException, IOException {
        try {
            System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
            driver = new ChromeDriver();

            driver.get("https://sahibinden.com");
            logger.info("Sahibinden sitesine gidilir");

            List<Cookie> cookieList = new ArrayList<>();
            cookieList.add(new Cookie("testBox", "187", ".sahibinden.com", "/", null));
            cookieList.add(new Cookie("tbSite", "x", ".sahibinden.com", "/", null));
            cookieList.forEach(cookie -> driver.manage().addCookie(cookie));

            driver.manage().window().maximize();
            logger.info("Sayfa maximize edildi");

            driver.navigate().refresh();

            LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
            localStorage.setItem("feature_discovery_data",
                    "{\"add-to-favorites\":{\"count\":1,\"displayedAt\":1666694107010},\"extended\":true}");

            SessionStorage sessionStorage = ((WebStorage) driver).getSessionStorage();
            sessionStorage.setItem("feature_discovery_displayed", "true");

            logger.info("T??m ??erezleri kabul et butonuna bas??ld??");
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();

            Thread.sleep(5000);

            logger.info("Pop??ler ??r??nler kategorisi bilgisi al??nd??");
            WebElement iframe = driver.findElement(By.id("google_ads_iframe_/32607536/mainpage_populer_urunler_0"));
            driver.switchTo().frame(iframe);
            String populerUrunler = driver.findElement(By.cssSelector("body > div.GoogleActiveViewElement > ul > li:nth-child(" + no + ") > a")).getText();
            System.out.println("Pop??ler ??r??nler ad??: " + populerUrunler);

            logger.info("Pop??ler ??r??nler kategorisine s??rayla t??klan??ld??");
            Actions actions = new Actions(driver);
            WebElement pop = driver.findElement(By.cssSelector("body > div.GoogleActiveViewElement > ul > li:nth-child(" + no + ") > a"));
            actions.moveToElement(pop).click().perform();

            Thread.sleep(5000);

            //String kategoriIsmi = driver.findElement(By.cssSelector("#search_cats > ul > li.cl3 > div > a > h2")).getText();
            //"X Pop??ler ??r??nler" araman??zda X ??r??n bulunmaktad??r yazan sat??rdan pop??ler ??r??nler ad?? al??nd??
            logger.info("Kategorideki ??r??nler bilgisi al??nd??");
            String kategoriIsmi = driver.findElement(By.cssSelector("#searchResultsSearchForm > div > div.searchResultsRight > div.relativeContainer > div.infoSearchResults > div > div > div.result-text > div > div:nth-child(1) > h1")).getText();
            System.out.println("Kategori ??r??n ad?? : " + kategoriIsmi);

            logger.info("Pop??ler aramadaki ??r??n ad?? ile kategorideki ??r??n ad?? bilgisi kontrol edildi");
            System.out.println(populerUrunler.equals(kategoriIsmi));

            driver.close();
        }
        catch(Exception e){

            logger.info("Error Message :" + e);
            String imageBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] imageByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            FileUtils.copyFile(imageFile, new File("/users/fatmanur.aycil/Desktop/Case2Test" + no + ".png"));
            logger.info("Fail eden alan??n ekran g??r??nt??s?? al??nd??");

            driver.close();
        }
    }

}
