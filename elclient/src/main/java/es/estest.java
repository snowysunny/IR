package es;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;

import static es.Bulkpro.Creatbulkprocess;

public class estest {
    public estest() {
    }

    public static void storeData1(TransportClient client) throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        String collectionName = "Weibo";
        List jsonData = DataFactory.getInitJsonData(collectionName);
        System.out.println(jsonData.size());

        int failnum = 0;
        for(int bulkResponse = 0; bulkResponse < jsonData.size(); ++bulkResponse) {
            // //bulkRequest.add(client.prepareIndex("database", "article", Integer.toString(bulkResponse)).setSource((XContentBuilder)jsonData.get(bulkResponse)));
            bulkRequest.add(client.prepareIndex("database", "weibo").setSource(JsonUtil.model2Json((Article) jsonData.get(bulkResponse))));
        }

        BulkResponse var5 = bulkRequest.get();
        if(var5.hasFailures()) {
            System.out.println(var5.buildFailureMessage());
            System.out.println("Failed!");
        }

    }

    public static void storeData(TransportClient client, String collectionName, String indexname) throws Exception {
        //String collectionName = "Wenshu";
        for(int i = 0; i < 80; i++){
            List jsonData = DataFactory.getInitJsonData1(collectionName, i);
            System.out.println(jsonData.size());
            BulkProcessor BPro= Bulkpro.Creatbulkprocess(client);
            for(int bulkResponse = 0; bulkResponse < jsonData.size(); ++bulkResponse) {
                //    BPro.add(new IndexRequest("database", "article", Integer.toString(bulkResponse)).source((XContentBuilder)jsonData.get(bulkResponse)));
                BPro.add(new IndexRequest("database", indexname).source(JsonUtil.model2Json((Article)jsonData.get(bulkResponse))));
            }
            BPro.flush();
            BPro.close();

        }

    }

    public static void storeSuggestWord(TransportClient client) throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        String path = "/home/snowy/develop/java/elclient/file/sougou_output_new.txt";
//        String path = "/home/snowy/develop/java/elclient/file/test";
      //  List Wordtemp = ReadFile.readWord(path);
        List Wordtemp = DataFactory.getInitJsonSuggestWord(path);
        int num = Wordtemp.size();
        System.out.println("storeSuggestWord： " + num);

        for(int bulkResponse = 0; bulkResponse < num; ++bulkResponse) {
      //      bulkRequest.add(client.prepareIndex("database", "article", Integer.toString(bulkResponse)).setSource((XContentBuilder)Wordtemp.get(bulkResponse)));
            bulkRequest.add(client.prepareIndex("database", "art_suggest").setSource(JsonUtil.suggestword2Json((String)Wordtemp.get(bulkResponse))));
        }

        BulkResponse var6 = (BulkResponse)bulkRequest.get();
        if(var6.hasFailures()) {
            System.out.println(var6.buildFailureMessage());
            System.out.println("Failed!");
        }

    }

    @Deprecated
    public static void main(String[] args) throws Exception {
        //设置集群名称:es为集群的名称
        Settings settings = Settings.builder().put("client.transport.sniff", true).put("cluster.name", "es").build();
        //创建client，设置es服务器的IP地址和端口号
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("219.223.251.214"), 9300));
        System.out.println("connecting elasticsearch success");
    //    System.out.println("Wenshu start");
    //    storeData(client, "Wenshu", "wenshu");


        /**
         * very important
         * */
//        System.out.println("Weibo start");
//        storeData(client, "Weibo", "weibo");
//        System.out.println("Weixin start");
//        storeData(client, "Weixin", "weixin");
//        System.out.println("MySpider start");
//        storeData(client, "MySpider", "common");
//        storeSuggestWord(client);


//        DataFactory.getWrite("MySpider", 40);




        esQuery eq = new esQuery(client);
        //SearchResponse sr =  eq.mustFieldQuery("中国","title");
        //SearchResponse sr =  eq.normalMultiQuery("中国"); //eq.advanceSerach();
        //SearchResponse sr = eq.filtermustQuery("杭州","萧山","title");
      //  SearchResponse sr = eq.titleQuery("杭州");
    /*    String querr = "萧山        最美";
        String []query =querr.split("\\s+");
        System.out.println(query.length + query[1]);
        SearchResponse sr = eq.filtertitle("杭州", query);
    */ //   SearchResponse sr = eq.filtermult("中国","世界");


//        //10-27
//        SearchResponse sr = eq.advanceSerach();
//        if(sr == null || sr.equals("")){
//            System.out.println("failed");
//            return;
//        }
//        SearchHit[] Sh = sr.getHits().getHits();
//
//        System.out.println(Sh.length);
//        for(int i = 0; i < sr.getHits().getHits().length; ++i) {
//            System.out.println(Sh[i].getSource().get("title"));
//            System.out.println(Sh[i].getSource().get("tag"));
//            System.out.println(Sh[i].getSource().get("pubtime"));
//            System.out.println(Sh[i].getScore());
//            System.out.println();
//        }


        String table1 = "";
        String table2 = "";
        String table3 = "weixin";
        String table4 = "common";

