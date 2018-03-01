# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class DevLearnLogin(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.katalon.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_dev_learn_login(self):
        driver = self.driver
        driver.get("http://dev-learn.herokuapp.com/")
        driver.find_element_by_xpath("(//button[@type='button'])[2]").click()
        driver.find_element_by_xpath("//button[@id='login']").click()
        driver.find_element_by_id("loginAlert").click()
        # Warning: assertTextPresent may require manual changes
        self.assertRegexpMatches(driver.find_element_by_css_selector("BODY").text, r"^[\s\S]*$")
        driver.find_element_by_id("loguname").click()
        driver.find_element_by_id("loguname").clear()
        driver.find_element_by_id("loguname").send_keys("sd")
        driver.find_element_by_xpath("//button[@id='login']").click()
        driver.find_element_by_id("logpass").click()
        driver.find_element_by_id("logpass").clear()
        driver.find_element_by_id("logpass").send_keys("sd")
        driver.find_element_by_xpath("//button[@id='login']").click()
        driver.find_element_by_xpath("//button[@id='login']").click()
        driver.find_element_by_id("loginAlert").click()
        driver.find_element_by_id("shade").click()
        driver.find_element_by_id("loguname").click()
        driver.find_element_by_id("loguname").click()
        driver.find_element_by_id("loguname").click()
        driver.find_element_by_id("loguname").clear()
        driver.find_element_by_id("loguname").send_keys("test")
        driver.find_element_by_id("logpass").clear()
        driver.find_element_by_id("logpass").send_keys("test")
        driver.find_element_by_xpath("//button[@id='login']").click()
        driver.find_element_by_xpath("(//button[@type='button'])[3]").click()
        driver.find_element_by_xpath("//button[@onclick='editMe();']").click()
        driver.find_element_by_xpath("//button[@onclick='closeLog();']").click()
        driver.find_element_by_xpath("(//button[@type='button'])[3]").click()
        driver.find_element_by_xpath("//div[@id='navbar']/div/div/a/button").click()
        # Warning: assertTextPresent may require manual changes
        self.assertRegexpMatches(driver.find_element_by_css_selector("BODY").text, r"^[\s\S]*$")
    
    def is_element_present(self, how, what):
        try: self.driver.find_element(by=how, value=what)
        except NoSuchElementException as e: return False
        return True
    
    def is_alert_present(self):
        try: self.driver.switch_to_alert()
        except NoAlertPresentException as e: return False
        return True
    
    def close_alert_and_get_its_text(self):
        try:
            alert = self.driver.switch_to_alert()
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally: self.accept_next_alert = True
    
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)

if __name__ == "__main__":
    unittest.main()
