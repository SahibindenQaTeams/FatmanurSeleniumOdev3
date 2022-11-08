package assertions;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumExamples {

  private static final Logger logger = LogManager.getLogger(SeleniumExamples.class);

  ChromeDriver driver;

  @BeforeEach
  public void beforeTest() {
    System.setProperty("webdriver.chrome.driver", "/opt/chromedriver");
    driver = new ChromeDriver();
  }

  @AfterEach
  public void afterTest() {
    driver.close();
    driver.quit();
  }

  @Test
  public void testAssertGetCurrentUrl() {
    driver.get("https://www.sahibinden.com/");

    String currentUrl = driver.getCurrentUrl();
    logger.info(currentUrl);

    Assertions.assertEquals("https://www.sahibinden.com/", currentUrl);
  }

  @Test
  public void testButtonName() {
    driver.get("https://www.sahibinden.com/");

    String buttonName = driver.findElement(By.id("post-new-classified")).getText();
    logger.info(buttonName);

    Assertions.assertEquals("Ücretsiz* İlan Ver", buttonName);
  }

  @Test
  public void testLogo() {
    driver.get("https://www.sahibinden.com/");

    String logoTitle = driver.findElement(By.cssSelector(".logo")).getAttribute("title");
    logger.info(logoTitle);

    Assertions.assertEquals("sahibinden.com anasayfasına dön", logoTitle);
  }

  @Test
  public void testClassifiedTitle() {
    driver.get("https://www.sahibinden.com/");

    List<WebElement> classifieds = driver.findElements(
        By.cssSelector(".showcase .vitrin-list li span"));

    classifieds.forEach(classified -> logger.info(classified.getText()));

    String classifiedTitle = classifieds.get(0).getText();

    Assertions.assertNotNull(classifiedTitle);
  }

  @Test
  public void testButtonDisplayed() {
    driver.get("https://www.sahibinden.com/");

    boolean postNewClassifiedIsDisplayed = driver.findElement(By.id("post-new-classified"))
        .isDisplayed();
    logger.info(postNewClassifiedIsDisplayed);

    Assertions.assertTrue(postNewClassifiedIsDisplayed);
  }

  @Test
  public void testRealEstateCategoryClassifiedSize() {
    driver.get("https://www.sahibinden.com/");

    String realEstateClassifiedLength = driver.findElement(By.cssSelector(".category-3518 > span"))
        .getText();

    logger.info(realEstateClassifiedLength);

    Assertions.assertNotNull(realEstateClassifiedLength);
  }
}