//        MultiSearchResponse musr = eq.mutitableQuery("法律短板","title", table1, table2, table3, table4);
        SearchResponse sr = eq.titleQuery("习近平");
        SearchHit[] Sh = sr.getHits().getHits();
        for(int i = 0; i < sr.getHits().getHits().length; ++i) {
//            String id = Sh[i].getSource().get("id").toString();
//            String url = Sh[i].getSource().get("url").toString();
//            String title = Sh[i].getSource().get("title").toString();
//            String content = Sh[i].getSource().get("content").toString();
//            String crawltime = String.valueOf(Sh[i].getSource().get("crawltime"));
//            String pubtime = Sh[i].getSource().get("pubtime").toString();
//            String tag = Sh[i].getSource().get("tag").toString();
//
            System.out.println("Title: " + Sh[i].getSource().get("title").toString());
            System.out.println("---------------------------------------------------");
            System.out.println();
            //String crawltime = "2000-00-00";
            //String crawltime = Sh[i].getSource().get("crawltime").toString();



        }



      /*  for (MultiSearchResponse.Item item : musr.getResponses()) {
            SearchResponse sr = item.getResponse();
            SearchHit[] Sh = sr.getHits().getHits();
            Sh = sr.getHits().getHits();

            System.out.println(Sh.length);
            for(int i = 0; i < sr.getHits().getHits().length; ++i) {
                System.out.println("title: " + Sh[i].getSource().get("title").toString());
//                System.out.println("tag: " + Sh[i].getSource().get("tag").toString());
//                System.out.println("pubtime: " + Sh[i].getSource().get("pubtime").toString());
                System.out.println("----------------------------------------------------------");
                String type = null;
                if(Sh[i].getSource().get("type") == null)
                    continue;
                else
                    type = Sh[i].getSource().get("type").toString();

                //            System.out.println(Sh[i].getSource().get("content").toString());
            }
        }*/
        //storeData(client);
        //storeSuggestWord(client);
//        System.out.println("completed");
        client.close();
    }
}



/*        Runnable runnable = new Runnable() {
            public void run() {
                estest et = new estest();
                Settings e = Settings.builder().put("client.transport.sniff", true).put("cluster.name", "es").build();

                InetSocketTransportAddress ista = null;
                try {
                    ista = new InetSocketTransportAddress(InetAddress.getByName("222.192.7.7"), 9300);
                    TransportClient client = (new PreBuiltTransportClient(e).addTransportAddress(ista));
                    System.out.println("connecting elasticsearch success");
                    et.storeData(client);
                    // storeSuggestWord(client);
                    System.out.println("completed");
                    client.close();
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 0, 86400, TimeUnit.SECONDS);
    }*/



























/*
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;


public class estest {

    //连接elasticsearch
    public static void Link_ES(){
        try {
            //设置集群名称
            Settings settings = Settings.builder().put("client.transport.sniff",true).put("cluster.name", "es").build();
            //创建client
            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.22.207.12"), 9300));

            //搜索数据
            GetResponse response = client.prepareGet("blog", "article", "1").execute().actionGet();
            //输出结果
            System.out.println(response.getSourceAsString());
            //关闭client
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static XContentBuilder getMapping(){
        XContentBuilder mapping = null;
        try{

        } catch (Exception e){
            e.printStackTrace();
        }
        return mapping;
    }

    public static void main(String[] args){
        estest test = new estest();
        test.Link_ES();


    }
}
*/


/*
            String json = "{" +
                    "\"id\":1," +
                    "\"title\":\"人生\"," +
                    "\"time\":\"2013-01-30\"," +
                    "\"content\":\"路漫漫其修远兮，吾将上下而求索\"," +
                    "\"url\":\"http://www.baidu.com\"" +
                    "}";
            System.out.println(json);
            IndexResponse response = client.prepareIndex("article", "text")
                    .setSource(json)
                    .get();
*/

/*    public static void storeWord(TransportClient client,String path) throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        List jsonWord = DataFactory.getInitJsonWord(path);
        System.out.println(jsonWord.size());

        for(int bulkResponse = 0; bulkResponse < jsonWord.size(); ++bulkResponse) {
            bulkRequest.add(client.prepareIndex("database", "art_suggest", Integer.toString(bulkResponse)).setSource((XContentBuilder)jsonWord.get(bulkResponse)));
        }

        BulkResponse var4 = (BulkResponse)bulkRequest.get();
        if(var4.hasFailures()) {
            System.out.println("------------------KKKKKKKKKKKKKKKKKkkkk---" + var4.buildFailureMessage());
            System.out.println("Failed!");
        }

    }*/