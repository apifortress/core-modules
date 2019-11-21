package com.apifortress.core.server.actions.io.impl.cassandra

import com.apifortress.core.core.actions.io.IMetricsStoreAction
import com.apifortress.core.core.beans.MEvent
import com.apifortress.core.core.beans.MMetrics
import com.datastax.driver.core.BoundStatement
import com.datastax.driver.core.UDTValue
import com.datastax.driver.core.UserType

/*import com.datastax.driver.core.querybuilder.Insert
import com.datastax.driver.core.querybuilder.QueryBuilder*/
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired

import java.sql.PreparedStatement

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * Stores metrics to database
 * */
class CassandraMetricsStoreAction extends AbstractCassandraBaseAction implements IMetricsStoreAction {

    @Autowired
    ObjectMapper objectMapper

    /**
     * JDBC Method to store metrics in the database
     * @param mMetrics
     * @throws Exception
     */
    @Override
    void store(MMetrics mMetrics) throws Exception {
        String statement = "insert into " + configContext.cassandra.events.keyspace +"."+configContext.cassandra.metrics.table + " (company_id,id,date,footprint,success,data,test) VALUES (?, ?, ?, ?, ?, ?, ?)"
        com.datastax.driver.core.PreparedStatement prepared = session.prepare(statement)

        UserType udtTest = (UserType) prepared.getVariables().getType("test")
        UDTValue valueTest = udtTest.newValue().setString(0,mMetrics.projectId).setString(1,mMetrics.testId)

        BoundStatement bound = prepared.bind(mMetrics.companyId, mMetrics._id,mMetrics.date,mMetrics.footprint,mMetrics.success,objectMapper.writeValueAsString(mMetrics),valueTest);
        session.execute(bound);
    }

    @Override
    protected String getConfigSection() {
        return 'metrics'
    }


}
