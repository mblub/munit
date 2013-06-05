
package org.mule.munit.config;

import javax.annotation.Generated;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/dbserver</code>.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:19-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class DbserverNamespaceHandler
    extends NamespaceHandlerSupport
{


    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        registerBeanDefinitionParser("config", new DBServerModuleConfigDefinitionParser());
        registerBeanDefinitionParser("start-db-server", new StartDbServerDefinitionParser());
        registerBeanDefinitionParser("execute", new ExecuteDefinitionParser());
        registerBeanDefinitionParser("execute-query", new ExecuteQueryDefinitionParser());
        registerBeanDefinitionParser("validate-that", new ValidateThatDefinitionParser());
        registerBeanDefinitionParser("stop-db-server", new StopDbServerDefinitionParser());
    }

}
