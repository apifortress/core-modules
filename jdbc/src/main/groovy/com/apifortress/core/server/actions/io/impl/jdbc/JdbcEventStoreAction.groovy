package com.apifortress.core.server.actions.io.impl.jdbc
import com.apifortress.core.core.actions.io.IEventStoreAction
import com.apifortress.core.core.beans.MEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired

import java.sql.PreparedStatement
/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * Stores events to database
 * */
class JdbcEventStoreAction extends AbstractJdbcBaseAction implements IEventStoreAction {

    @Autowired
    ObjectMapper objectMapper

    /**
     * JDBC Method to store events in the database
     * @param mEvent
     * @throws Exception
     */
    @Override
    void store(MEvent mEvent) throws Exception {
        final PreparedStatement smt = connection.prepareStatement("INSERT INTO ${configContext.jdbc.events.table} (id,company_id,project_id,test_id,failures_count,date,data) VALUES(?,?,?,?,?,?,?)")
        smt.setString(1,mEvent._id)
        smt.setObject(2,mEvent.companyId)
        smt.setObject(3,mEvent.projectId)
        smt.setObject(4,mEvent.test.id)
        smt.setObject(5,mEvent.failuresCount)
        smt.setLong(6,mEvent.date)
        smt.setString(7,objectMapper.writeValueAsString(mEvent))
        smt.execute()
    }

    @Override
    protected String getConfigSection() {
        'events'
    }
}
