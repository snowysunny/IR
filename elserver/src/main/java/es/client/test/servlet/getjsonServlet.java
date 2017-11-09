package es.client.test.servlet;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import es.client.test.es.Article;
import es.client.test.es.ArticleTotal;
import es.client.test.es.EsClient;
import es.client.test.es.esQuery;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static es.client.test.common.LoadURLJson.receivePost;

/**
 * Created by hadoop on 2017/06/04.
 */
@WebServlet(name = "getjsonServlet")
public class getjsonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        /** 设置响应头允许ajax跨域访问 **/
        resp.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");

        System.out.println("Start:   ");
    //    Writer out1 = resp.getWriter();
        String json = receivePost(req);
//        JSONObject json= receivePost(req);
        System.out.println(json);
        TransportClient client = EsClient.getInstancei().getclient();
        //进行查询操作
        esQuery eq = new esQuery(client);
        PrintWriter out = new PrintWriter(resp.getOutputStream());

        if(json.equals("") || json == null){
            out.print("{}");
            out.flush();
            return;
        }

        //高级检索

        SearchResponse sr = eq.advanceSerach(json);
        if(sr == null || sr.equals("")){
            out.print("{}");
            out.flush();
            return;
        }

        SearchHit[] Sh = sr.getHits().getHits();
        List<Article> articleList = new ArrayList<Article>();
        for(int i = 0; i < sr.getHits().getHits().length; ++i) {
            String id = Sh[i].getSource().get("id").toString();
            String url = Sh[i].getSource().get("url").toString();
            String title = Sh[i].getSource().get("title").toString();
            String content = Sh[i].getSource().get("content").toString();
            if(content.length() > 200 ){
                content = content.substring(0, 200)+"..";
            }
            String crawltime = String.valueOf(Sh[i].getSource().get("crawltime"));

            //String crawltime = Sh[i].getSource().get("crawltime").toString();
            String pubtime = Sh[i].getSource().get("pubtime").toString();
            String tag = Sh[i].getSource().get("tag").toString();

            Article page = new Article(id, title, crawltime, pubtime, content, url, tag);          //返回查询到一篇文档的内容
            articleList.add(page);
        }
        List<String> suggestresult = null;
        List<String> recommendresult = null;
        ArticleTotal pageTotal = new ArticleTotal(articleList.size(), suggestresult, recommendresult, articleList);

        //调用GSON jar工具包封装好的toJson方法，可直接生成JSON字符串
        Gson gson = new Gson();
        System.out.println(gson);
        String json1 = gson.toJson(pageTotal);

        //输出到界面
        System.out.println(json1);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        out.println(json1);
        out.flush();




    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        doPost(req,resp);
    }
}
