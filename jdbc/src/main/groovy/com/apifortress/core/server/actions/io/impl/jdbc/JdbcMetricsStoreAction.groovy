package com.apifortress.core.server.actions.io.impl.jdbc

import com.apifortress.core.core.actions.io.IMetricsStoreAction
import com.apifortress.core.core.beans.MMetrics
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired

import java.sql.PreparedStatement

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * Stores metrics to database
 * */
class JdbcMetricsStoreAction extends AbstractJdbcBaseAction implements IMetricsStoreAction {

    @Autowired
    ObjectMapper objectMapper

    @Override
    protected String getConfigSection() {
        return 'metrics'
    }

    /**
     * JDBC Method to store metrics in the database
     * @param mMetrics
     * @throws Exception
     */
    @Override
    void store(MMetrics mMetrics) throws Exception {
        final def companyId = mMetrics.companyId
        final def projectId = mMetrics.projectId
        final def testId = mMetrics.testId
        final PreparedStatement smt = connection.prepareStatement("INSERT INTO ${configContext.jdbc.metrics.table} (id,company_id,project_id,test_id,date,footprint,success,data) VALUES(?,?,?,?,?,?,?,?)")
        smt.setObject(1,mMetrics._id)
        smt.setObject(2,companyId)
        smt.setObject(3,projectId)
        smt.setObject(4,testId)
        smt.setLong(5,((Date)mMetrics.time).getTime())
        smt.setString(6,mMetrics.footprint)
        smt.setBoolean(7,mMetrics.success)
        smt.setString(8,objectMapper.writeValueAsString(mMetrics))
        smt.execute()
    }
}
