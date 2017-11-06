package es;


/**
 * Created by hadoop on 2017/03/11.
 */
public class Article {
    private String id;
    private String title;
    private String crawltime;
    private String pubtime;
    private String content;
    private String url;
    private String tag;

    public Article() {
    }

    public Article(String id, String title, String crawltime, String pubtime, String content, String url, String tag) {
        this.id = id;
        this.title = title;
        this.crawltime = crawltime;
        this.pubtime = pubtime;
        this.content = content;
        this.url = url;
        this.tag = tag;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCrawltime() {
        return this.crawltime;
    }

    public void setCrawltime(String crawltime) {
        this.crawltime = crawltime;
    }

    public String getPubtime() {
        return this.pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() { return this.tag;}

    public void setTag(String tag) {
        this.tag = tag;
    }
}
