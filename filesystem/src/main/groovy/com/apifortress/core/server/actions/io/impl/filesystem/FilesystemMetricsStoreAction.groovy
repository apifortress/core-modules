package com.apifortress.core.server.actions.io.impl.filesystem

import com.apifortress.core.core.actions.io.IMetricsStoreAction
import com.apifortress.core.core.beans.MMetrics
import com.apifortress.core.server.context.ConfigContext
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * */
class FilesystemMetricsStoreAction implements IMetricsStoreAction {

    @Autowired
    ConfigContext configContext

    @Autowired
    ObjectMapper objectMapper

    @Override
    void store(MMetrics mMetrics) throws Exception {
        final String basepath = configContext.filesystem.metrics.basepath
        final String dirPath = "${basepath}${File.separator}${mMetrics.companyId}${File.separator}${mMetrics.projectId}"
        final File directory = new File(dirPath);
        directory.mkdirs()
        final File outputFile = new File(directory.absolutePath+File.separator+mMetrics.getId())
        outputFile.write(objectMapper.writeValueAsString(mMetrics))
    }
}
