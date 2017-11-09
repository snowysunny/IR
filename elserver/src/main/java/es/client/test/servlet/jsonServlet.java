package es.client.test.servlet;

import com.fasterxml.jackson.core.TreeCodec;
import com.hankcs.hanlp.suggest.Suggester;
import es.client.test.common.FileHandle;
import es.client.test.es.*;

import es.client.test.word2vec.Word2Vec;
import es.client.test.word2vec.WordEntry;
import jdk.nashorn.internal.ir.ObjectNode;
import org.codehaus.jackson.JsonNode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hadoop on 2017/03/21.
 */
public class jsonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //        Suggester suggester = new Suggester();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, NullPointerException, IOException {
        // 指定允许其他域名访问
        resp.setHeader("Access-Control-Allow-Origin","*");
        long starttime = new Date().getTime();
        System.out.println(starttime);
        TransportClient client = EsClient.getInstancei().getclient();

//        System.out.println("Start: success or failure");
        long startTime = System.currentTimeMillis();
        Suggester suggester = HanlpClient.getClient();
        long endloadhanlpTime = System.currentTimeMillis();
        System.out.println("LoadHanLPTime: " + (endloadhanlpTime - startTime));
        Word2Vec recommender = Word2VecClient.getClient();
        long endloadword2vecTime = System.currentTimeMillis();
        System.out.println("LoadWord2VecTime: " + (endloadword2vecTime - endloadhanlpTime));
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        Vector<String> titleArray =FileHandle.readByBufferedReader("/home/snowy/develop/java/elserver/word_set.txt");
//        for (String title : titleArray)
//            suggester.addSentence(title);
//        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        System.out.println(suggester.suggest("蓝球", 1));
//        System.out.println("YES OR NO");
//        long endTime = System.currentTimeMillis();
//        System.out.println("---------------------------------------------" + (endTime-startTime) + "ms");

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

        /**
         * 全字母判断
         * */
        String regex = "[a-zA-Z]+";
        Matcher m = Pattern.compile(regex).matcher(queryword);
        List<String> suggestresult = new ArrayList<String>();
        List<String> recommandresult = new ArrayList<String>();
        long startmatchTime = System.currentTimeMillis();
        if(m.matches()){
            suggestresult = suggester.suggest(queryword,4);
            queryword = suggestresult.get(0);
            long endmatchhanlpTime = System.currentTimeMillis();
            System.out.println("usehanlpmatchTime: " + (endmatchhanlpTime - startmatchTime));
        } else{
            Set<WordEntry> recommandset = recommender.distance(queryword);
            for(int i = 0; i < recommandset.size(); i++)
                recommandresult.add(recommandset.toArray()[i].toString().split("\t")[0]);
            long endmatchword2vecTime = System.currentTimeMillis();
            System.out.println("useword2vecmatchTime: " + (endmatchword2vecTime - startmatchTime));

        }


        System.out.println("queryword: " + queryword);
        String table1 = "common";
        String table2 = "weixin";
        String table3 = "weibo";
        String table4 = "wenshu";
        long startsearchTime = System.currentTimeMillis();

        int num = 100;
//        System.out.println(table1 + "," + table2 + "," + table3 + "," + table4);
        sr = eq.normalMultiQuery(queryword, num,table1, table2, table3, table4);
        //选择查询方式
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
                System.out.println();
                break;
            }
            default:
                sr = eq.normalMultiQuery(queryword, num, table1, table2, table3, table4);
        }
        long endsearchTime = System.currentTimeMillis();
        System.out.println("SearchTime: " + (endsearchTime - startsearchTime));
        SearchHit[] Sh = sr.getHits().getHits();
       /*
        esQuery eq = new esQuery(client);
       //        String queryword = "常州";
        SearchResponse sr;
        String querytypepara = req.getParameter("querytype");
        if (querytypepara == null || !Tool.isNumeric(querytypepara)){
            querytypepara = "2";
        }else
            querytypepara = req.getParameter("querytype");
        int querytype = Integer.valueOf(querytypepara).intValue();//Integer.valueOf(req.getParameter("querytype")).intValue();

        String queryword = req.getParameter("query");
        sr = eq.normalMultiQuery(queryword);
        //选择查询方式
        switch (querytype){
            case 0:
                sr = eq.contentQuery(queryword);
                break;
            case 1:
                sr = eq.titleQuery(queryword);
                break;
            case 2:
                sr = eq.normalMultiQuery(queryword);
               break;
            case 3:{
                sr = eq.mustFieldQuery(queryword, "title", 100);
                break;
            }
            case 4:{
                sr = eq.mustFieldQuery(queryword, "id", 1);
                break;
            }
            default:
                sr = eq.normalMultiQuery(queryword);
        }

        SearchHit[] Sh = sr.getHits().getHits();

        if(querytype >= 0 && querytype < 3 && Sh.length < 2){
            sr = eq.pinyinQuery(queryword);
        }
        Sh = sr.getHits().getHits();

        System.out.println(Sh.length);*/

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

            int zhs = 1;

            //String crawltime = "2000-00-00";
            String crawltime = String.valueOf(Sh[i].getSource().get("crawltime"));
            //String crawltime = Sh[i].getSource().get("crawltime").toString();
            String pubtime = Sh[i].getSource().get("pubtime").toString();
            String tag = Sh[i].getSource().get("tag").toString();
        //    System.out.println(Sh[i].getSource().get("tag"));
        /*    String tag = null;
            if(Sh[i].getSource().get("tag") == null)
                continue;
            else
                tag = Sh[i].getSource().get("tag").toString();*/

            Article page = new Article(id, title, crawltime, pubtime, content, url, tag);          //返回查询到一篇文档的内容

            articleList.add(page);
            //            System.out.println(Sh[i].getSource().get("content").toString());
        }

        ArticleTotal pageTotal = new ArticleTotal(articleList.size(), suggestresult, recommandresult, articleList);

        //调用GSON jar工具包封装好的toJson方法，可直接生成JSON字符串
        Gson gson = new Gson();
        System.out.println(gson);
//        System.out.println("pageTotal: " + pageTotal.toString());
        String json =  gson.toJson(pageTotal);


        //输出到界面
        System.out.println(json);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        out.println(json);
        out.flush();
        long endtime = new Date().getTime();
        System.out.println("pagedealwithTime: " + (endtime - endsearchTime));

        long spendtime = endtime - starttime;
        System.out.println("查询花费时间" + spendtime);
        System.out.println("-----------------------------------------------");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 指定允许其他域名访问
        resp.setHeader("Access-Control-Allow-Origin","*");
        // 响应类型
        //resp.setHeader('Access-Control-Allow-Methods:POST');
        // 响应头设置
        //header('Access-Control-Allow-Headers:x-requested-with,content-type');
        this.doGet(req, resp);
    }
}
