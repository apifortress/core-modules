package com.apifortress.core.server.actions.io.impl.jdbc

import com.apifortress.core.core.actions.io.IVaultLoadAction
import com.apifortress.core.core.beans.MVault
import com.apifortress.core.core.messages.RunTestMessage
import org.yaml.snakeyaml.Yaml

import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * Load Action from database
 * */
class JdbcVaultLoadAction extends AbstractJdbcBaseAction implements IVaultLoadAction {

    private static Yaml yaml = new Yaml()

    @Override
    protected String getConfigSection() {
        return 'vault'
    }

    /**
     * JDBC Method to load vault from database
     * @param runTestMessage
     * @throws Exception
     */
    @Override
    void loadVault(RunTestMessage runTestMessage) throws Exception {
        runTestMessage.vault = loadVault(runTestMessage.companyId,runTestMessage.projectId)
    }

    @Override
    MVault loadVault(Object companyId, Object projectId) {
        PreparedStatement smt = connection.prepareStatement("SELECT * FROM ${configContext.jdbc.vault.table} where company_id=? AND project_id=?")
        smt.setObject(1,companyId)
        smt.setObject(2,projectId)

        ResultSet rs = smt.executeQuery()
        MVault vault = new MVault()
        if(rs.next()){
            vault = MVault.create(yaml.load(rs.getString('data')))
        }
        rs.close()

        println vault
        return vault
    }

}
