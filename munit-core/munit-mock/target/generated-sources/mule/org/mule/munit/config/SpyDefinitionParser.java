
package org.mule.munit.config;

import javax.annotation.Generated;
import org.mule.config.spring.factories.MessageProcessorChainFactoryBean;
import org.mule.munit.processors.SpyMessageProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:12-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class SpyDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(SpyMessageProcessor.class.getName());
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        parseProperty(builder, element, "messageProcessor", "messageProcessor");
        parseNestedProcessorAsListAndSetProperty(element, "assertions-before-call", parserContext, MessageProcessorChainFactoryBean.class, builder, "assertionsBeforeCall");
        parseNestedProcessorAsListAndSetProperty(element, "assertions-after-call", parserContext, MessageProcessorChainFactoryBean.class, builder, "assertionsAfterCall");
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        attachProcessorDefinition(parserContext, definition);
        return definition;
    }

}
