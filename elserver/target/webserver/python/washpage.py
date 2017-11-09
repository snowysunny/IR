# -*- coding: utf-8 -*-
from bs4 import BeautifulSoup, Comment, Tag
import re
from GlobalMethod import GenTxt, getDB
import sys
import traceback
import datetime

import chardet
import codecs

def washpage(source):
    soup = BeautifulSoup(source, 'lxml')
    for element in soup(text=lambda text: isinstance(text, Comment)):
        element.extract()
    [s.extract() for s in soup(['style', 'script', '[document]', 'head'])]
    maindiv = soup.findAll(["div", "tr", "td", "tbody", "table"], {"class": re.compile("main|content|contain")})
    maindiv.extend(soup.findAll(["div", "tr", "td", "tbody", "table"], {"id": re.compile("main|content|contain")}))
    footandtop = soup.findAll(["div", "tr", "td", "tbody", "table"], {"class": re.compile("foot|top|bottom")})
    footandtop.extend(soup.findAll(["div", "tr", "td", "tbody", "table"], {"id": re.compile("foot|top|bottom")}))
    for element in footandtop:
        element.extract()
    cdivs = []
    if len(maindiv) > 0:
        for div in maindiv[0].find_all(["div", "span", "tr"]):
            isleaf = True
            for child in div.children:
                if isinstance(child, Tag) and not (len(child.findAll(["div", "span", "tr"])) == 0):
                    isleaf = False
            if isleaf:
                cdivs.append(div)
        '''cdivs2 = [divs for divs in soup.find_all(["div","span","tr"]) if
                (len(child.findAll(["div","span","tr"])) == 0 for child in divs.children)]'''
    else:
        for div in soup.find_all(["div", "span", "tr"]):
            isleaf = True
            for child in div.children:
                if isinstance(child, Tag) and not (len(child.findAll(["div", "span", "tr"])) == 0):
                    isleaf = False
            if isleaf:
                cdivs.append(div)
        '''cdivs2 = [divs for divs in soup.find_all(["div","span","tr"]) if
                (len(child.findAll(["div","span","tr"])) == 0 for child in divs.children)]'''
    ddivs = list()
    pattern = re.compile(r"\s")
    for div in cdivs:
        notxtlen = [a.get_text() for a in div.findAll("a")]
        length = sum([txt.__len__() for txt in notxtlen])
        alllen = re.sub(pattern, '', div.get_text()).__len__()
        if alllen == 0:
            continue
        else:
            if float(length) / float(alllen) > 0.25:
                ddivs.append(div)
    for div in ddivs:
        div.extract()
    if (len(maindiv) > 0):
        [s.extract() for s in maindiv[0].findAll("a")]
        finalsoup = maindiv[0]
    else:
        [s.extract() for s in soup.findAll("a")]
        finalsoup = soup
    txt = re.sub(re.compile("\n+"), "\n", GenTxt(finalsoup))
    if re.sub(re.compile("\s"), "", txt).__len__() < 5:
        # print txt
        return None
    else:
        return txt

def filetostr(path):
    default_encoding = "utf-8"
    if (default_encoding != sys.getdefaultencoding()):
        reload(sys)
        sys.setdefaultencoding(default_encoding)
    file = open(path, "r")
    data = file.read()
    code = chardet.detect(data)
    file.close()
    file = codecs.open(path,"r",code["encoding"])
    str2 = ""
    lines = file.readlines()
    for line in lines:
        str2 = str2 + line + "\n"
    file.close()
    return str2

def washfromfile(path):
    str2= filetostr(path)
    txt = washpage(str2)
    return txt

def sentToFile(txt):
    data = {}
    data["txt"] = txt
    data["crawltime"] = datetime.datetime.now().strftime("%Y-%m-%d")
    file = open("D:/testM.txt","w")
    for key in data:
        file.write("%s:%s" % (key,data[key]))
    file.flush()
    file.close()

def sentToDB(db, txt, tag=""):
    data = {}
    data["txt"] = txt
    data["tag"] = tag
    data["crawltime"] = datetime.datetime.now().strftime("%Y-%m-%d")
    custom_collection = db["CUSTOM"]
    custom_collection.insert(data)

if __name__ == '__main__':
    try:
        db = getDB()
        if sys.argv[1].endswith(".txt"):
            #sentToFile(filetostr(sys.argv[1]))
            if len(sys.argv) > 2:
                sentToDB(db,filetostr(sys.argv[1]),sys.argv[2])
            else:
                sentToDB(db,filetostr(sys.argv[1]))
        elif sys.argv[1].endswith(".html"):
            #sentToFile(washfromfile(sys.argv[1]))
            if len(sys.argv) > 2:
                sentToDB(db,washfromfile(sys.argv[1]),sys.argv[2])
            else:
                sentToDB(db,washfromfile(sys.argv[1]))
    except:
        file = open("DEBUG.txt","w")
        sys.stdout = file
        sys.stderr = file
        traceback.print_exc()
