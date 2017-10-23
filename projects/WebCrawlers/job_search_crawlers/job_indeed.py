import scrapy
import re
import datetime
import sys


from job_launcher import FileRetriever

reload(sys)
sys.setdefaultencoding('utf8')

class IndeedSearchEngine(scrapy.Spider):
    name = 'searchIndeed'
    allowed_domains = ['indeed.ca']
    start_urls = ['https://www.indeed.ca/jobs?q=computer&l=Vancouver%2C%20BC&sort=date&limit=20'
                  ,'https://www.indeed.ca/jobs?q=software&l=Vancouver%2C%20BC&sort=date&limit=20'
                  ,'https://www.indeed.ca/jobs?q=developer&l=Vancouver%2C%20BC&sort=date&limit=20']

    count = 0
    page_counter = 0
    write_html = 0
    f = "" # file
    d = "" # date

    def parse(self,response):
        fr = FileRetriever
        exist_url = fr().getFiles(self.exist_files)

        jobs = response.xpath('//div[@data-tn-component="organicJob"]/h2').extract()
        for j in jobs:
                item_url = re.search('href="([^"]+)',j)
                if item_url.group(1) not in exist_url:
                    yield scrapy.Request('https://www.indeed.ca' + item_url.group(1) ,self.parse_item)
        self.page_counter = response.xpath('//div[@class="pagination"]/b/text()').extract()[0]
        if self.page_counter != '8':
            page= response.xpath('//a[contains(span,"Next")]/@href').extract()[0]
            yield scrapy.Request('https://www.indeed.ca/'+page,self.parse)

    def parse_item(self, response):
        if "indeed.ca" in response.url:
            if self.write_html == 0:
                self.buildHtmlFile()
                self.write_html = 1
            jobtitle = response.xpath('//b[@class="jobtitle"]/font/text()').extract()[0]

            description= response.xpath('//span[@class="summary"]').extract()[0]

            description = u''.join(description).encode('utf-8').strip()
            location = response.xpath('//span[@class="location"]/text()').extract()[0]
            company_name =response.xpath('//span[@class="company"]/text()').extract()[0]
            posted = response.xpath('//span[@class="date"]/text()').extract()[0]
            self.f = open(self.filename + ".html","a")
            message = "\n<table class=\"table table-inverse\" border=1><th>Job Title</th><th><h3>"+jobtitle+"</h3></th>\n"
            message += "<tr><td><b>Description</b></td><td>"+description+"</td></tr>\n"
            message += "<tr><td><b>URL</b></td><td><a href="+response.url+">"+response.url+"</a></td></tr>\n"
            message += "<tr><td><b>Company</b></td><td>"+company_name+"</td></tr>\n"
            message += "<tr><td><b>Location</b></td><td>"+location+"</td></tr>\n"
            message += "<tr><td><b>Posted:</b></td><td>"+posted+"</td></tr></table>\n<br><br>"
            self.f.write(message)
            self.f.close()
            self.count+=1
            print str(self.count) + " - "+ str(self.page_counter)+"pg "

    def buildHtmlFile(self):
        message = "<div class=\"jumbotron jumbotron-fluid\" id=\"indeed\"><br>\n"
        message += "<div class=\"container\">\n"
        message += "<h1 class=\"display-3\">Indeed Jobs Found </h1>\n"
        message += "<p class=\"lead\">" + "stuff" + "</p></div></div>\n"
        self.f = open(self.filename+".html", "a")
        self.f.write(message)
        self.f.close()