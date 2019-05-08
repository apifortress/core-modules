package com.apifortress.core.server.actions.io.impl.filesystem

import com.apifortress.core.core.actions.io.IDownloaderLoadAction
import com.apifortress.core.core.beans.MDownloader
import com.apifortress.core.core.messages.RunTestMessage
import com.apifortress.core.server.context.ConfigContext
import org.springframework.beans.factory.annotation.Autowired
import org.yaml.snakeyaml.Yaml

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * */
class FilesystemDownloaderLoadAction implements IDownloaderLoadAction {

    @Autowired
    ConfigContext configContext;

    static Yaml yaml = new Yaml()

    @Override
    void load(RunTestMessage runTestMessage) throws Exception {
        final String basepath = configContext.filesystem.downloaders.basepath
        final String dirPath = basepath+File.separator
        final File directory = new File(dirPath)
        runTestMessage.downloader = MDownloader.create(yaml.load(new File(directory.absolutePath+File.separator+runTestMessage.downloader.id+'.yml').getText()))
    }
}
