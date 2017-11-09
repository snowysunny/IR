package es.client.test.common;

import net.sf.json.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hadoop on 2017/06/03.
 */
public class LoadURLJson {
    public static String loadJson (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    /*public static void main(String[] args) {
        String url = "http://api.map.baidu.com/telematics/v3/weather?location=%E6%88%90%E9%83%BD&output=json&ak=rnm8udmHdWaHFWZTO2tuTiG8";
//        String url = "http://www.kuaidi100.com/query?type=yunda&postid=1201386764793";
        String json = loadJson(url);
        System.out.println(json);
        System.out.println("----------");
        JSONObject jsonObject=JSONObject.fromObject(json);
        System.out.println(jsonObject.get("message"));  //其中id为json中某个key，检测是否可以获得value值
//        System.out.println(json.toCharArray().length);
    }*/

    public static String receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {

        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder("");
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        System.out.println("hhhhhhhhhhhhhhh: " + sb);
        String json = sb.toString();
        //将json字符串转换为json对象
        //JSONObject json=JSONObject.fromObject(sb.toString());
        return json;
    }
}
