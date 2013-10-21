/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import org.mule.api.processor.LoggerMessageProcessor;
import org.mule.component.simple.EchoComponent;
import org.mule.modules.interceptor.processors.MessageProcessorId;

import java.util.HashMap;

import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitMessageProcessorInterceptorFactoryTest
{

    @Test
    public void testIdIsCorrect()
    {
        assertEquals("__messageProcessorEnhancerFactory", MunitMessageProcessorInterceptorFactory.ID);
    }

    @Test
    public void testFactoryBeanNameAndMethod()
    {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        MunitMessageProcessorInterceptorFactory.addFactoryDefinitionTo(beanDefinition);

        assertEquals("create", beanDefinition.getFactoryMethodName());
        assertEquals(MunitMessageProcessorInterceptorFactory.ID, beanDefinition.getFactoryBeanName());
    }

    @Test
    public void createCorrectInterceptor()
    {
        MethodInterceptor interceptor = new MunitMessageProcessorInterceptorFactory().createInterceptor();
        assertTrue(interceptor instanceof MunitMessageProcessorInterceptor);
    }


    @Test
    public void testCreate()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        factory.create(EchoComponent.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2");
    }

    @Test
    public void testCreateLogger()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        factory.create(LoggerMessageProcessor.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2");
    }
}
