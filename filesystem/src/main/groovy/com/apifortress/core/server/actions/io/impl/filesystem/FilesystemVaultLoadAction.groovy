package com.apifortress.core.server.actions.io.impl.filesystem

import com.apifortress.core.core.actions.io.IVaultLoadAction
import com.apifortress.core.core.beans.MVault
import com.apifortress.core.core.messages.RunTestMessage
import com.apifortress.core.server.context.ConfigContext
import org.springframework.beans.factory.annotation.Autowired
import org.yaml.snakeyaml.Yaml

/**
 * © 2018 API Fortress
 * @author Simone Pezzano
 * */
class FilesystemVaultLoadAction implements IVaultLoadAction {

    @Autowired
    ConfigContext configContext;

    static Yaml yaml = new Yaml()


    @Override
    void loadVault(RunTestMessage runTestMessage) throws Exception {
        runTestMessage.vault = loadVault(runTestMessage.getCompanyId(),runTestMessage.getProjectId())
    }

    @Override
    MVault loadVault(Object companyId, Object projectId) {
        final String basepath = configContext.filesystem.vault.basepath
        final String dirPath = "${basepath}${File.separator}${companyId}${File.separator}${projectId}${File.separator}"
        final File directory = new File(dirPath)
        if(!directory.exists())
            return new MVault()
        final File vaultFile = new File(directory.getAbsolutePath()+File.separator+'vault.yml')
        if(!vaultFile.exists())
            return new MVault()
        return MVault.create(yaml.load(vaultFile.getText('utf-8')))
    }
}
