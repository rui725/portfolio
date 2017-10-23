import scrapy
import pdb
import re
import datetime
import sys
from job_launcher import FileRetriever

reload(sys)
sys.setdefaultencoding('utf8')

class WorkBcSearchEngine(scrapy.Spider):
    name = 'searchworkbc'
    allowed_domains = ['workbc.ca']
    start_urls = ['https://www.workbc.ca/Jobs-Careers/Find-Jobs/Jobs.aspx?industry=165415']
  #  download_delay = 4
    count = 0
    page_counter = 1
    write_html = 0
    checkDay = True

    f = "" # file

    def parse(self,response):
        fr = FileRetriever
        exist_url = fr().getFiles(self.exist_files)

        jobs = response.xpath('//div[@class="job-search-result-item container"]/div').extract()
        for j in jobs:
            d = re.search('([0-9-]+)',self.filename)
            if d.group(1) in j:
                link = re.search('href="([^"]+)', j).group(1)
                if exist_url:
                    if (fr().verifyUrl(exist_url,'https://www.workbc.ca'+link)):
                        yield scrapy.Request('https://www.workbc.ca'+link, self.parse_item)
                else:
                    yield scrapy.Request('https://www.workbc.ca'+link, self.parse_item)
            else:
                self.checkDay = False
        if self.checkDay:
            self.page_counter += 1
            yield scrapy.Request("https://www.workbc.ca/Jobs-Careers/Find-Jobs/Jobs.aspx?industry=165415&page="+str(self.page_counter), self.parse)


    def parse_item(self, response):
        if "workbc.ca" in response.url:
            if self.write_html == 0:
                self.buildHtmlFile()
                self.write_html = 1

            jobtitle = response.xpath('//div[@class="job-item-col"]/h1/text()').extract()[0]

            description= ''.join(response.xpath('//div[@id="job-detail-page"]/p').extract())

            location = response.xpath('//div[contains(@class,"job-item-col third")]/address').extract()[0]
            company_name =response.xpath('//div[@class="job-company job-detail-row"]/a/text()').extract()[0]
            posted = response.xpath('//div[@class="job-post-date"]/text()').extract()[0]
            self.f = open(self.filename, "a")
            message = "\n<table class=\"table table-inverse\" border=1 id=\"workbc_"+str(self.count)+"\"><th>Job Title</th><th><h3>"+jobtitle+"</h3></th>\n"
            #message = "\n<table border=1><th>Job Title</th><th><h3>"+jobtitle+"</h3></th>\n"
            message += "<tr><td><b>Description</b></td><td>"+description+"</td></tr>\n"
            message += "<tr><td><b>URL</b></td><td><a href="+response.url+">"+response.url+"</a></td></tr>\n"
            message += "<tr><td><b>Company</b></td><td>"+company_name+"</td></tr>\n"
            message += "<tr><td><b>Location</b></td><td>"+location+"</td></tr>\n"
            message += "<tr><td><b>Posted:</b></td><td>"+posted+"</td></tr></table>\n<br><br>"
            self.f.write(message)
            self.f.close()
            load = open(self.filename+"_load.txt",'a')
            load.write("&nbsp;<small><kbd><a href=\"#workbc_"+ str(self.count)+"\">" + jobtitle + "</a></kbd></small>\n")
            load.close()
            self.count+=1
            print str(self.count) + " - "+ str(self.page_counter)+"pg "

    def buildHtmlFile(self):
        message = "<div class=\"jumbotron jumbotron-fluid\" id=\"workbc\"><br>\n"
        message += "<div class=\"container\">\n"
        message += "<h1 class=\"display-3\">WorkBC Jobs Found </h1>\n"
        message += "<p class=\"lead\">" + "jumbo-rui!" + "</p></div></div>\n"
        self.f = open(self.filename, "a")
        self.f.write(message)
        self.f.close()