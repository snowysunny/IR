package es.client.test.es;

/**
 * Created by hadoop on 2017/03/20.
 */

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

public class esQuery {
    TransportClient client=null;

    public esQuery(TransportClient TranCli) {
        if (TranCli != null) {
            this.client=TranCli;
        } else {
            System.out.printf("tranclient is error", new Object[0]);
        }

    }

    public SearchResponse marchall() {
        SearchResponse response=(SearchResponse) this.client.prepareSearch(new String[0]).get();
        return response;
    }

    public BoolQueryBuilder insertfilter(BoolQueryBuilder qb, String field, String query) {
        qb=qb.filter(termQuery(field, query));
        return qb;
    }

    public SearchResponse normalMultiQuery(String query_content, int num, String table1, String table2, String table3, String table4) {
        SearchResponse rs=(SearchResponse) this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4})
                .setQuery(QueryBuilders.multiMatchQuery(query_content, new String[]{"content", "title"}))
                .setFrom(0).setSize(num).setExplain(true).get();
        return rs;
    }

    public SearchResponse filtermult(String query_content, String query_content1, int num, String table1, String table2, String table3, String table4) {
        SearchRequestBuilder builder=this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4}).setSearchType(SearchType.DEFAULT).setFrom(0).setSize(num);
        BoolQueryBuilder qb=boolQuery().must(QueryBuilders.multiMatchQuery(query_content, new String[]{"content", "title"})).filter((QueryBuilders.multiMatchQuery(query_content1, new String[]{"content", "title"})).field("content").field("title"));
        builder.setQuery(qb);
        SearchResponse response=(SearchResponse) builder.execute().actionGet();
        return response;
    }

    public SearchResponse titleQuery(String query_content, int num, String table1, String table2, String table3, String table4) {
        SearchResponse rs=(SearchResponse) this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4})
                .setQuery(QueryBuilders.matchQuery("title", query_content))
                .setFrom(0).setSize(num).setExplain(false).get();
        return rs;
    }

    public SearchResponse filterField(String field, String query_content, String[] query, int num, String table1, String table2, String table3, String table4) {
        SearchRequestBuilder builder=this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4})
                .setSearchType(SearchType.DEFAULT).setFrom(0).setSize(num);
        BoolQueryBuilder qb=boolQuery().must(QueryBuilders.matchQuery(field, query_content));
        if (query.length == 0)
            qb=boolQuery().must(QueryBuilders.matchQuery(field, query_content));
        else {
            for (int i=0; i < query.length; i++) {
                qb=insertfilter(qb, field, query[i]);
            }
        }
        builder.setQuery(qb);
        SearchResponse response=(SearchResponse) builder.execute().actionGet();
        return response;
    }

    public SearchResponse contentQuery(String query_content, int num, String table1, String table2, String table3, String table4) {
        SearchResponse rs=(SearchResponse) this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4})
                .setQuery(QueryBuilders.matchQuery("content", query_content)).setFrom(0).setSize(num).setExplain(false).get();
        return rs;
    }

    public SearchResponse filtercontent(String query_content, String query_content1, int num, String table1, String table2, String table3, String table4) {
        SearchRequestBuilder builder=this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4}).setSearchType(SearchType.DEFAULT).setFrom(0).setSize(num);
        BoolQueryBuilder qb=boolQuery().must(QueryBuilders.matchQuery("content", query_content)).filter(QueryBuilders.matchQuery("content", query_content1));
        builder.setQuery(qb);
        SearchResponse response=(SearchResponse) builder.execute().actionGet();
        return response;
    }

    //must 即 and
    public SearchResponse mustFieldQuery(String query_content, String Field, int num, String table1, String table2, String table3, String table4) {
        SearchRequestBuilder builder=this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4})
                .setSearchType(SearchType.DEFAULT).setFrom(0).setSize(num);
        BoolQueryBuilder qb=QueryBuilders.boolQuery().must((new QueryStringQueryBuilder(query_content)).field(Field));
        builder.setQuery(qb);
        SearchResponse response=(SearchResponse) builder.execute().actionGet();
        return response;
    }

    public SearchResponse filtermustQuery(String query_content, String query_content1, String Field, int num, String table1, String table2, String table3, String table4) {
        SearchRequestBuilder builder=this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4})
                .setSearchType(SearchType.DEFAULT).setFrom(0).setSize(num);
        QueryBuilder qb=boolQuery().must((new QueryStringQueryBuilder(query_content)).field(Field)).filter((new QueryStringQueryBuilder(query_content1)).field(Field));
        builder.setQuery(qb);
        SearchResponse response=(SearchResponse) builder.execute().actionGet();
        return response;
    }

    //should 即 or
    public SearchResponse shouldFieldQuery(String query_content, String Field, int num, String table1, String table2, String table3, String table4) {
        SearchRequestBuilder builder=this.client.prepareSearch(new String[]{"database"})
                .setTypes(new String[]{table1, table2, table3, table4}).setSearchType(SearchType.DEFAULT).setFrom(0).setSize(num);
        BoolQueryBuilder query=QueryBuilders.boolQuery();
        query.should(QueryBuilders.termQuery(Field, query_content));
        builder.setQuery(query);
        SearchResponse response=(SearchResponse) builder.execute().actionGet();
        return response;
    }

    //mustnot 即 not
    public SearchResponse must_notFieldQuery(String query_content, String Field, int num, String table1, String table2, String table3, String table4) {
        SearchRequestBuilder builder=this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4})
                .setSearchType(SearchType.DEFAULT).setFrom(0).setSize(num);
        BoolQueryBuilder query=QueryBuilders.boolQuery();
        query.mustNot(QueryBuilders.termQuery(Field, query_content));
        builder.setQuery(query);
        SearchResponse response=(SearchResponse) builder.execute().actionGet();
        return response;
    }

    //正则表达式查询
    public SearchResponse regularFieldQuery(String query_content, String Field, int num, String table1, String table2, String table3, String table4) {
        SearchResponse rs=this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4})
                .setQuery(QueryBuilders.regexpQuery(Field, query_content)).setFrom(0).setSize(num).setExplain(false).get();
        return rs;
    }

    //拼音搜索
    public SearchResponse pinyinQuery(String query_content, int num, String table1, String table2, String table3, String table4) {
        SearchRequestBuilder builder=this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table1, table2, table3, table4})
                .setSearchType(SearchType.DEFAULT).setFrom(0).setSize(num);
