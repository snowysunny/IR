package es;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import static es.DateExchange.dateExchange;

/**
 * Created by hadoop on 2017/03/11.
 */

public class DataFactory {
    public static DataFactory dataFacetory = new DataFactory();

    private DataFactory() {
    }

    public DataFactory getInstance() {
        return dataFacetory;
    }

    //            /8   public static List<XContentBuilder> getInitJsonData() throws Exception {

    @Deprecated
    public static List<Article> getInitJsonData(String collectionName) throws Exception {
        ArrayList list = new ArrayList();

        //检索条件为爬取时间
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -13);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String mDateTime=formatter.format(c.getTime());
        System.out.println(mDateTime);

        //连接到mongodb服务
        MongoClient mongoClient = new MongoClient("116.62.148.121", 27017);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("DebugSpider");
        System.out.println("Connect to database successfully");

        //选择数据库中的集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        System.out.println("集合"+ collectionName +"选择成功！");

        //检索
        /**
         * 1.获取迭代其FindIterable<Document>
         * 2.获取游标MongoCursor<Document>
         * 3.通过游标便利检索出的文档集合
         * */
        //按照时间进行检索
        FindIterable<Document> findIterable = collection.find(Filters.eq("crawltime", mDateTime));
//        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        Article mongodbdata = null;
        int statictisnum = 0;

        try{
            while(mongoCursor.hasNext()){
                Document Cursor = mongoCursor.next();
                if(Cursor.isEmpty()){
                    System.out.println("Cursor is null");
                    continue;
                }
                System.out.println();
                String id = Cursor.getString("_id");
                String title = Cursor.getString("title");
                String url = Cursor.getString("url");
                String crawltime = Cursor.getString("crawltime");
                String pubtime = Cursor.getString("pubtime");
                String tag = Cursor.getString("Tag");
                String content = Cursor.getString("content");
                if(pubtime == null || pubtime.equals(""))
                    pubtime = "1111-11-11";
                if(collectionName.equals("Weibo")){
                    pubtime = dateExchange(pubtime);
                }
                if(pubtime == null || pubtime.equals(""))
                    pubtime = "1111-11-11";
                statictisnum ++;
                /*String type = null;
                if(collectionName.equals("MySpider"))
                    type = "common";
                else
                    type = "weibo";*/
                mongodbdata = new Article(id, title, crawltime, pubtime, content, url, tag);
                list.add(mongodbdata);
                if(statictisnum % 1000 == 0)
                    System.out.println("statictisnum: " + statictisnum);
                //            /8   list.add(JsonUtil.model2Json(mongodbdata));
            }
        }catch (NoSuchElementException e){
            System.out.println("False!");
            e.printStackTrace();

        }finally {

            mongoCursor.close();
            mongoClient.close();
            return list;

        }
    }

    @Deprecated
    public static List<Article> getInitJsonData1(String collectionName, int i) throws Exception {
        ArrayList list = new ArrayList();

        //检索条件为爬取时间
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -i);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String mDateTime=formatter.format(c.getTime());
        System.out.println(mDateTime);

        //连接到mongodb服务
        MongoClient mongoClient = new MongoClient("116.62.148.121", 27017);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("DebugSpider");
        System.out.println("Connect to database successfully");

        //选择数据库中的集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        System.out.println("集合"+ collectionName +"选择成功！");

        //检索
        /**
         * 1.获取迭代其FindIterable<Document>
         * 2.获取游标MongoCursor<Document>
         * 3.通过游标便利检索出的文档集合
         * */
        //按照时间进行检索
        FindIterable<Document> findIterable = collection.find(Filters.eq("crawltime", mDateTime));
