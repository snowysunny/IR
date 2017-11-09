package es.client.test.servlet;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import es.client.test.es.MongoDBSQL;
import es.client.test.mongodb.MongoDBJDBC;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hadoop on 2017/05/08.
 */
@WebServlet(name = "mongodbServlet")
public class mongodbServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 指定允许其他域名访问
        resp.setHeader("Access-Control-Allow-Origin","*");
        this.doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 指定允许其他域名访问
        resp.setHeader("Access-Control-Allow-Origin","*");
        try{
            PrintWriter out = new PrintWriter(resp.getOutputStream());
         //   MongoDBJDBC db = new MongoDBJDBC();
            MongoClient mongoclient = MongoDBSQL.getmongoclient("222.192.7.7", 27017);
            MongoDBSQL.connect(mongoclient, "TestSpider", "Weibo");
        //    db.connect("Weibo");
            long iMyWeiboCount = MongoDBSQL.findCountByTable();
            System.out.println(iMyWeiboCount);
            out.print("{\"total\":" + 7 +",\"WeiboNum\":" + iMyWeiboCount + ",\"weiborows\":[");
            for (int i = -6; i <= 0; i++){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, i);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String mDateTime=formatter.format(c.getTime());
                Document filter = new Document();
                filter.append("crawltime", mDateTime);
                long iCount = MongoDBSQL.findCountBy(filter);
                out.print("{\"time\":\"" +mDateTime + "\",\"num\":" +iCount);
                if(i != 0)
                    out.print("},");
                else
                    out.print("}],");
                //    System.out.println(iCount);
                out.flush();
            }

            MongoDBSQL.connect(mongoclient, "TestSpider", "MySpider");
         //   db.connect("MySpider");
            long iMyCommonCount = MongoDBSQL.findCountByTable();
            System.out.println(iMyCommonCount);
            out.print("\"CommonNum\":" + iMyCommonCount + ",\"rows\":[");
            for (int i = -6; i <= 0; i++){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, i);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String mDateTime=formatter.format(c.getTime());
                //    System.out.println(mDateTime);
                Document filter = new Document();
                filter.append("crawltime", mDateTime);
                long iCount = MongoDBSQL.findCountBy(filter);
                out.print("{\"time\":\"" +mDateTime + "\",\"num\":" +iCount);
                if(i != 0)
                    out.print("},");
                else
                    out.print("}],");
                //    System.out.println(iCount);
                out.flush();
            }

            MongoDBSQL.connect(mongoclient, "TestSpider", "Wenshu");
          //  db.connect("Wenshu");
            long iMyWenshuCount = MongoDBSQL.findCountByTable();
            System.out.println(iMyWenshuCount);
            out.print("\"WenshuNum\":" + iMyWenshuCount + ",\"wenshurows\":[");
            for (int i = -6; i <= 0; i++){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, i);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String mDateTime=formatter.format(c.getTime());
                //    System.out.println(mDateTime);
                Document filter = new Document();
                filter.append("crawltime", mDateTime);
                long iCount = MongoDBSQL.findCountBy(filter);
                out.print("{\"time\":\"" +mDateTime + "\",\"num\":" +iCount);
                if(i != 0)
                    out.print("},");
                else
                    out.print("}]}");
                //    System.out.println(iCount);
                out.flush();
            }

        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
