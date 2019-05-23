package com.apifortress.core.server.actions.post.impl.elastic

import com.apifortress.core.core.actions.post.IPostMetricsStoreAction
import com.apifortress.core.core.beans.MMetrics
import com.apifortress.core.server.actions.io.impl.elastic.AbstractElasticBaseAction
import com.apifortress.core.server.context.ConfigContext
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.RequestOptions
import org.springframework.beans.factory.annotation.Autowired

/**
 * © 2018 API Fortress
 *
 * @author Simone Pezzano
 **/
class ElasticPostMetricsStoreAction extends AbstractElasticBaseAction implements IPostMetricsStoreAction {

    @Autowired
    ConfigContext configContext;

    @Override
    void perform(MMetrics mMetrics) {
        try {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.putAll(mMetrics.clone())
            jsonMap.remove(configContext.elastic[configSection]['id'])
            def id = configContext.elastic[configSection]['id']
            def index = configContext.elastic[configSection]['index']
            IndexRequest indexRequest = new IndexRequest(index).id(mMetrics.get(id)).source(jsonMap);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            println e.getMessage()
            println e.printStackTrace()
        }
    }
    
    @Override
    protected String getConfigSection() {
        'metricspost'
    }
}
