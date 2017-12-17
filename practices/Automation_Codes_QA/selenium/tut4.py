from selenium import webdriver

# iPhone
#river = webdriver.Remote(browser_name="iphone", command_executor='http://172.24.101.36:3001/hub')

# Android
#driver = webdriver.Remote(browser_name="android", command_executor='http://127.0.0.1:8080/hub')

# Google Chrome
driver = webdriver.Chrome("drivers/chromedriver")


# ------------------------------
# The actual test scenario: Test the codepad.org code execution service.

# Go to codepad.org
driver.get('http://codepad.org')

# Select the Python language option
python_link = driver.find_elements_by_xpath("//input[@name='lang' and @value='Python']")[0]
python_link.click()

# Enter some text!
text_area = driver.find_element_by_id('textarea')
text_area.send_keys("print 'Hello,' + ' World!'")

# Submit the form!
submit_button = driver.find_element_by_name('submit')
submit_button.click()

# Make this an actual test. Isn't Python beautiful?
assert "Hello, World!" in driver.page_source

# Close the browser!
driver.quit()