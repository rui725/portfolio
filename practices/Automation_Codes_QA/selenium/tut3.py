import unittest
from selenium import webdriver
from selenium.webdriver.common.keys import Keys


class PythonTests(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome("drivers/chromedriver")

    def test_example3(self):
        driver = self.driver
        driver.get("http://www.google.com")
        assert "Google" in driver.title
        element1 = driver.find_element_by_id("lst-ib")
        element2 = driver.find_element_by_name("q")
        element3 = driver.find_element_by_xpath("//input[@title='Search']")
        element1.send_keys("HI")
        assert "HI" in element2.get_attribute("value")
        element3.send_keys("What")
        element3.send_keys(Keys.RETURN);



    def tearDown(self):
        None
        #self.driver.close()

if __name__ == "__main__":
    unittest.main();