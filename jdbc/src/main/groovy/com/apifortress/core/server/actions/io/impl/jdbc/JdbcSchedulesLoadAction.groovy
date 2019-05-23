package com.apifortress.core.server.actions.io.impl.jdbc

import com.apifortress.core.core.actions.io.ITestLoadAction
import com.apifortress.core.core.messages.RunTestMessage
import com.apifortress.core.server.actions.io.ISchedulesLoadAction
import com.apifortress.core.server.beans.Schedule
import com.apifortress.core.server.beans.Schedules
import com.apifortress.core.server.context.ConfigContext
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired

import java.sql.DatabaseMetaData
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * Loads test unit and input from database
 * */
class JdbcSchedulesLoadAction extends AbstractJdbcBaseAction implements ISchedulesLoadAction {

    @Autowired
    ConfigContext configContext

    /**
     * JDBC Method to load test unit and input from database
     * @param runTestMessage
     * @throws Exception
     */
    @Override
    Schedules load() {
        final PreparedStatement smt = connection.prepareStatement("SELECT * FROM ${configContext.jdbc.schedules.table}")
        ResultSet rs = smt.executeQuery()
        Schedules schedules = new Schedules()
        while(rs.next()){
            Schedule schedule = new Schedule()
            schedule.id = rs.getString('id')
            schedule.name = rs.getString('name')
            schedule.testId = rs.getString('test_id')
            schedule.projectId = rs.getString('project_id')
            schedule.companyId = rs.getString('company_id')
            schedule.lastUpdated = new Date(rs.getTimestamp('last_update').getTime())
            schedule.paused = rs.getBoolean('paused')
            schedule.periodString = rs.getString('period_string')
            schedule.timezone = rs.getString('timezone')
            schedule.downloaderId = rs.getString('downloader_id')

            if(rs.getString('overrides'))
                schedule.overrides = new JsonSlurper().parseText(rs.getString('overrides'))
            else
                schedule.overrides = [:]

            println(JsonOutput.toJson(schedule))
            schedules.add(schedule)
        }
        rs.close()
        return schedules
    }

    @Override
    protected String getConfigSection() {
        return 'schedules'
    }
}
