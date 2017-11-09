# -*- coding: utf-8 -*-
from bs4 import Doctype, NavigableString
import re
import pymongo
import settings

def GenTxt(node):
        txt = ""
        for n in node.children:
            #if re.sub(re.compile("\s"),"",n.get_text()).__len__()==0:
            #    return ""
            if isinstance(n,Doctype):
                continue
            if isinstance(n,NavigableString):
                txt+=re.sub(re.compile("\n|\r"),'',n.string.strip())
                continue
            if n.name == "br":
                txt+="\n"
                continue
            if n.name in ["p","td","div","h1","h2","h3"]:
                txt += GenTxt(n)+"\n"
                continue
            if n.name in ["span","em","strong","b","font","i"]:
                txt += GenTxt(n)
                continue
            txt+=GenTxt(n)+"\n"
        return txt

def getDB():
    MONGODB_SERVER = 'localhost'
    MONGODB_PORT = 27017
    MONGODB_DB = 'TestSpider'
    if settings.MONGODB_SERVER is not None:
        MONGODB_SERVER = settings.MONGODB_SERVER
    if settings.MONGODB_PORT is not None:
        MONGODB_PORT = settings.MONGODB_PORT
    if settings.MONGODB_DB is not None:
        MONGODB_DB = settings.MONGODB_DB
    connection = pymongo.MongoClient(
        MONGODB_SERVER,
        MONGODB_PORT
    )
    db = connection[MONGODB_DB]
    return db