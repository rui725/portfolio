import unittest
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

# import for select
from selenium.webdriver.support.ui import Select

class PythonTests(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome("drivers/chromedriver")
        self.driver.implicitly_wait(10)
    def test_example3(self):
        driver = self.driver
        driver.get("http://www.google.com")
        assert "Google" in driver.title
        element1 = driver.find_element_by_id("lst-ib")
        element2 = driver.find_element_by_name("q")
        element3 = driver.find_element_by_xpath("//input[@title='Search']")
        element1.send_keys("HI")
        assert "HI" in element2.get_attribute("value")
        #concatenate
        element3.send_keys("What")
        element3.send_keys(Keys.RETURN);

    def test_example3part2(self):
        driver = self.driver
        driver.get("http://google.com")

        elem = driver.find_element_by_name("q")
        elem.send_keys("Weather in Vancouver", Keys.RETURN, "Good")


        cookie = {"name":"myname", "value":"val"}
        # adds cookie
        driver.add_cookie(cookie)
        #displays cookies
        print(driver.get_cookies())
        # move back history
        driver.back()

        # move forward history
        driver.forward()

        driver.get("https://www.w3schools.com/bootstrap/bootstrap_dropdowns.asp")
        # Link Text finding
        # example link
        # <html>
        #<body>
        #<p>Are you sure you want to do this?</p>
        #<a href="continue.html">Continue</a>
        #<a href="cancel.html">Cancel</a>
        #</body>
        #<html>
        #continue_link = driver.find_element_by_link_text('Continue')
        #continue_link = driver.find_element_by_partial_link_text('Conti')

        # finding by class name
        #content = driver.find_element_by_class_name('content')

        for handle in driver.window_handles:
            print(handle)
            driver.switch_to_window(handle)

    def tearDown(self):
        None
        #self.driver.close()

if __name__ == "__main__":
    unittest.main();