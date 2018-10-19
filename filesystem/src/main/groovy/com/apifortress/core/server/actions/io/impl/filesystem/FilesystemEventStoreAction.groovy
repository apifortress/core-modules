package com.apifortress.core.server.actions.io.impl.filesystem
import com.apifortress.core.core.actions.io.IEventStoreAction
import com.apifortress.core.core.beans.MEvent
import com.apifortress.core.server.context.ConfigContext
import org.codehaus.jackson.map.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
/**
 * © 2018 API Fortress
 *
 * @author Simone Pezzano
 **/
public class FilesystemEventStoreAction implements IEventStoreAction {

    @Autowired
    ConfigContext configContext;

    @Autowired
    ObjectMapper objectMapper

    public void store(MEvent mEvent) throws Exception {
        final String basepath = configContext.filesystem.events.basepath
        final String dirPath = "${basepath}${File.separator}${mEvent.companyId}${File.separator}${mEvent.projectId}"
        final File directory = new File(dirPath);
        directory.mkdirs()
        final File outputFile = new File(directory.absolutePath+File.separator+mEvent.getId())
        outputFile.write(objectMapper.writeValueAsString(mEvent))
    }
}
