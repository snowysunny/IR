BOT_NAME = 'TestSpider'

SPIDER_MODULES = ['TestSpider.spiders']
NEWSPIDER_MODULE = 'TestSpider.spiders'

DOWNLOADER_MIDDLEWARES = {
    "TestSpider.middlewares.UserAgentMiddleware": 401,
}
ROBOTSTXT_OBEY = False

SCHEDULER = "TestSpider.scrapy_redis.scheduler.Scheduler"
SCHEDULER_PERSIST = True
SCHEDULER_QUEUE_CLASS = 'TestSpider.scrapy_redis.queue.SpiderPriorityQueue'
DUPEFILTER_CLASS = "TestSpider.scrapy_redis.dupefilter.RFPDupeFilter"

REDIS_URL = None

FILTER_URL = None
FILTER_DB = 0

MONGODB_DB = 'TestSpider'
MONGODB_COLLECTION = 'MySpider'
MONGODB_WEIBOCOLLECTION = 'Weibo'
MONGODB_WENSHUCOLLECTION = 'Wenshu'
MONGODB_BAIDUKWD_COLLECTION = "keywords"
MONGODB_START_URLS_COLLECTION= "starturls"
MONGODB_URL_FIELD = "url"
MONGODB_KWD_FIELD = "keyword"

'''custom configure as following'''
FILTER_HOST = 'localhost'
MONGODB_SERVER = 'localhost'
FILTER_PORT = 6379
MONGODB_PORT = 27017
Process_WeChatSpider = 1
PHANTOMJSPATH = 'E:\\phantomjs-windows\\bin\\phantomjs.exe'
REDIS_HOST = 'localhost'
Process_WenshuSpider = 1
Process_MySpider = 4
Process_baiduSpider = 4
REDIS_PORT = 6379
Process_SinaSpider = 1
REDIS_PASS = 'foobared'
FILTER_PASS = 'foobared'
