import scrapy
import pdb
import re


class BestBuySpider(scrapy.Spider):
    name = 'bestbuyspider'
    start_urls= ['http://www.bestbuy.ca/en-CA/sitemap-overview.aspx?NVID=footer;corporate%20info;site%20map;en']
    count =0
    def parse(self,response):
        url_cat = response.xpath('//a[@class="lnk-more"]/@href').extract()
        if len(url_cat) !=0:
            for cat_link in url_cat:
                yield scrapy.Request('http://www.bestbuy.ca'+cat_link, self.parse)
        url_link = response.xpath('//a[@class="class-links"]/@href').extract()
        if len(url_link)!=0:
            for link in url_link:
                yield scrapy.Request('http://www.bestbuy.ca'+link,self.parse_items)

    def parse_items (self,response):
        link = response.xpath('//h4[@class="prod-title"]/a/@href').extract()
        if len(link)!=0:
            for l in link:
                yield scrapy.Request('http://www.bestbuy.ca'+l,self.parse_item)
        nextpage = response.xpath('//li[@class="pagi-next disabled"]/a/text()').extract()
        if len(nextpage)==0:
            l = response.xpath('//li[@class="pagi-next"]/a/@href').extract()
            yield scrapy.Request('//http://www.bestbuy.ca'+l[0], self.parse_items)

    def parse_item(self,response):
        name = response.xpath('//h1[@class="product-title"]/span/text()').extract()[0]
        print 'Name: '+ name
        price = response.xpath('//span[@class="amount"]/text()').extract()[0]
        print 'Price: ' + price
        print 'url: ' + response.url
        self.count+=1
        print 'Item Found = '+ str(self.count)
