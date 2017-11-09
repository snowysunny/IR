package es.client.test.servlet;

import com.mongodb.MongoClient;
import es.client.test.es.MongoDBSQL;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by hadoop on 2017/06/23.
 */
@WebServlet(name = "getKeywordServlet")
public class getKeywordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 指定允许其他域名访问
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        MongoClient mongoclient = MongoDBSQL.getmongoclient("222.192.7.7", 27017);
            MongoDBSQL.connect(mongoclient, "DebugSpider", "keywords");
            List<Document> keywords = MongoDBSQL.findAll();
            out.print("{\"keywords\":[");
            int i = 0;
            for(Document doc : keywords) {
                String keywordlist = doc.getString("keyword");
                String labellist = doc.getString("priority");
                out.print("{\"keywordlist\":\"" + keywordlist + "\",\"labellist\":\"" + labellist + "\"}");
                i++;
                if(i < keywords.size())
                    out.print(",");
            }
            out.print("]}");
            out.flush();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin","*");
        this.doPost(req, resp);
    }
}
