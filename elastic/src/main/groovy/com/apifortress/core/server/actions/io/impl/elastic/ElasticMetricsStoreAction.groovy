package com.apifortress.core.server.actions.io.impl.elastic
import com.apifortress.core.core.actions.io.IMetricsStoreAction
import com.apifortress.core.core.beans.MMetrics
import com.apifortress.core.server.context.ConfigContext
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.HttpHost
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.beans.factory.annotation.Autowired

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * */
class ElasticMetricsStoreAction extends AbstractElasticBaseAction implements IMetricsStoreAction {

    @Autowired
    ConfigContext configContext

    @Override
    void store(MMetrics mMetrics) throws Exception {
        try {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.putAll(mMetrics)
            jsonMap.remove(configContext.elastic[configSection]['id'])
            def index = configContext.elastic[configSection]['id']
            IndexRequest indexRequest = new IndexRequest(configSection).id(mMetrics.get(index)).source(jsonMap);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            println e.getMessage()
            println e.printStackTrace()
        }
    }

    @Override
    protected String getConfigSection() {
        'metrics'
    }
}
