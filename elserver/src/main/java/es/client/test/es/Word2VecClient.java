package es.client.test.es;

import java.io.IOException;
import java.util.Vector;

import com.hankcs.hanlp.suggest.Suggester;
import es.client.test.common.FileHandle;
import es.client.test.word2vec.Word2Vec;

public class Word2VecClient {
    private static Word2Vec recommandclient = null;

    public static Word2Vec getClient() throws IOException {
        if(recommandclient == null){
            System.out.println("--------------------Rcommandtest---------------------------");
            recommandclient = new Word2Vec();
            recommandclient.loadJavaModel("/opt/software/tomcat/webapps/attachment-file/javaVector");
        }
        return  recommandclient;
    }

    // 定义一个私有的构造方法
    @Deprecated
    private Word2VecClient(){}
}