//        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        Article mongodbdata = null;
        int statictisnum = 0;

        try{
            while(mongoCursor.hasNext()){
                Document Cursor = mongoCursor.next();
                if(Cursor.isEmpty()){
                    System.out.println("Cursor is null");
                    continue;
                }
                String id = Cursor.getString("_id");
                String title = Cursor.getString("title");
                String url = Cursor.getString("url");
                String crawltime = Cursor.getString("crawltime");
                String pubtime = Cursor.getString("pubtime");
                String tag = Cursor.getString("Tag");
                String content = Cursor.getString("content");
                if(pubtime == null || pubtime.equals(""))
                    pubtime = "1111-11-11";
                if(collectionName.equals("Weibo")){
                    pubtime = dateExchange(pubtime);
                }
                if(pubtime == null || pubtime.equals(""))
                    pubtime = "1111-11-11";
                statictisnum ++;
                /*String type = null;
                if(collectionName.equals("MySpider"))
                    type = "common";
                else
                    type = "weibo";*/
                mongodbdata = new Article(id, title, crawltime, pubtime, content, url, tag);
                list.add(mongodbdata);
                if(statictisnum % 1000 == 0)
                    System.out.println("statictisnum: " + statictisnum);
                //            /8   list.add(JsonUtil.model2Json(mongodbdata));
            }
        }catch (NoSuchElementException e){
            System.out.println("False!");
            e.printStackTrace();

        }finally {

            mongoCursor.close();
            mongoClient.close();
            return list;

        }
    }


    public static List<String> getInitJsonSuggestWord(String path) throws Exception {
        if (path.equals("")) {
            path = "/home/hadoop/develop/testFile/w5";
        }

    //    ArrayList listWord = new ArrayList();
        List Wordtemp = ReadFile.readWord(path);

       /* for (int i = 0; i < Wordtemp.size(); ++i) {
            listWord.add(JsonUtil.word2Json(Wordtemp));
        }*/

        return Wordtemp;
    }

    public static void getWrite(String collectionName, int i) throws Exception {


        //连接到mongodb服务
        MongoClient mongoClient = new MongoClient("116.62.148.121", 27017);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("DebugSpider");
        System.out.println("Connect to database successfully");

        //选择数据库中的集合
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        System.out.println("集合"+ collectionName +"选择成功！");

        int statictisnum = 0;
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/home/snowy/" + collectionName)),true);

        try{
            //检索
            /**
             * 1.获取迭代其FindIterable<Document>
             * 2.获取游标MongoCursor<Document>
             * 3.通过游标便利检索出的文档集合
             * */
            //按照时间进行检索
            //检索条件为爬取时间
            for(i = 0; i < 30; i++) {
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, -i);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String mDateTime=formatter.format(c.getTime());
                System.out.println(mDateTime);
//                FindIterable<Document> findIterable = collection.find(Filters.eq("crawltime", mDateTime));
        FindIterable<Document> findIterable = collection.find();
                MongoCursor<Document> mongoCursor = findIterable.iterator();
                while(mongoCursor.hasNext()){
                    Document Cursor = mongoCursor.next();
                    if(Cursor.isEmpty()){
                        System.out.println("Cursor is null");
                        continue;
                    }
                    String title = Cursor.getString("title");
                    pw.println(title);
                    String tag = Cursor.getString("Tag");
                    pw.println(tag);
                    String content = Cursor.getString("content");
                    pw.println(content);

                    statictisnum ++;
                    if(statictisnum % 1000 == 0)
                        System.out.println("statictisnum: " + statictisnum);
                    //            /8   list.add(JsonUtil.model2Json(mongodbdata));
                }
            }
        }catch (NoSuchElementException e){
            System.out.println("False!");
            e.printStackTrace();

        }finally {
            mongoClient.close();
        }
    }

}



/*
    public static List<XContentBuilder> getInitJsonData(String path, int n) throws Exception {
        if (path.equals("")) {
            System.exit(1);
        }
        ArrayList list = new ArrayList();
        File[] fa = ReadFile.getFileName(path);
        boolean m = false;
        int length;

        if (fa.length > n) {
            length = n;
        } else {
            length = fa.length;
        }
        for (int i = 0; i < length; ++i) {
            Article temp = ReadFile.readFile(i, fa[i].toString());
            list.add(JsonUtil.model2Json(temp));
        }

        return list;
    }
*/


/*
    public static List<XContentBuilder> getInitJsonWord(String path) throws Exception {
        if (path.equals("")) {
            path = "/home/hadoop/develop/testFile/w4";
        }

        ArrayList listWord = new ArrayList();
        List Wordtemp = ReadFile.readWord(path);
        listWord.add(JsonUtil.word2Json(Wordtemp));
        return listWord;
    }*/