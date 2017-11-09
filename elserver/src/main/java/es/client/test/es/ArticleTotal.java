package es.client.test.es;

import java.util.List;

/**
 * Created by hadoop on 2017/03/21.
 */
public class ArticleTotal {
    private int total;          //返回查询数目
    private List<String> suggeter;
    private List<String> recommender;
    private List<Article> rows; //返回查询列表

    public ArticleTotal(){}

    public ArticleTotal(int total, List<String> suggeter, List<String> recommender, List<Article> rows){
        this.total = total;
        this.suggeter = suggeter;
        this.recommender = recommender;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<String> getSuggester() {
        return suggeter;
    }

    public void setSuggeter(List<String> suggeter) {
        this.suggeter = suggeter;
    }

    public List<String> getRecommender() {
        return recommender;
    }

    public void setRecommender(List<String> recommender) {
        this.recommender = recommender;
    }

    public List<Article> getRows() {
        return rows;
    }

    public void setRows(List<Article> rows) {
        this.rows = rows;
    }
}
