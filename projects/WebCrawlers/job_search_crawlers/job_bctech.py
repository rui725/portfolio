import scrapy
import pdb
import re
import datetime
import sys
from job_launcher import FileRetriever

reload(sys)
sys.setdefaultencoding('utf8')

class BcTechSearchEngine(scrapy.Spider):
    name = 'searchbctech'
    allowed_domains = ['bctechnology.com']
    start_urls = ['http://bctechnology.com/jobs/search-results.cfm?category=3&cat=4']

    count = 0
    page_counter = 0
    write_html = 0
    checkDay = 0

    f = "" # file

    def parse(self,response):
        fr = FileRetriever
        exist_url = fr().getFiles(self.exist_files)

        jobs = response.xpath('//td[@id="td-job-results-row"]/table[@id="table-job-results-row"]//tr/td[2]').extract()
        for j in jobs:
            if "Today" in j:
                item_url = re.search('href="([^"]+)',j).group(1)
                if exist_url:
                    if (fr().verifyUrl(exist_url,'http://bctechnology.com'+item_url)):
                        yield scrapy.Request('http://bctechnology.com'+item_url, self.parse_item)
                else:
                    yield scrapy.Request('http://bctechnology.com'+item_url, self.parse_item)
            else:
                self.checkDay=1
                break
        if self.checkDay == 0:
            self.page_counter += 1
            next_page = response.xpath('//p[@class="default"]/span/a/@href').extract()
            print next_page[len(next_page)-1]
            yield scrapy.Request('http://bctechnology.com/'+next_page[len(next_page)-1], self.parse)

    def parse_item(self, response):
        if "bctechnology.com" in response.url:
            if self.write_html == 0:
                self.buildHtmlFile()
                self.write_html = 1

            jobtitle = response.xpath('//div[@class="screen-ui-standard"]/h2/text()').extract()[0]
            description= response.xpath('//td[@id="td-job-overview"]').extract()[0]
            location = response.xpath('//div[@class="screen-ui-standard"]/p[2]/span/text()').extract()[0]
            company_name =response.xpath('//div[@class="screen-ui-standard"]/p[1]/text()').extract()[0]
            posted = response.xpath('//div[@class="screen-ui-standard"]/p[2]/text()').extract()[0]
            self.f = open(self.filename, "a")
            message = "\n<table class=\"table table-inverse\" border=1 id=\"bctech_"+str(self.count)+"\"><th>Job Title</th><th><h3>"+jobtitle+"</h3></th>\n"
            #message = "\n<table border=1><th>Job Title</th><th><h3>"+jobtitle+"</h3></th>\n"
            message += "<tr><td><b>Description</b></td>"+description+"</tr>\n"
            message += "<tr><td><b>URL</b></td><td><a href="+response.url+">"+response.url+"</a></td></tr>\n"
            message += "<tr><td><b>Company</b></td><td>"+company_name+"</td></tr>\n"
            message += "<tr><td><b>Location</b></td><td>"+location+"</td></tr>\n"
            message += "<tr><td><b>Posted:</b></td><td>"+posted+"</td></tr></table>\n<br><br>"
            self.f.write(message)
            self.f.close()
            load = open(self.filename+"_load.txt",'a')
            load.write("&nbsp;<small><kbd><a href=\"#bctech_"+ str(self.count)+"\">" + jobtitle + "</a></kbd></small>\n")
            load.close()
            self.count+=1
            print str(self.count) + " - "+ str(self.page_counter)+"pg "

    def buildHtmlFile(self):
        message = "<div class=\"jumbotron jumbotron-fluid\" id=\"bctech\"><br>\n"
        message += "<div class=\"container\">\n"
        message += "<h1 class=\"display-3\">BcTechnology Jobs Found </h1>\n"
        message += "<p class=\"lead\">" + "jumbo-rui!" + "</p></div></div>\n"
        self.f = open(self.filename, "a")
        self.f.write(message)
        self.f.close()