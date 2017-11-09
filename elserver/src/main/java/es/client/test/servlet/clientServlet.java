package es.client.test.servlet;

import com.hankcs.hanlp.suggest.Suggester;
import com.mongodb.client.MongoDatabase;
import es.client.test.es.EsClient;
import es.client.test.es.HanlpClient;
import es.client.test.mongodb.MongoDBClient;
import org.elasticsearch.client.transport.TransportClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by hadoop on 2017/03/21.
 * 实现单例连接ElasticSearch
 */
public class clientServlet extends HttpServlet
{
    TransportClient client=null;
    Suggester suggester=null;
    MongoDatabase mongoDatabase = null;
//    private static final long serialVersionUID = 1L;
    @Override
    public void destroy(){
        System.out.println("destroy success");
            super.destroy();
    }
    @Override
    public  void init() throws ServletException{
        EsClient.getInstancei();
        HanlpClient.getInstance();
//        MongoDBClient.getInstancei();
    }

}
