package com.apifortress.core.server.actions.io.impl.cassandra

import com.apifortress.core.server.context.ConfigContext
import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session
import org.springframework.beans.factory.annotation.Autowired


import javax.annotation.PostConstruct
//import java.sql.Connection


/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * */
abstract class AbstractCassandraBaseAction {

    @Autowired
    ConfigContext configContext

    //protected Connection connection
    protected Cluster cluster
    protected Session session

    protected abstract String getConfigSection()

    @PostConstruct
    public void init(){
        final String host = configContext.cassandra[configSection]['host']
        final int port = configContext.cassandra[configSection]['port']
        final String user = configContext.cassandra[configSection]['user']
        final String password = configContext.cassandra[configSection]['password']
        final String keyspace = configContext.cassandra[configSection]['keyspace']

        //Creating Cluster object
        cluster = Cluster.builder().addContactPoint("localhost").withPort(port ).withCredentials(user,password).build();
        //Creating Session object
        session = cluster.connect(keyspace);
        //session = CqlSession.builder().build()
        //session = CqlSession.builder().addContactPoint(new InetSocketAddress(host, port)).withAuthCredentials(user,password).withLocalDatacenter("datacenter1").build();
    }

}