//        BoolQueryBuilder qb=QueryBuilders.boolQuery().must((new QueryStringQueryBuilder(query_content)).field("*.pinyin"));
        MatchQueryBuilder qb = QueryBuilders.matchQuery("title", query_content).analyzer("pinyin");
        builder.setQuery(qb);
        SearchResponse response=(SearchResponse) builder.execute().actionGet();
        return response;
    }

    public BoolQueryBuilder boolquery(BoolQueryBuilder boolqb, String query_content, String Field, String operator) {
        if (operator.equals("not"))
            boolqb=boolqb.mustNot((new QueryStringQueryBuilder(query_content)).field(Field));
        else if (operator.equals("or"))
            boolqb=boolqb.should((new QueryStringQueryBuilder(query_content)).field(Field));
        else
            boolqb=boolqb.must((new QueryStringQueryBuilder(query_content)).field(Field));
        return boolqb;
    }

    //高级检索
    public SearchResponse advanceSerach(String json) {
        /*String json = "{\"timestart\":\"2014-01-01\",\"timesend\":\"2017-01-01\"," +
                "\"usertype\":[\"weibo\",\"weixin\",\"wenshu\"],\"total\":3," +
                "\"rows\":[{\"queryfield\":\"1\",\"querytype\":\"and\",\"keyword\":\"刑事\"}," +
                "{\"queryfield\":\"1\",\"querytype\":\"or\",\"keyword\":\"裁定书\"}," +
                "{\"queryfield\":\"1\",\"querytype\":\"or\",\"keyword\":\"\"}]}";
        */
        System.out.println(json);
        SearchResponse response=null;

        JSONObject jsonObject=JSONObject.fromObject(json);

        String starttime=jsonObject.getString("timestart");
        String endtime=jsonObject.getString("timesend");
        BoolQueryBuilder boolqb=boolQuery();
        QueryBuilder timeqb;
        if (starttime.equals("") || starttime == null || endtime.equals("") || endtime == null) {
            System.out.println("时间不限定");
        } else {
            timeqb=rangeQuery("pubtime").format("yyyy-MM-dd").gte(starttime).lt(endtime);
            boolqb=boolqb.must(timeqb);
        }

        String[] table={"", "", "", ""};
        if (!jsonObject.has("usertype")) {
            table[0]="weixin";
            table[1]="weibo";
            table[2]="common";
            table[3]="wenshu";
        } else {
            if (jsonObject.optJSONArray("usertype") != null) {       //usertype字段是数组
                JSONArray usertype=jsonObject.getJSONArray("usertype");
                if (usertype.equals("") || usertype == null || usertype.isEmpty()) {
                    table[0]="weixin";
                    table[1]="weibo";
                    table[2]="common";
                    table[3]="wenshu";
                }
                for (int i=0; i < usertype.size(); i++)
                    table[i]=usertype.get(i).toString();
            } else   //usertype只有一个字段
                table[0]=jsonObject.getString("usertype");

        }
        for (int i=0; i < 4; i++)
            System.out.println("table[i]:" + table[i]);
        SearchRequestBuilder builder=this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table[0], table[1], table[2], table[3]})
                .setFrom(0).setSize(100);

        JSONArray jary=jsonObject.getJSONArray("rows");

        int rowslength=jary.size();

        String[] field=new String[rowslength];
        String[] querytype=new String[rowslength];
        String[] keyword=new String[rowslength];
        for (int i=0; i < rowslength; i++) {
            JSONObject obj=jary.getJSONObject(i);
            field[i]=obj.getString("queryfield");
            querytype[i]=obj.getString("querytype");
            keyword[i]=obj.getString("keyword");
        }

        if (keyword[0] == null || keyword[0].equals(""))
            return null;

        for (int i=0; i < rowslength; i++) {

            if (field[i].equals("0"))
                field[i]="content";
            else if (field[i].equals("2"))
                field[i]="tag";
            else
                field[i]="title";

        }

        for (int i=0; i < rowslength; i++) {
            System.out.println(querytype[i] + "," + field[i] + "," + keyword[i]);
            if (keyword[i].equals("") || keyword[i] == null)
                continue;
            boolqb=boolquery(boolqb, keyword[i], field[i], querytype[i]);
            //          System.out.println(boolqb);
        }
        System.out.println(boolqb);
        //  builder.setQuery(timeqb);
        builder.setQuery(boolqb);
        response=(SearchResponse) builder.execute().actionGet();
        return response;
    }

}










