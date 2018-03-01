package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class DevLearnLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testDevLearnLogin() throws Exception {
    driver.get("http://dev-learn.herokuapp.com/");
    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
    driver.findElement(By.xpath("//button[@id='login']")).click();
    driver.findElement(By.id("loginAlert")).click();
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    driver.findElement(By.id("loguname")).click();
    driver.findElement(By.id("loguname")).clear();
    driver.findElement(By.id("loguname")).sendKeys("sd");
    driver.findElement(By.xpath("//button[@id='login']")).click();
    driver.findElement(By.id("logpass")).click();
    driver.findElement(By.id("logpass")).clear();
    driver.findElement(By.id("logpass")).sendKeys("sd");
    driver.findElement(By.xpath("//button[@id='login']")).click();
    driver.findElement(By.xpath("//button[@id='login']")).click();
    driver.findElement(By.id("loginAlert")).click();
    driver.findElement(By.id("shade")).click();
    driver.findElement(By.id("loguname")).click();
    driver.findElement(By.id("loguname")).click();
    driver.findElement(By.id("loguname")).click();
    driver.findElement(By.id("loguname")).clear();
    driver.findElement(By.id("loguname")).sendKeys("test");
    driver.findElement(By.id("logpass")).clear();
    driver.findElement(By.id("logpass")).sendKeys("test");
    driver.findElement(By.xpath("//button[@id='login']")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    driver.findElement(By.xpath("//button[@onclick='editMe();']")).click();
    driver.findElement(By.xpath("//button[@onclick='closeLog();']")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    driver.findElement(By.xpath("//div[@id='navbar']/div/div/a/button")).click();
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
