
package org.mule.munit.config;

import javax.annotation.Generated;
import org.mule.munit.config.AbstractDefinitionParser.ParseDelegate;
import org.mule.munit.config.AbstractDefinitionParser.ParseDelegate;
import org.mule.munit.holders.AttributeExpressionHolder;
import org.mule.munit.holders.MunitMuleMessageExpressionHolder;
import org.mule.munit.processors.WhenMessageProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:12-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class WhenDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(WhenMessageProcessor.class.getName());
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        parseProperty(builder, element, "messageProcessor", "messageProcessor");
        parseListAndSetProperty(element, builder, "withAttributes", "with-attributes", "with-attribute", new ParseDelegate<BeanDefinition>() {


            public BeanDefinition parse(Element element) {
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(AttributeExpressionHolder.class);
                parseProperty(builder, element, "name", "name");
                if (hasAttribute(element, "whereValue-ref")) {
                    if (element.getAttribute("whereValue-ref").startsWith("#")) {
                        builder.addPropertyValue("whereValue", element.getAttribute("whereValue-ref"));
                    } else {
                        builder.addPropertyValue("whereValue", (("#[registry:"+ element.getAttribute("whereValue-ref"))+"]"));
                    }
                }
                return builder.getBeanDefinition();
            }

        }
        );
        if (!parseObjectRef(element, builder, "then-return", "thenReturn")) {
            BeanDefinitionBuilder thenReturnBuilder = BeanDefinitionBuilder.rootBeanDefinition(MunitMuleMessageExpressionHolder.class.getName());
            Element thenReturnChildElement = DomUtils.getChildElementByTagName(element, "then-return");
            if (thenReturnChildElement!= null) {
                if (hasAttribute(thenReturnChildElement, "payload-ref")) {
                    if (thenReturnChildElement.getAttribute("payload-ref").startsWith("#")) {
                        thenReturnBuilder.addPropertyValue("payload", thenReturnChildElement.getAttribute("payload-ref"));
                    } else {
                        thenReturnBuilder.addPropertyValue("payload", (("#[registry:"+ thenReturnChildElement.getAttribute("payload-ref"))+"]"));
                    }
                }
                parseMapAndSetProperty(thenReturnChildElement, thenReturnBuilder, "invocationProperties", "invocation-properties", "invocation-property", new ParseDelegate<String>() {


                    public String parse(Element element) {
                        return element.getTextContent();
                    }

                }
                );
                parseMapAndSetProperty(thenReturnChildElement, thenReturnBuilder, "inboundProperties", "inbound-properties", "inbound-property", new ParseDelegate<String>() {


                    public String parse(Element element) {
                        return element.getTextContent();
                    }

                }
                );
                parseMapAndSetProperty(thenReturnChildElement, thenReturnBuilder, "sessionProperties", "session-properties", "session-property", new ParseDelegate<String>() {


                    public String parse(Element element) {
                        return element.getTextContent();
                    }

                }
                );
                parseMapAndSetProperty(thenReturnChildElement, thenReturnBuilder, "outboundProperties", "outbound-properties", "outbound-property", new ParseDelegate<String>() {


                    public String parse(Element element) {
                        return element.getTextContent();
                    }

                }
                );
                builder.addPropertyValue("thenReturn", thenReturnBuilder.getBeanDefinition());
            }
        }
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        attachProcessorDefinition(parserContext, definition);
        return definition;
    }

}
