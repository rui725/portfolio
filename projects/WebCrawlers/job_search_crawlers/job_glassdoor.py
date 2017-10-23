import scrapy
import pdb
import re
import datetime

from job_launcher import FileRetriever
import sys
reload(sys)
sys.setdefaultencoding('utf8')

class GlassdoorSearchEngine(scrapy.Spider):
    name = 'searchglassdoor'
    allowed_domains = ['glassdoor.ca']
    download_delay = 0.5
    start_urls = [#'https://www.glassdoor.ca/Job/vancouver-computer-jobs-SRCH_IL.0,9_IC2278756_KE10,18.htm',
                  #'https://www.glassdoor.ca/Job/html/jobs.htm?sc.keyword=computer&locT=C&locId='
                  #'2278756&locKeyword=Vancouver,%20BC&includeNoSalaryJobs=false&jobType=all&fromAge=1&radius=-1'
                  #'&cityId=-1&minRating=0.0&industryId=-1&companyId=-1&employerSizes=0']
                  'https://www.glassdoor.ca/Job/vancouver-computer-jobs-SRCH_IL.0,9_IC2278756_KE10,18_IP1.htm?fromAge=1']
    count = 0
    page_counter = 1
    write_html = 0
    checkDay = 0
    fr = ''
    exist_url = ''
    f = "" # file
    d = "" # date

    def parse(self,response):
        jobs = response.xpath('//td[@class="job_title"]/a/@href').extract()
        self.fr = FileRetriever
        self.exist_url = self.fr().getFiles(self.exist_files)
        print len(jobs)
        for j in jobs:
            yield scrapy.Request('http://glassdoor.ca'+j, self.parse_item)

        next_page = response.xpath('//div[@id="ResultsFooter"]/div/text()').extract()
        print next_page
        if next_page:
            next_page = str(next_page[0]).replace(" Page 1 of ","")
            if int(self.page_counter) < next_page:
                self.page_counter += 1
                yield scrapy.Request('https://www.glassdoor.ca/Job/vancouver-computer-jobs-SRCH_IL.0,9_IC2278756_KE10,18_IP'
                    + str(self.page_counter) + '.htm?fromAge=1', self.parse)

        #next_page = response.xpath('//li[@class="next"]/a/@href').extract()
        #if next_page:
         #   self.page_counter+=1
         #   yield scrapy.Request('http://glassdoor.ca'+next_page[0], self.parse)
    def parse_item(self, response):
        if "glassdoor.ca" in response.url and 'easyApply' not in response.url:
            link = str(response.url)
            if '&ctt=' in link:
                link = re.search("(.+)&ctt=[0-9]+",link).group(1)
            if self.exist_url:
                if (self.fr().verifyUrl(self.exist_url,link)):
                    return
            if self.write_html == 0:
                self.buildHtmlFile()
                self.write_html = 1


            jobtitle = response.xpath('//h2[@class="noMargTop margBotSm strong"]/text()').extract()[0]
            description= response.xpath('//div[@id="JobDescContainer"]').extract()[0]
            location = response.xpath('//span[@class="subtle ib"]/text()').extract()[0]
            company_name =response.xpath('//div[@class="header cell info"]/span[1]/text()').extract()[0]
            #posted = response.xpath('//div[@class="screen-ui-standard"]/p[2]/text()').extract()[0]
            self.f = open(self.filename,"a")
            message = "\n<table class=\"table table-inverse\" border=1 id=\"glassdoor_"+str(self.count)+"\"><th>Job Title</th><th><h3>"+jobtitle+"</h3></th>\n"
            #message = "\n<table border=1><th>Job Title</th><th><h3>"+jobtitle+"</h3></th>\n"
            message += "<tr><td><b>Description</b></td><td>"+description+"</td></tr>\n"
            message += "<tr><td><b>URL</b></td><td><a href="+link+">"+link+"</a></td></tr>\n"
            message += "<tr><td><b>Company</b></td><td>"+company_name+"</td></tr>\n"
            message += "<tr><td><b>Location</b></td><td>"+location+"</td></tr>\n"
            #message += "<tr><td><b>Posted:</b></td><td>"+posted+"</td></tr></table>\n<br><br>"
            self.f.write(message)
            self.f.close()
            load = open(self.filename+"_load.txt",'a')
            load.write("&nbsp;<small><kbd><a href=\"#glassdoor_"+ str(self.count)+"\">" + jobtitle + "</a></kbd></small>\n")
            load.close()
            self.count+=1
            print link
            print str(self.count) + " - "+ str(self.page_counter)+"pg "

    def buildHtmlFile(self):
        message = "<div class=\"jumbotron jumbotron-fluid\" id=\"glassdoor\"><br>\n"
        message += "<div class=\"container\">\n"
        message += "<h1 class=\"display-3\">Glassdoor Jobs Found </h1>\n"
        message += "<p class=\"lead\">" + "jumbo-rui!" + "</p></div></div>\n"
        self.f = open(self.filename, "a")
        self.f.write(message)
        self.f.close()