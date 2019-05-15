package com.apifortress.core.server.actions.io.impl.jdbc

import com.apifortress.core.core.actions.io.ITestLoadAction
import com.apifortress.core.core.messages.RunTestMessage
import com.apifortress.core.server.context.ConfigContext
import org.springframework.beans.factory.annotation.Autowired

import java.sql.DatabaseMetaData
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * Loads test unit and input from database
 * */
class JdbcTestLoadAction extends AbstractJdbcBaseAction implements ITestLoadAction {

    @Autowired
    ConfigContext configContext

    /**
     * JDBC Method to load test unit and input from database
     * @param runTestMessage
     * @throws Exception
     */
    @Override
    void load(RunTestMessage runTestMessage) throws Exception {
        final def companyId = runTestMessage.companyId
        final def projectId = runTestMessage.projectId
        final def testId = runTestMessage.test.id
        final PreparedStatement smt = connection.prepareStatement("SELECT * FROM ${configContext.jdbc.tests.table} WHERE id=? AND company_id=? AND project_id=?")
        smt.setObject(1,testId)
        smt.setObject(2,companyId)
        smt.setObject(3,projectId)

        ResultSet rs = smt.executeQuery()
        if(rs.next()){
            runTestMessage.getTest().setName(rs.getString('name'))
            runTestMessage.getTest().setUnit(rs.getString('unit'))
            runTestMessage.getTest().setInput(rs.getString('input'))
        }
        rs.close()
    }

    @Override
    protected String getConfigSection() {
        return 'tests'
    }
}
