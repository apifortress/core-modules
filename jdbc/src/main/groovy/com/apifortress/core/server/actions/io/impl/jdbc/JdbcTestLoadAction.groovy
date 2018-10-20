package com.apifortress.core.server.actions.io.impl.jdbc

import com.apifortress.core.core.actions.io.ITestLoadAction
import com.apifortress.core.core.messages.RunTestMessage
import com.apifortress.core.server.context.ConfigContext
import org.springframework.beans.factory.annotation.Autowired

import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * */
class JdbcTestLoadAction extends AbstractJdbcBaseAction implements ITestLoadAction {

    @Autowired
    ConfigContext configContext

    @Override
    void load(RunTestMessage runTestMessage) throws Exception {
        final def companyId = runTestMessage.companyId
        final def projectId = runTestMessage.projectId
        final def testId = runTestMessage.test.id
        final PreparedStatement smt = connection.prepareStatement("SELECT * FROM ${configContext.jdbc.tests.table} WHERE id=? AND companyId=? AND projectId=?")
        smt.setObject(1,testId)
        smt.setObject(2,companyId)
        smt.setObject(3,projectId)

        ResultSet rs = smt.executeQuery()
        if(rs.next()){
            runTestMessage.test.unit = rs.getString('unit')
            runTestMessage.test.input = rs.getString('input')
        }
        rs.close()

    }

    @Override
    protected String getConfigSection() {
        return 'tests'
    }
}
