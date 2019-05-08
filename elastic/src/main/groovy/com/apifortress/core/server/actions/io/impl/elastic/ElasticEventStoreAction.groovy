package com.apifortress.core.server.actions.io.impl.elastic
import com.apifortress.core.core.actions.io.IEventStoreAction
import com.apifortress.core.core.beans.MEvent
import com.apifortress.core.server.context.ConfigContext
import org.apache.http.HttpHost
import org.codehaus.jackson.map.ObjectMapper
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Autowired
/**
 * © 2018 API Fortress
 *
 * @author Simone Pezzano
 **/
class ElasticEventStoreAction extends AbstractElasticBaseAction implements IEventStoreAction {

    @Autowired
    ConfigContext configContext;

    @Override
    void store(MEvent mEvent) throws Exception {
        try {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.putAll(mEvent)
            jsonMap.remove("_id")
            jsonMap.put("time",new Date(mEvent.date))
            IndexRequest indexRequest = new IndexRequest("events").id(mEvent._id).source(jsonMap);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            println e.getMessage()
            println e.printStackTrace()
        }
    }

    @Override
    protected String getConfigSection() {
        'events'
    }
}
