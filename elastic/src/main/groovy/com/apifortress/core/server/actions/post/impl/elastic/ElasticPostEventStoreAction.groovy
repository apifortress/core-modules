package com.apifortress.core.server.actions.post.impl.elastic

import com.apifortress.core.core.actions.post.IPostEventStoreAction
import com.apifortress.core.core.beans.MEvent
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
class ElasticPostEventStoreAction extends AbstractElasticBaseAction implements IPostEventStoreAction {

    @Autowired
    ConfigContext configContext;

    @Override
    void perform(MEvent mEvent) {
        try {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.putAll(mEvent.clone())
            jsonMap.remove(configContext.elastic[configSection]['id'])
            jsonMap.remove('events')
            jsonMap.put("time",new Date(mEvent.date))
            def index = configContext.elastic[configSection]['id']
            IndexRequest indexRequest = new IndexRequest(configSection).id(mEvent.get(index)).source(jsonMap);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            println e.getMessage()
            println e.printStackTrace()
        }
    }

    @Override
    protected String getConfigSection() {
        'eventspost'
    }
}
