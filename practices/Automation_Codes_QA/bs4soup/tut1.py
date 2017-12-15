from bs4 import BeautifulSoup
import urllib2


html_doc = """
<html><head><title>The Dormouse's story</title></head>
<body>
<p class="title"><b>The Dormouse's story</b></p>

<p class="story">Once upon a time there were three little sisters; and their names were
<a href="http://example.com/elsie" class="sister" id="link1">Elsie</a>,
<a href="http://example.com/lacie" class="sister" id="link2">Lacie</a> and
<a href="http://example.com/tillie" class="sister" id="link3">Tillie</a>;
and they lived at the bottom of a well.</p>

<p class="story">...</p>
"""

def start():
    # load page
    page = urllib2.urlopen("http://www.google.com").read()
    #load html
    soup = BeautifulSoup(page, "html.parser")

    # soup . [tag] with tag
    print(soup.title)

    # soup . [tag] . [tag name]
    print(soup.title.name)
    # soup . [tag] . actual string inside tag
    print(soup.title.string)

    # soup . [tag] . parent . [tag name]
    print(soup.title.parent.name)

    # formats html code prints it out
    print(soup.prettify().encode('UTF-8'))

    print("BORDER----------------------------------")
    for n in soup.find_all('div'):
        # get attributes works for multiple results
        print n.get('id')

    print(soup.p)
    # soup . tag[attributes] only works for single results
    print(soup.p["style"])

    # prints out all the text form the page
    print(soup.get_text().encode("UTF-8"))
    print("BORDER----------------------------------")

    head_tags = soup.head
    #contents produces array of tags
    print(head_tags.contents)

    print("BORDER----------------------------------")
    print(soup.div)
    print(soup.div.attrs)
    # find via attributes
    print(soup.find(id="hplogo"))

    #children returns 1
    # descendants returns all the descendants

    #parent finds parent
    #siblings
    # next_sibling find the next sibling
    # previous_sibling find the previous sibling
    #same way
    # next_element finds the next element
    # previous_element find s the previous element


 #   for n in soup.div["class"]:
   #     print(n)



if __name__ == "__main__":
    start()
