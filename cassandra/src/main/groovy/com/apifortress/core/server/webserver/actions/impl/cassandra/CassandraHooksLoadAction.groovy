package com.apifortress.core.server.webserver.actions.impl.cassandra

import com.apifortress.core.server.actions.io.impl.cassandra.AbstractCassandraBaseAction
import com.apifortress.core.server.beans.Hook
import com.apifortress.core.server.context.ConfigContext
import com.apifortress.core.server.webserver.actions.IHooksLoadAction
import org.springframework.beans.factory.annotation.Autowired
import org.yaml.snakeyaml.Yaml

import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * © 2018 API Fortress
 *
 * @author Simone Pezzano
 **/
public class CassandraHooksLoadAction extends AbstractCassandraBaseAction implements IHooksLoadAction {

    @Autowired
    ConfigContext configContext

    Yaml yaml = new Yaml()

    /*
    @Override
    public Hook getHookFS(String s) {
        final String path = configContext.filesystem.hooks.path
        def hooks = yaml.load(new File(path).getText())
        return new Hook(hooks.hooks.find { it.identifier == s })
    }

     */

    @Override
    public Hook getHook(String s) {
        PreparedStatement smt = connection.prepareStatement("SELECT * FROM ${configContext.jdbc.hooks.table} where identifier=?")
        smt.setObject(1,s)
        ResultSet rs = smt.executeQuery()
        Hook hook = new Hook()
        if(rs.next()) {
            hook.setCompanyId(rs.getString('company_id'))
            hook.setProjectId(rs.getString('project_id'))
            hook.setIdentifier(rs.getString('identifier'))
        }
        rs.close()
        return hook
    }

    @Override
    protected String getConfigSection() {
        return 'hooks'
    }
}
