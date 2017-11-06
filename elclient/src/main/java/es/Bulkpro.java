package es;

import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;

/**
 * Created by 张某某 on 2017/4/12.
 */
public class Bulkpro {
    public static  BulkProcessor Creatbulkprocess(TransportClient client)
    {
        BulkProcessor bulkProcessor = BulkProcessor.builder(
                client,
                new BulkProcessor.Listener() {
                    public void beforeBulk(long executionId,
                                           BulkRequest request) {  }

                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          BulkResponse response) { System.out.println("insert success");}

                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          Throwable failure) { System.out.println("fail"); }
                })
                .setBulkSize(new ByteSizeValue(100, ByteSizeUnit.MB))
                .setConcurrentRequests(0)
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();
        System.out.println("completed");
        return bulkProcessor;
    }
}
