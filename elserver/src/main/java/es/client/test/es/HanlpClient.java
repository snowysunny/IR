package es.client.test.es;

import com.hankcs.hanlp.suggest.Suggester;
import es.client.test.common.FileHandle;

import java.util.Vector;

public class HanlpClient {

    private static Suggester suggestclient = null;
    public static Suggester getClient() {
        if(suggestclient == null){
            System.out.println("--------------------test---------------------------");
            Vector<String> titleArray =FileHandle.readByBufferedReader("/opt/software/tomcat/webapps/attachment-file/word_set.txt");
            suggestclient = new Suggester();
            for (String title : titleArray)
                suggestclient.addSentence(title);
        }
        return  suggestclient;
    }

    // 定义一个私有的构造方法
    @Deprecated
    private HanlpClient(){}

    // 将自身的实例对象设置为一个属性,并加上Static和final修饰符
    private static final HanlpClient instance = new HanlpClient();

    // 静态方法返回该类的实例
    public static HanlpClient getInstance(){
        return instance;
    }
}
