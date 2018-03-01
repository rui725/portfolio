using System;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.Support.UI;

namespace SeleniumTests
{
    [TestFixture]
    public class DevLearnLogin
    {
        private IWebDriver driver;
        private StringBuilder verificationErrors;
        private string baseURL;
        private bool acceptNextAlert = true;
        
        [SetUp]
        public void SetupTest()
        {
            driver = new FirefoxDriver();
            baseURL = "https://www.katalon.com/";
            verificationErrors = new StringBuilder();
        }
        
        [TearDown]
        public void TeardownTest()
        {
            try
            {
                driver.Quit();
            }
            catch (Exception)
            {
                // Ignore errors if unable to close the browser
            }
            Assert.AreEqual("", verificationErrors.ToString());
        }
        
        [Test]
        public void TheDevLearnLoginTest()
        {
            driver.Navigate().GoToUrl("http://dev-learn.herokuapp.com/");
            driver.FindElement(By.XPath("(//button[@type='button'])[2]")).Click();
            driver.FindElement(By.XPath("//button[@id='login']")).Click();
            driver.FindElement(By.Id("loginAlert")).Click();
            // Warning: assertTextPresent may require manual changes
            Assert.IsTrue(Regex.IsMatch(driver.FindElement(By.CssSelector("BODY")).Text, "^[\\s\\S]*$"));
            driver.FindElement(By.Id("loguname")).Click();
            driver.FindElement(By.Id("loguname")).Clear();
            driver.FindElement(By.Id("loguname")).SendKeys("sd");
            driver.FindElement(By.XPath("//button[@id='login']")).Click();
            driver.FindElement(By.Id("logpass")).Click();
            driver.FindElement(By.Id("logpass")).Clear();
            driver.FindElement(By.Id("logpass")).SendKeys("sd");
            driver.FindElement(By.XPath("//button[@id='login']")).Click();
            driver.FindElement(By.XPath("//button[@id='login']")).Click();
            driver.FindElement(By.Id("loginAlert")).Click();
            driver.FindElement(By.Id("shade")).Click();
            driver.FindElement(By.Id("loguname")).Click();
            driver.FindElement(By.Id("loguname")).Click();
            driver.FindElement(By.Id("loguname")).Click();
            driver.FindElement(By.Id("loguname")).Clear();
            driver.FindElement(By.Id("loguname")).SendKeys("test");
            driver.FindElement(By.Id("logpass")).Clear();
            driver.FindElement(By.Id("logpass")).SendKeys("test");
            driver.FindElement(By.XPath("//button[@id='login']")).Click();
            driver.FindElement(By.XPath("(//button[@type='button'])[3]")).Click();
            driver.FindElement(By.XPath("//button[@onclick='editMe();']")).Click();
            driver.FindElement(By.XPath("//button[@onclick='closeLog();']")).Click();
            driver.FindElement(By.XPath("(//button[@type='button'])[3]")).Click();
            driver.FindElement(By.XPath("//div[@id='navbar']/div/div/a/button")).Click();
            // Warning: assertTextPresent may require manual changes
            Assert.IsTrue(Regex.IsMatch(driver.FindElement(By.CssSelector("BODY")).Text, "^[\\s\\S]*$"));
        }
        private bool IsElementPresent(By by)
        {
            try
            {
                driver.FindElement(by);
                return true;
            }
            catch (NoSuchElementException)
            {
                return false;
            }
        }
        
        private bool IsAlertPresent()
        {
            try
            {
                driver.SwitchTo().Alert();
                return true;
            }
            catch (NoAlertPresentException)
            {
                return false;
            }
        }
        
        private string CloseAlertAndGetItsText() {
            try {
                IAlert alert = driver.SwitchTo().Alert();
                string alertText = alert.Text;
                if (acceptNextAlert) {
                    alert.Accept();
                } else {
                    alert.Dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }
}
