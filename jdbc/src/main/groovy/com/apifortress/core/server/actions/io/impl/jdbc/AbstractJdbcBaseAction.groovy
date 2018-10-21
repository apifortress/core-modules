package com.apifortress.core.server.actions.io.impl.jdbc

import com.apifortress.core.server.context.ConfigContext
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct
import java.sql.Connection
import java.sql.DriverManager

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * */
abstract class AbstractJdbcBaseAction {

    @Autowired
    ConfigContext configContext

    protected Connection connection

    protected abstract String getConfigSection()

    @PostConstruct
    public void init(){
        final String driver = configContext.jdbc[configSection]['driver']
        final String url = configContext.jdbc[configSection]['url']

        Class.forName(driver)
        Properties properties = new Properties()
        properties.putAll(configContext.jdbc[configSection]['properties'])
        connection = DriverManager.getConnection(url,properties)
    }
}
