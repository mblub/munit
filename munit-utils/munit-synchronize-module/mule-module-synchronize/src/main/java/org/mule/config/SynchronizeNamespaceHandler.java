package org.mule.config;

import org.mule.config.spring.handlers.AbstractMuleNamespaceHandler;
import org.mule.config.spring.parsers.generic.ChildDefinitionParser;

/**
 *
 */
public class SynchronizeNamespaceHandler extends AbstractMuleNamespaceHandler
{
    @Override
    public void init()
    {
        registerBeanDefinitionParser("run-and-wait", new ChildDefinitionParser("messageProcessor", RunAndWait.class));
    }
}
