package com.apifortress.core.server.actions.io.impl.filesystem
import com.apifortress.core.core.actions.io.ITestLoadAction
import com.apifortress.core.core.messages.RunTestMessage
import com.apifortress.core.server.context.ConfigContext
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
/**
 * © 2018 API Fortress
 *
 * @author Simone Pezzano
 **/
public class FilesystemTestLoadAction implements ITestLoadAction {

    @Autowired
    ConfigContext configContext;

    public void load(RunTestMessage runTestMessage) throws Exception {
        final String basepath = configContext.filesystem.tests.basepath
        final String dirPath = "${basepath}${File.separator}${runTestMessage.getCompanyId()}${File.separator}${runTestMessage.getProjectId()}${File.separator}${runTestMessage.getTest().getId()}"
        final File directory = new File(dirPath)
        if(!directory.exists())
            throw new Exception("Test directory not found")
        File unitFile = new File(directory.getAbsolutePath()+File.separator+"unit.xml")
        File inputFile = new File(directory.getAbsolutePath()+File.separator+"input.xml")
        runTestMessage.getTest().setUnit(FileUtils.readFileToString(unitFile, "UTF-8"))
        runTestMessage.getTest().setInput(FileUtils.readFileToString(inputFile, "UTF-8"))
    }
}
