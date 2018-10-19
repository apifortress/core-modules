package com.apifortress.core.server.webserver.actions.impl.filesystem

import com.apifortress.core.server.beans.Hook
import com.apifortress.core.server.context.ConfigContext
import com.apifortress.core.server.webserver.actions.IHooksLoadAction
import org.springframework.beans.factory.annotation.Autowired
import org.yaml.snakeyaml.Yaml

/**
 * © 2018 API Fortress
 *
 * @author Simone Pezzano
 **/
public class FilesystemHooksLoadAction implements IHooksLoadAction {

    @Autowired
    ConfigContext configContext

    Yaml yaml = new Yaml()

    @Override
    public Hook getHook(String s) {
        final String path = configContext.filesystem.hooks.path
        def hooks = yaml.load(new File(path).getText())
        return new Hook(hooks.hooks.find { it.identifier == s })
    }
}
