package es;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hadoop on 2017/03/11.
 */

public class ReadFile {
    public ReadFile() {
    }

    public static File[] getFileName(String path) {
        File f = new File(path);
        if(!f.exists()) {
            System.out.println(path + " not exists");
            return null;
        } else {
            File[] fa = f.listFiles();
            return fa;
        }
    }

    public static List<String> readWord(String path) throws Exception {
        ArrayList list = new ArrayList();
        InputStreamReader ir = null;
        BufferedReader br = null;
        FileInputStream inputStream = new FileInputStream(path);
        ir = new InputStreamReader(inputStream);
        br = new BufferedReader(ir);
        String line = null;
        String[] words = null;

        while((line = br.readLine()) != null) {
//            words = line.split("\t");
            list.add(line);
        }

        return list;
    }

    public static Article readFile(String id, String filename) throws Exception {
        InputStreamReader ir = null;
        BufferedReader br = null;
        Object outputStream = null;
        FileInputStream inputStream = new FileInputStream(filename);
        ir = new InputStreamReader(inputStream);
        br = new BufferedReader(ir);
        String crawltime = null;
        String pubtime = null;
        String title = null;
        String content = null;
        String url = null;
        String tag = null;
        String line = null;
        StringBuffer strBuffer = new StringBuffer();

        for(int index = 0; (line = br.readLine()) != null; ++index) {
            if(index == 1) {
                url = line;
            } else if(index == 3) {
                title = line;
            } else if(index == 4) {
                SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                crawltime = a.format(date);
            } else if(index>=5){
                strBuffer.append(line + "\n");
            }
        }

        content = strBuffer.toString();
        Article article = new Article(id, title, crawltime, pubtime, content, url, tag);
        ir.close();
        br.close();
        return article;
    }
}