package es.client.test.servlet;

import es.client.test.es.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hadoop on 2017/03/21.
 */
public class SuggestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    @Deprecated
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin","*");
        //发送 GET 请求
/*        String s= HttpRequest.sendGet("http://222.192.7.7:9200/database/article/_search", "q=title:1");
        System.out.println(s);
        System.out.println("Faild");*/

        String query = req.getParameter("query");
        //发送 POST 请求
        String param = "{\n" +
                "    \"suggest\": {\n" +
                "        \"searchsuggest\" : {\n" +
                "            \"text\" : \""
                +query
                +"\",\n" +
                "            \"completion\" : {\n" +
                "                \"field\" : \"suggest\"\n" +
                "          }\n" +
                "      }\n" +
                "  }\n" +
                "}";
        System.out.println("Suggest Running!");

//        String jsonStr = "{\"id\":\"1\",\"name\":\"a1\",\"obj\":{\"id\":11,\"name\":\"a11\",\"array\":[{\"id\":111,\"name\":\"a111\"},{\"id\":112,\"name\":\"a112\"}]}}";
        String sr=HttpRequest.sendPost("http://219.223.251.214:9200/database/art_suggest/_search", param);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        out.println(sr);
        out.flush();
        System.out.println(sr);
    }

    @Override
    @Deprecated
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin","*");
        this.doGet(req, resp);
    }
}
