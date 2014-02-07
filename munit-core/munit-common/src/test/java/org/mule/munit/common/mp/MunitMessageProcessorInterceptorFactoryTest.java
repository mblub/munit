/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.mp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import org.mule.api.MuleContext;
import org.mule.api.processor.LoggerMessageProcessor;
import org.mule.component.simple.EchoComponent;
import org.mule.config.spring.factories.FlowRefFactoryBean;
import org.mule.construct.Flow;
import org.mule.modules.interceptor.processors.MessageProcessorId;

import java.util.HashMap;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitMessageProcessorInterceptorFactoryTest
{

    private MuleContext context = mock(MuleContext.class);

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

    @Test
    public void testCreateWithTwoConstructorArguments()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        Object o = factory.create(Flow.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2", "flowName", context);

        assertTrue(Enhancer.isEnhanced(o.getClass()));
    }

    @Test(expected = java.lang.Error.class)
    public void testCreateWithOneConstructorArgumentsButFail()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        factory.create(Flow.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2", "flowName");
    }

    @Test
    public void testCreateWithOneConstructorArgumentsButFailAndThenCreateReal()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        Object o = factory.create(EchoComponent.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2", "Error");

        assertFalse(Enhancer.isEnhanced(o.getClass()));
    }

    @Test
    public void failAndCreateTheRealOne()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        Object o = factory.create(EchoComponent.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2", "Error");

        assertFalse(Enhancer.isEnhanced(o.getClass()));
    }

    @Test
    public void createAFactoryBean()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        Object o = factory.create(FlowRefFactoryBean.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2");

        assertTrue(Enhancer.isEnhanced(o.getClass()));
    }

    @Test(expected = java.lang.Error.class)
    public void failCreatingMockWithoutConstructor()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        factory.create(Flow.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2");
    }


    @Test
    public void createWithEmptyConstructors()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        Object o = factory.create(EchoComponent.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2", new Object[]{});

        assertTrue(Enhancer.isEnhanced(o.getClass()));

    }

}
