package es;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by hadoop on 2017/03/11.
 */

public class JsonUtil {
    public JsonUtil() {
    }

    public static XContentBuilder model2Json(Article article) {
        XContentBuilder jsonData = null;

        try {
            jsonData = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id", article.getId())
                    .field("title", article.getTitle())
                    .field("carwltime", article.getCrawltime())
                    .field("pubtime", article.getPubtime())
                    .field("content", article.getContent())
                    .field("url", article.getUrl())
                    .field("tag", article.getTag())
                    .startObject("suggest")
                    .field("input","123")
                    .endObject()
                    .endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonData;
    }

    public static XContentBuilder word2Json(List<String> word) {
        XContentBuilder jsonWord = null;

        try {
            jsonWord = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("suggest")
                    .field("input", word)
                    .endObject().endObject();
           /* jsonWord = XContentFactory.jsonBuilder()
                    .startObject().field("suggest", word).endObject();*/
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return jsonWord;
    }

    public static XContentBuilder suggestword2Json(String word) {
        XContentBuilder jsonWord = null;

        try {
            jsonWord = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("suggest")
                    .field("input", word)
                    .endObject().endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonWord;
    }
}
