# import libraries
import os
import re
import datetime
import fileinput
from lxml.html import fromstring
import urllib as ul
from subprocess import PIPE, Popen

# Handles all Launching Events
class Launcher():

    # initiate global variables
    empty = False
    status = ''
    file_list = []
    sites = {'Indeed': 'job_indeed.py' ,
             'BcTechnology': 'job_bctech.py' ,
             'Craigslist': 'job_craigslist.py',
             'Glassdoor': 'job_glassdoor.py' ,
             'WorkBC': 'job_workbc.py'}
    filename = ""
    d = ""

    # initiates class
    def __init__(self):
        # sets dates
        self.d =  datetime.date.today()

        # starts method
        self.start()

    # param name - Site Name
    # return none
    # loads the necessary values and links to the prebuilt html file
    def loadJobLinks(self, name):

        ptags = ""
        maximum = 0
        if not os.path.isfile(self.filename+"_load.txt"):
            return
        self.empty = True
        # set regex
        textToSearch = name + " Jobs Found </h1>\n<p class=\"lead\">jumbo-rui!"
        # open file
        f = open(self.filename+"_load.txt", 'r')
        with open(self.filename +"_load.txt") as reader:
            for line in reader:
                maximum +=1
                ptags += line
        f.close()
        # set text to replace
        textToReplace = name + " Jobs Found "+ str(maximum) + "</h1><p class=\"lead\">"+ptags

        #read all stuff
        readin = open(self.filename, 'r+').read()
        if textToSearch in readin:
            readin = readin.replace(textToSearch,textToReplace)
            writeout = open(self.filename,'w')
            writeout.write(readin)
            writeout.close()
            cmd = "rm "+ self.filename+"_load.txt"
            os.system(cmd)

    def cleaner(self, days=3):
        today = datetime.datetime.now()
        date_list = []
        for d in range(0,days+1):
            dd = datetime.timedelta(days=d)
            date_list.append((today - dd).strftime("%Y-%m-%d"))
        print date_list
        cmd = "ls *.html"
        status = Popen(cmd,stdout=PIPE)
        status.wait()
        if status.returncode == 2:
            self.buildHtmlFile("open" ,1)
        else:
            check = False
            for f in status.stdout:
                for d in date_list:
                    if d in f:
                       check = True
                if not check:
                    cmd = "rm "+ f
                    os.system(cmd)
                else:
                    check = False



    def loadHeaders(self, today):
        message = "<html>\n\t<head><title> Job Search as of " + today + "</title>\n\t"
        message += "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\""
        message += " integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\">\n"
        message += "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n"
        message += "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js\" integrity=\"sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4\" crossorigin=\"anonymous\"></script>\n"
        message += "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js\" integrity=\"sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1\" crossorigin=\"anonymous\"></script>\n\t</head>\n"
        message += "<style> a { color:white; margin-top: 0px;}</style>"

        message += "<body data-spy=\"scroll\" data-target=\"#navs\" data-offset=\"0\" ><br />\n"
        message += "<nav id=\"navs\" class=\"navbar fixed-top navbar-dark bg-dark\">\n"
        message += "<!-- Navbar content -->\n"
        message += "<a class=\"navbar-brand\" href=\"#navs\">Job Search As of " + today + "</a>\n"
        message += "<ul class=\"nav nav-pills\">\n"
        message += "    <li class=\"nav-item\">\n"
        message += "    <a class=\"nav-link\" href=\"#indeed\" >Indeed</a></li>\n"
        message += "    <li class=\"nav-item\">\n"
        message += "    <a class=\"nav-link\" href=\"#bctech\" >BcTechnology</a></li>\n"
        message += "    <li class=\"nav-item\">\n"
        message += "    <a class=\"nav-link\" href=\"#craigslist\" >Craigslist</a></li>\n"
        message += "    <li class=\"nav-item\">\n"
        message += "    <a class=\"nav-link\" href=\"#glassdoor\" >Glassdoor</a></li>\n"
        message += "    <li class=\"nav-item\">\n"
        message += "    <a class=\"nav-link\" href=\"#workbc\" >WorkBC</a></li>\n"
        message += "</ul></nav>\n"
        return message

    def buildHtmlFile(self,operation, version):
        d = self.d
        today = str(d.strftime("%B %d %Y"))
        f = ""
        if 'open' in operation:
            message = self.loadHeaders(today)
            self.filename = str(d) + "_v1.html"
            f = open(self.filename,"w")
        elif 'update' in operation:
            message = self.loadHeaders(today)
            self.filename = str(d) + "_v"+ str(version+1) + ".html"
            f = open(self.filename,'w')
        else:
            message = "\n</body>\n</html>"
            f = open(self.filename,"a")
        f.write(message)
        f.close()

    def getExistingFileList(self):
        cmd = "ls *.html"
        status = Popen(cmd, stdout=PIPE)
        status.wait()
        if status.returncode == 2:
            return
        for f in status.stdout:
            temp = f.replace("\n","")
            if temp not in self.file_list:
                self.file_list.append(temp)


    def makefile(self):
        global status
        cmd = "ls " + str(self.d) + "*.html"
        #cmd = "ls *.html"
        status = Popen(cmd,stdout=PIPE)
        status.wait()
        if status.returncode == 2:
            self.buildHtmlFile("open" ,1)
        else:
            cnt = 0
            for f in status.stdout:
                cnt += 1
            self.buildHtmlFile("update", cnt)


    def start(self):
        d = self.d
        self.cleaner()

        self.makefile()

        self.getExistingFileList()

        # iterate websites
        for k in sorted(self.sites.keys()):
            if k == 'Indeed':
                continue
            cmd = "scrapy runspider "+ self.sites[k] + " -a filename="+ self.filename + " -a exist_files=" + ",".join(self.file_list)
            os.system(cmd)
            self.loadJobLinks(k)

        self.buildHtmlFile("close",0)
        if self.empty:
            cmd = "start "+self.filename
        else:
            cmd = "rm " + self.filename
        os.system(cmd)

class FileRetriever():

    def __init__(self):
        None

    def getFiles(self,files):
        if not files:
            return []
        existing_url = []
        files = files.split(',')
        for f in files:
            fi = open(f, 'r')
            url = re.findall('URL</b></td><td><a href=(http.+)>http', fi.read())
            for l in url:
                if l not in existing_url:
                    l = str(l).replace("[\w|\r|\n]","")
                    existing_url.append(l)
            fi.close()
        return existing_url

    def verifyUrl(self,links, url):
        for l in links:
            if url in l or url in l:
                return False
        return True

