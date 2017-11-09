package es.client.test.servlet;

//多余页面

import com.google.gson.Gson;
import es.client.test.es.*;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static es.client.test.common.LoadURLJson.loadJson;

/**
 * Created by hadoop on 2017/06/04.
 */
@WebServlet(name = "advancequeryServlet")
public class advancequeryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 指定允许其他域名访问
        resp.setHeader("Access-Control-Allow-Origin","*");
        TransportClient client = EsClient.getInstancei().getclient();
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        //进行查询操作
        esQuery eq = new esQuery(client);
        SearchResponse sr;
        String querytypepara = req.getParameter("querytype");
        if (querytypepara == null || !Tool.isNumeric(querytypepara)){
            querytypepara = "2";
        }else
            querytypepara = req.getParameter("querytype");
        int querytype = Integer.valueOf(querytypepara).intValue();//Integer.valueOf(req.getParameter("querytype")).intValue();
        String queryword = req.getParameter("query");

        System.out.println();
        String table1 = "common";
        String table2 = "weixin";
        String table3 = "weibo";
        String table4 = "wenshu";

        int num = 100;
        System.out.println(table1 + "," + table2 + "," + table3 + "," + table4);
        sr = eq.normalMultiQuery(queryword, num,table1, table2, table3, table4);
        String queryword2 = req.getParameter("query-content2");
        System.out.println("queryword2:"+queryword2);
        if(queryword2==null || queryword2.equals("")){
     //       System.out.println("ttttttttttttttttttttttt1");
            switch (querytype){
                case 0:
                    sr = eq.contentQuery(queryword, num,table1, table2, table3, table4);
                    break;
                case 1:
                    sr = eq.titleQuery(queryword, num, table1, table2, table3, table4);
                    break;
                case 2:
                    sr = eq.normalMultiQuery(queryword, num, table1, table2, table3, table4);
                    break;
                case 3:{
                    sr = eq.mustFieldQuery(queryword, "title", num, table1, table2, table3, table4);
                    break;
                }
                case 4:{
                    sr = eq.mustFieldQuery(queryword, "id", 1, table1, table2, table3, table4);
                    break;
                }
                default:
                    sr = eq.normalMultiQuery(queryword, num, table1, table2, table3, table4);
            }
        }
        else{
    //        System.out.println("ttttttttttttttttttttttt2");
            switch (querytype){
                case 0:
                    sr = eq.filtercontent(queryword, queryword2, num,table1, table2, table3, table4);
                    break;
                case 1:{
                    String []querys =queryword2.split("\\s+");
                    sr = eq.filterField("title", queryword, querys, num, table1, table2, table3, table4);
                    break;
                }
                case 2:
                    sr = eq.filtermult(queryword, queryword2, num, table1, table2, table3, table4);
                    break;
                case 3:{
                    sr = eq.filtermustQuery(queryword, queryword2, "title", num, table1, table2, table3, table4);
                    break;
                }
                case 4:{
                    sr = eq.mustFieldQuery(queryword, "id", 1, table1, table2, table3, table4);
                    break;
                }
                default:
                    sr = eq.filtermult(queryword, queryword2, num, table1, table2, table3, table4);
            }
        }

        System.out.println("sr:"+sr);
        SearchHit[] Sh = sr.getHits().getHits();
        List<Article> articleList = new ArrayList<Article>();
        for(int i = 0; i < sr.getHits().getHits().length; ++i) {
            String id = Sh[i].getSource().get("id").toString();
            String url = Sh[i].getSource().get("url").toString();
            String title = Sh[i].getSource().get("title").toString();
            String content = Sh[i].getSource().get("content").toString();
            if(querytype != 4){
                if(content.length() > 200 ){
                    content = content.substring(0, 200)+"..";
                }
            }
            String crawltime = String.valueOf(Sh[i].getSource().get("crawltime"));
            String pubtime = Sh[i].getSource().get("pubtime").toString();
            String tag = Sh[i].getSource().get("tag").toString();
            Article page = new Article(id, title, crawltime, pubtime, content, url, tag);          //返回查询到一篇文档的内容
            articleList.add(page);

        }
        List<String> suggestresult = null;
        List<String> recommendresult = null;
        ArticleTotal pageTotal = new ArticleTotal(articleList.size(), suggestresult, recommendresult,  articleList);

        //调用GSON jar工具包封装好的toJson方法，可直接生成JSON字符串
        Gson gson = new Gson();
        System.out.println(gson);
        String json = gson.toJson(pageTotal);

        //输出到界面
        System.out.println(json);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        out.println(json);
        out.flush();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin","*");
        doPost(req, resp);
    /*    http://localhost:8080/urljson?starttime=2012-12-10&endtime=2017-06-04&table0=wenshu&table1=common&table2=weixin&field0=0&field1=0&field2=0&operator0=and&operator1=or&operator2=and&query0=%E8%A3%81%E5%AE%9A%E4%B9%A6&query1=%E4%B8%AD%E5%9B%BD&query2=%E4%B8%80%E6%A1%88
        http://localhost:8080/urljson?starttime=2012-12-10&endtime=2017-06-04&table0=wenshu&table1=common&table2=weixin&field0=0&field1=0field2=0&operator0=and&operator1=or&operator2=and&query0=%E8%A3%81%E5%AE%9A%E4%B9%A6&query1=%E4%B8%AD%E5%9B%BD&query2=%E4%B8%80%E6%A1%88
*/
    }
}
