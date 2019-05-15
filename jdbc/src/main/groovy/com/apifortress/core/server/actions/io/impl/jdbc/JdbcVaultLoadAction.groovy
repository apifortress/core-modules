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

    MVault loadVaultComplex(Object companyId, Object projectId) {
        PreparedStatement snippetSmt = connection.prepareStatement("SELECT * FROM ${configContext.jdbc.vaultComplex.table} where company_id=? AND project_id=? AND vault_type =?")
        snippetSmt.setObject(1,companyId)
        snippetSmt.setObject(2,projectId)
        snippetSmt.setObject(3,'snippets')
        ResultSet rsSnippets = snippetSmt.executeQuery()

        PreparedStatement variablesSmt = connection.prepareStatement("SELECT * FROM ${configContext.jdbc.vaultComplex.table} where company_id=? AND project_id=? AND vault_type =?")
        variablesSmt.setObject(1,companyId)
        variablesSmt.setObject(2,projectId)
        variablesSmt.setObject(3,'variables')
        ResultSet rsVariables = variablesSmt.executeQuery()

        MVault vault = new MVault()
        Map snippets = new HashMap()
        while (rsSnippets.next()){
            def snippetName = rsSnippets.getString('name')
            def snippet = [code:rsSnippets.getString('code'),name:rsSnippets.getString('name')]
            snippets.put(snippetName,snippet)
        }
        vault.put('snippets',snippets)

        Map variables = new HashMap()
        while (rsVariables.next()){
            def variableName = rsVariables.getString('name')
            def variable = [value:rsVariables.getString('value'),name:rsVariables.getString('name')]
            variables.put(variableName,variable)
        }
        vault.put('variables',variables)

        rsSnippets.close()
        rsVariables.close()

        println vault
        return vault
    }
}
