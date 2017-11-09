package es.client.test.es;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * Created by hadoop on 2017/03/21.
 */

//连接elasticsearch单例
public class EsClient {
    private static TransportClient client=null;
    public TransportClient getclient(){
        return  client;
    }

    // 定义一个私有的构造方法
    private EsClient() {
        try {
            Settings e = Settings.builder().put("client.transport.sniff", true).put("cluster.name", "es").build();
            client = (new PreBuiltTransportClient(e, new Class[0])).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("219.223.251.214"), 9300));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 将自身的实例对象设置为一个属性,并加上Static和final修饰符
    private static final EsClient instance = new EsClient();

    // 静态方法返回该类的实例
    public static EsClient getInstancei() {
        return instance;
    }

}
