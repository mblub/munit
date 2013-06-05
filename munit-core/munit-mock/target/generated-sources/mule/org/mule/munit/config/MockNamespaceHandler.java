
package org.mule.munit.config;

import javax.annotation.Generated;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/mock</code>.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:12-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class MockNamespaceHandler
    extends NamespaceHandlerSupport
{


    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        registerBeanDefinitionParser("config", new MockModuleConfigDefinitionParser());
        registerBeanDefinitionParser("when", new WhenDefinitionParser());
        registerBeanDefinitionParser("spy", new SpyDefinitionParser());
        registerBeanDefinitionParser("throw-an", new ThrowAnDefinitionParser());
        registerBeanDefinitionParser("verify-call", new VerifyCallDefinitionParser());
        registerBeanDefinitionParser("outbound-endpoint", new OutboundEndpointDefinitionParser());
    }

}
