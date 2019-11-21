package com.apifortress.core.server.actions.io.impl.cassandra
import com.apifortress.core.core.actions.io.IEventStoreAction
import com.apifortress.core.core.beans.MEvent
import com.datastax.driver.core.PreparedStatement
import com.datastax.driver.core.BoundStatement
import com.datastax.driver.core.UDTValue
import com.datastax.driver.core.UserType
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import groovy.json.JsonOutput

//import java.sql.PreparedStatement
/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * Stores events to database
 * */
class CassandraEventStoreAction extends AbstractCassandraBaseAction implements IEventStoreAction {

    @Autowired
    ObjectMapper objectMapper

    /**
     * JDBC Method to store events in the database
     * @param mEvent
     * @throws Exception
     */
    @Override
    void store(MEvent mEvent) throws Exception {
        String statement = "insert into " + configContext.cassandra.events.keyspace +"."+configContext.cassandra.events.table + " (company_id,id,date,data,test,counters) VALUES (?, ?, ?, ?, ?, ?)"
        PreparedStatement prepared = session.prepare(statement)

        UserType udtTest = (UserType) prepared.getVariables().getType("test")
        UDTValue valueTest = udtTest.newValue().setString(0,mEvent.projectId).setString(1,mEvent.test.id)

        UserType udtCounters = (UserType) prepared.getVariables().getType("counters")
        UDTValue valueCounters = udtCounters.newValue().setInt(0,mEvent.failuresCount).setInt(1,mEvent.warningsCount)

        BoundStatement bound = prepared.bind(mEvent.companyId, mEvent._id,mEvent.date,objectMapper.writeValueAsString(mEvent),valueTest,valueCounters);
        session.execute(bound);
    }

    @Override
    protected String getConfigSection() {
        'events'
    }

}
