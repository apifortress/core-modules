package com.apifortress.core.server.actions.io.impl.filesystem


import com.apifortress.core.server.actions.io.ISchedulesLoadAction
import com.apifortress.core.server.beans.Schedule
import com.apifortress.core.server.beans.Schedules
import com.apifortress.core.server.context.ConfigContext
import groovy.json.JsonSlurper
import org.apache.commons.io.FileUtils
import org.apache.commons.io.input.ReaderInputStream
import org.springframework.beans.factory.annotation.Autowired

import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * Loads test unit and input from database
 * */
class FilesystemSchedulesLoadAction implements ISchedulesLoadAction {

    @Autowired
    ConfigContext configContext

    /**
     * JDBC Method to load test unit and input from database
     * @param runTestMessage
     * @throws Exception
     */
    @Override
    Schedules load() throws Exception {
        final String basepath = configContext.filesystem.schedules.basepath
        final String filename = configContext.filesystem.schedules.filename
        final File directory = new File(basepath)
        Schedules schedules = new Schedules()
        ArrayList companyDirectories = listNotHiddenDirectories(directory)
        for (File companyDirectory : companyDirectories) {
            ArrayList projectDirectories = listNotHiddenDirectories(companyDirectory)
            for (File projectDirectory : projectDirectories) {
                ArrayList testDirectories = listNotHiddenDirectories(projectDirectory)
                for (File testDirectory : testDirectories) {
                    try {
                        final String file = "${testDirectory.getAbsolutePath()}${File.separator}${filename}"
                        File scheduleFile = new File(file)
                        InputStream scheduleStream = new FileInputStream(scheduleFile)
                        Properties properties = new Properties();
                        properties.load(scheduleStream);
                        scheduleStream.close()

                        Schedule schedule = new Schedule()
                        schedule.id = file
                        schedule.name = properties.name
                        schedule.testId = properties.test_id
                        schedule.projectId = properties.project_id
                        schedule.companyId = properties.company_id
                        schedule.lastUpdated = new Date(scheduleFile.lastModified())
                        schedule.paused = Boolean.parseBoolean(properties.paused)
                        schedule.periodString = properties.period_string
                        schedule.timezone = properties.timezone
                        schedule.downloaderId = properties.downloader_id

                        if (properties.overrides)
                            schedule.overrides = new JsonSlurper().parseText(properties.overrides)
                        else
                            schedule.overrides = [:]
                        schedules.add(schedule)
                    } catch (Exception e) {
                        println e.getMessage()
                    }
                }
            }
        }

        return schedules
    }

    private  ArrayList listNotHiddenDirectories(File dir) {
        ArrayList dirs = new ArrayList()
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory() && !file.name.startsWith(".") &&!file.isHidden())
                    dirs.add(file)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dirs
    }
}
