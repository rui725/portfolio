# needs for implementing tests
import unittest
# needs for webdriver
from selenium import webdriver
# needs for typing in input
from selenium.webdriver.common.keys import Keys


# class that has used the unittest and declares it as TestCase
class PythonOrgSearch(unittest.TestCase):
    # always run first
    def setUp(self):
        self.driver = webdriver.Chrome("drivers/chromedriver")

    # runs and must always starts with the word test
    def test_search_in_python(self):
        driver = self.driver
        driver.get("http://www.python.org")
        self.assertIn("Python", driver.title)
        elem = driver.find_element_by_name("q")
        elem.send_keys("pycon")
        elem.send_keys(Keys.RETURN)
        assert "No results found." not in driver.page_source
    # gets called after every test method
    def tearDown(self):
        self.driver.close()

if __name__ == "__main__":
    # used to run test suite
    unittest.main()