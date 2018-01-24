# library for selenium
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time

#open driver
driver = webdriver.Chrome("drivers/chromedriver")

# go to url
driver.get("http://www.python.org")
#checks if title is Python if wrong driver stops
assert "Python" in driver.title

#finds the element by name
elem = driver.find_element_by_name("q")

#clears the elements input
elem.clear()

#type in inputs
elem.send_keys("pycon")

#key in enter
elem.send_keys(Keys.RETURN)

# checks if results found
assert "No results found." not in driver.page_source


print(driver.page_source.encode("UTF-8"))
time.sleep(5)

#closes driver
driver.close()
