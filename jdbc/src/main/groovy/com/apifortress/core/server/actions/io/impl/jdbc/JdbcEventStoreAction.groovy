package com.apifortress.core.server.actions.io.impl.jdbc
import com.apifortress.core.core.actions.io.IEventStoreAction
import com.apifortress.core.core.beans.MEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired

import java.sql.PreparedStatement
/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * */
class JdbcEventStoreAction extends AbstractJdbcBaseAction implements IEventStoreAction {

    @Autowired
    ObjectMapper objectMapper

    @Override
    void store(MEvent mEvent) throws Exception {
        final def companyId = mEvent.companyId
        final def projectId = mEvent.projectId
        final def testId = mEvent.test.id
        final PreparedStatement smt = connection.prepareStatement("INSERT INTO ${configContext.jdbc.events.table} (id,company_id,project_id,test_id,date,data) VALUES(?,?,?,?,?,?)")
        smt.setString(1,mEvent._id)
        smt.setObject(2,companyId)
        smt.setObject(3,projectId)
        smt.setObject(4,testId)
        smt.setLong(5,mEvent.date)
        smt.setString(6,objectMapper.writeValueAsString(mEvent))
        smt.execute()
    }

    @Override
    protected String getConfigSection() {
        'events'
    }
}