/*
    public SearchResponse  advanceSerach(String json){
 *//*       String json = "{\"starttime\":\"2014-04-01\",\"endtime\":\"2015-01-01\",\"rows\":[{\"field\":\"title\",\"operator\":\"or\",\"table\":\"weixin\",\"query\":\"中国\"}, " +
                "{\"field\":\"title\",\"operator\":\"or\",\"table\":\"common\",\"query\":\"裁决书\"}," +
                "{\"field\":\"title\",\"operator\":\"not\",\"table\":\"weixin\",\"query\":\"\"}]}";
 *//*
   //     json = "{\"starttime\":\"2012-12-10\",\"endtime\":\"2017-06-04\",\"table0\":\"wenshu\",\"table1\":\"common\",\"table2\":\"weixin\",\"rows\":[{\"field\":\"title\",\"operator\":\"and\",\"query\":\"裁定书\"},{\"field\":\"title\",\"operator\":\"or\",\"query\":\"中国\"},{\"field\":\"title\",\"operator\":\"and\",\"query\":\"一案\"}]}";
        json = "{\"timestart\":\"2014-04-01\",\"timesend\":\"2015-01-01\"," +
                "\"usertype\":[\"weibo\",\"weixin\",\"wenshu\"],\"total\":3" +
                "\"rows\":[{\"queryfield\":\"1\",\"querytype\":\"and\",\"keyword\":\"中国\"}," +
                "{\"queryfield\":\"1\",\"querytype\":\"or\",\"keyword\":\"裁决书\"}," +
                "{\"queryfield\":\"1\",\"querytype\":\"or\",\"keyword\":\"\"}]}";

        SearchResponse response = null;
       *//* String json = "{\"queryfield1\":\"1\",\"querytype1\":\"and\",\"keyword1\":\"\"," +
                "\"queryfield2\":\"1\",\"querytype2\":\"and\",\"keyword2\":\"\"," +
                "\"queryfield3\":\"1\",\"querytype3\":\"or\",\"keyword3\":\"\"," +
                "\"usertype\":[\"weibo\",\"weixin\",\"wenshu\"],\"timestart\":\"2016-06-06\",\"timesend\":\"2017-01-01\"}\n";
*//*
        System.out.println(json);
        JSONObject jsonObject=JSONObject.fromObject(json);

        String starttime = jsonObject.getString("timestart");
        String endtime = jsonObject.getString("timesend");
        BoolQueryBuilder boolqb = boolQuery();
        QueryBuilder timeqb;
        if(starttime.equals("") || starttime == null || endtime.equals("") || endtime == null){
            System.out.println("时间不限定");
        }else{
            timeqb = rangeQuery("pubtime").format("yyyy-MM-dd").gte(starttime).lt(endtime);
            boolqb = boolqb.must(timeqb);
        }

        String []table = {"", "", "", ""};
        if(!jsonObject.has("usertype")) {
            table[0] = "weixin";
            table[1] = "weibo";
            table[2] = "common";
            table[3] = "wenshu";
        }else{
            if(jsonObject.optJSONArray("usertype") != null){
                JSONArray usertype = jsonObject.getJSONArray("usertype");
                if(usertype.equals("") || usertype == null){
                    table[0] = "weixin";
                    table[1] = "weibo";
                    table[2] = "common";
                    table[3] = "wenshu";
                }
                for (int i = 0; i < usertype.size(); i++)
                    table[i] = usertype.get(i).toString();
            }else
                table[0]= jsonObject.getString("usertype");

        }
        for(int i = 0; i< 4; i ++)
            System.out.println("table[i]:" + table[i]);
        SearchRequestBuilder builder = this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{table[0], table[1], table[2], table[3]})
                .setFrom(0).setSize(100);


        String []field = new String[3];
        String []querytype = new String[3];
        String []keyword = new String[3];
        field[0] = jsonObject.getString("queryfield1");
        querytype[0] = jsonObject.getString("querytype1");
        keyword[0] = jsonObject.getString("keyword1");
        field[1] = jsonObject.getString("queryfield2");
        querytype[1] = jsonObject.getString("querytype2");
        keyword[1] = jsonObject.getString("keyword2");
        field[2] = jsonObject.getString("queryfield3");
        querytype[2] = jsonObject.getString("querytype3");
        keyword[2] = jsonObject.getString("keyword3");

        if((keyword[0] == null || keyword[0].equals("")) &&  (keyword[1] == null || keyword[1].equals("")) && (keyword[2] == null || keyword[2].equals("")))
            return null;

        for(int i = 0 ; i < 3; i++){

            if(field[i].equals("0"))
                field[i] = "content";
            else if(field[i].equals("2"))
                field[i] = "tag";
            else
                field[i] = "title";

        }

        for(int i = 0; i < 3; i++){
            System.out.println(querytype[i] + "," + field[i] + "," + keyword[i]);
            if(keyword[i].equals("") || keyword[i] == null)
                continue;
            boolqb = boolquery(boolqb, keyword[i], field[i], querytype[i]);
  //          System.out.println(boolqb);
        }
        //  builder.setQuery(timeqb);
        builder.setQuery(boolqb);
        response = (SearchResponse)builder.execute().actionGet();
        return response;


    }*/

