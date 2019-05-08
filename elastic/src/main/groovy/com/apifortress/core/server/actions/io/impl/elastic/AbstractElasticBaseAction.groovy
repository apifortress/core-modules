package com.apifortress.core.server.actions.io.impl.elastic

import com.apifortress.core.core.actions.io.IEventStoreAction
import com.apifortress.core.core.beans.MEvent
import com.apifortress.core.server.context.ConfigContext
import org.apache.http.HttpHost
import org.codehaus.jackson.map.ObjectMapper
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.client.RequestOptions
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct

abstract class AbstractElasticBaseAction {
    @Autowired
    ConfigContext configContext

    protected RestHighLevelClient client

    protected String host;
    protected int port1;
    protected int port2;
    protected String scheme;

    protected abstract String getConfigSection()

    @PostConstruct
    public void init(){
        host = configContext.elastic[configSection]['host']
        port1 = configContext.elastic[configSection]['port1'] as Integer
        port2 = configContext.elastic[configSection]['port2'] as Integer
        scheme = configContext.elastic[configSection]['scheme']

        try {
            client = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port1, scheme), new HttpHost(host, port2, scheme)));
        } catch (Exception e) {
            println e.getMessage()
            println e.printStackTrace()
        }

        println client.toString()
    }
}
