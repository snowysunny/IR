package es.client.test.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by hadoop on 2017/05/10.
 */
public class MongoDBClient {
    private static MongoDatabase mongoDatabase = null;
    public MongoDatabase getCollection(String collectionName) { return mongoDatabase; }

    // 定义一个私有的构造方法
    private MongoDBClient() {
        try{
            //连接到mongodb服务
            MongoClient mongoclient = new MongoClient("222.192.7.7", 27017);
            //连接到数据库
            MongoDatabase mongoDatabase = mongoclient.getDatabase("TestSpider");
        //    System.out.println("Connect to database successfully");

            //选择数据库中的集合
            //collection = mongoDatabase.getCollection(collectionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 将自身的实例对象设置为一个属性,并加上Static和final修饰符
    private static final MongoDBClient instance = new MongoDBClient();

    // 静态方法返回该类的实例
    public static MongoDBClient getInstancei() {
        return instance;
    }

}
