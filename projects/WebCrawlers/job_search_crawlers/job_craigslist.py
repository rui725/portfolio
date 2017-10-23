import scrapy
import pdb
import re
import datetime
import sys
import urllib as ul
from job_launcher import FileRetriever

reload(sys)
sys.setdefaultencoding('utf8')

class CraiglistSearchEngine(scrapy.Spider):
    name = 'searchcraiglist'
    allowed_domains = ['craigslist.ca']
    start_urls = ['https://vancouver.craigslist.ca/d/software-qa-dba-etc/search/sof',
                  'https://vancouver.craigslist.ca/d/systems-networking/search/sad',
                  'https://vancouver.craigslist.ca/d/technical-support/search/tch',
                  'https://vancouver.craigslist.ca/d/computer-gigs/search/cpg']

    count = 0
    page_counter = 0
    write_html = 0

    f = "" # file
    d = "" # date

    def setDate(self):
        self.d = str(datetime.date.today())

    def parse(self,response):
        self.setDate()
        fr = FileRetriever
        exist_url = fr().getFiles(self.exist_files)

        jobs = response.xpath('//p[@class="result-info"]').extract()
        for j in jobs:
            if self.d in j:
                item_url = str(re.search('href="([^"]+)',j).group(1))
                if exist_url:
                    if (fr().verifyUrl(exist_url,item_url)):
                        yield scrapy.Request(item_url, self.parse_item)
                else:
                    yield scrapy.Request(item_url, self.parse_item)

    def parse_item(self, response):
        if "craigslist.ca" in response.url:
            if self.write_html == 0:
                self.buildHtmlFile()
                self.write_html = 1

            jobtitle = response.xpath('//span[@id="titletextonly"]/text()').extract()[0]
            description= response.xpath('//section[@class="userbody"]/section/text()').extract()
            description = '<br />\n'.join(description)
            location = response.xpath('//span[@class="postingtitletext"]/small/text()').extract()
            posted = response.xpath('//time[@class="date timeago"]/text()').extract()[0]
            self.f = open(self.filename,"a")
            message = "\n<table class=\"table table-inverse\" border=1 id=\"craig_"+str(self.count)+"\"><th>Job Title</th><th><h3>"+jobtitle+"</h3></th>\n"
            #  message = "\n<table border=1><th>Job Title</th><th><h3>"+jobtitle+"</h3></th>\n"
            message += "<tr><td><b>Description</b></td><td>"+description+"</td></tr>\n"
            message += "<tr><td><b>URL</b></td><td><a href="+ul.unquote(response.url)+">"+ul.unquote(response.url)+"</a></td></tr>\n"
            if location:
                message += "<tr><td><b>Location</b></td><td>"+location[0]+"</td></tr>\n"
            message += "<tr><td><b>Posted:</b></td><td>"+posted+"</td></tr></table>\n<br><br>"
            self.f.write(message)
            self.f.close()
            load = open(self.filename+"_load.txt",'a')
            load.write("&nbsp;<small><kbd><a href=\"#craig_"+ str(self.count)+"\">" + jobtitle + "</a></kbd></small>\n")
            load.close()
            self.count+=1
            print str(self.count) + " - "+ str(self.page_counter)+"pg "

    def buildHtmlFile(self):
        message = "<div class=\"jumbotron jumbotron-fluid\" id=\"craigslist\"><br>\n"
        message += "<div class=\"container\">\n"
        message += "<h1 class=\"display-3\">Craigslist Jobs Found </h1>\n"
        message += "<p class=\"lead\">" + "jumbo-rui!" + "</p></div></div>\n"
        self.f = open(self.filename, "a")
        self.f.write(message)
        self.f.close()
