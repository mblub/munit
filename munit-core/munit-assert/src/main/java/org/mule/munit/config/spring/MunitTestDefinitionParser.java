/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.config.spring;

import org.mule.api.config.MuleProperties;
import org.mule.config.spring.parsers.generic.OrphanDefinitionParser;
import org.mule.config.spring.util.ProcessingStrategyUtils;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * <p>
 * Munit Test Definition Parser
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitTestDefinitionParser extends OrphanDefinitionParser
{

    public MunitTestDefinitionParser(Class munitClass)
    {
        super(munitClass, true);
        addIgnored("abstract");
        addIgnored("name");
        addIgnored("processingStrategy");
    }

    @java.lang.Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder)
    {
        builder.addConstructorArgValue(element.getAttribute(ATTRIBUTE_NAME));
        builder.addConstructorArgReference(MuleProperties.OBJECT_MULE_CONTEXT);
        builder.addPropertyValue("expectExceptionThatSatisfies", element.getAttribute("expectExceptionThatSatisfies"));
        builder.addPropertyValue("ignore", Boolean.getBoolean(element.getAttribute("ignore")));
        ProcessingStrategyUtils.configureProcessingStrategy(element, builder,
                                                            ProcessingStrategyUtils.QUEUED_ASYNC_PROCESSING_STRATEGY);
        super.doParse(element, parserContext, builder);
    }
}
