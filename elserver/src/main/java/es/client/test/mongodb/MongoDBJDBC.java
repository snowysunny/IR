package es.client.test.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 2017/05/10.
 */
public class MongoDBJDBC {
    private static MongoDatabase mongoDatabase;

    private static MongoCollection<Document> collection;
    /**
     * 链接数据库
     * @param collectionName            集合名称
     */
    public void connect(String collectionName) {
        //选择数据库中的集合
        collection = this.mongoDatabase.getCollection(collectionName);
        System.out.println("Connect to " + collectionName + " successfully");
    }

    /**
     * 插入一个文档
     *@param document      文档
     */
    public static void insert(Document document) {
        collection.insertOne(document);
    }

    /**
     * 查询所有文档
     * @return 所有文档集合
     */
    public static List<Document> findAll() {
        List<Document> results = new ArrayList<Document>();
        FindIterable<Document> iterables = collection.find();
        MongoCursor<Document> cursor = iterables.iterator();
        while (cursor.hasNext()) {
            results.add(cursor.next());
        }
        return results;
    }

    /**
     * 根据条件查询
     * @param filter
     *查询条件 //注意Bson的几个实现类，BasicDBObject, BsonDocument, BsonDocumentWrapper, CommandResult, Document, RawBsonDocument
     * @return 返回查询到的数量
     */
    public static long findCountBy(Bson filter) {
        long icount = collection.count(filter);
        return icount;
    }

    /**
     * 查询集合中的table中的文档数目
     * @return 返回查询到的数量
     */
    public static long findCountByTable() {
        long icount = collection.count();
        return icount;
    }

    /**
     * 根据条件查询
     * @param filter
     *查询条件
     * @return 返回集合列表
     */
    public static List<Document> findBy(Bson filter) {
        List<Document> results = new ArrayList<Document>();
        FindIterable<Document> iterables = collection.find(filter);
        MongoCursor<Document> cursor = iterables.iterator();
        while (cursor.hasNext()) {
            results.add(cursor.next());
        }
        return results;
    }

    /**
     * 更新查询到的第一个
     * @param filter    查询条件
     * @param update    更新文档
     * @return 更新结果
     */
    public static UpdateResult updateOne(Bson filter, Bson update) {
        UpdateResult result = collection.updateOne(filter, update);
        return result;
    }

    /**
     * 更新查询到的所有的文档
     * @param filter    查询条件
     * @param update    更新文档
     * @return 更新结果
     */
    public static UpdateResult updateMany(Bson filter, Bson update) {
        UpdateResult result = collection.updateMany(filter, update);
        return result;
    }

    /**
     * 更新一个文档, 结果是replacement是新文档，老文档完全被替换
     * @param filter    查询条件
     * @param replacement   更新文档
     */
    public static void replace(Bson filter, Document replacement) {
        collection.replaceOne(filter, replacement);
    }

    /**
     * 根据条件删除一个文档
     * @param filter    查询条件
     */
    public static void deleteOne(Bson filter) {
        collection.deleteOne(filter);
    }

    /**
     * 根据条件删除多个文档
     * @param filter    查询条件
     */
    public static void deleteMany(Bson filter) {
        collection.deleteMany(filter);
    }
}