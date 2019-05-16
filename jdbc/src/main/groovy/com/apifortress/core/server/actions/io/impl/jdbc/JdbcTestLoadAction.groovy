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
    List<String> listIds(def companyId, def projectId) {
        final PreparedStatement smt = connection.prepareStatement("SELECT * FROM ${configContext.jdbc.tests.table} WHERE company_id=? AND project_id=?")
        smt.setObject(1,companyId)
        smt.setObject(2,projectId)
        ResultSet rs = smt.executeQuery()
        ArrayList<String> listIds = new ArrayList<>()
        while (rs.next())
        {
            listIds.add(rs.getString('id'))
        }
        rs.close()
        return listIds
    }

    @Override
    protected String getConfigSection() {
        return 'tests'
    }
}
