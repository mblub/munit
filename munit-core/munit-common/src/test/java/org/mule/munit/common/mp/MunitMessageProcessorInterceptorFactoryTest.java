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
    public void testCreateDoProcess()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        factory.create(MyTestMessageProcessor.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2", new Object());
    }

   @Test(expected = Error.class)
    public void testCreateFailureOnDoProcess()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        factory.create(LoggerMessageProcessor.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2", new Object());
    }


    @Test
    public void testCreateLogger()
    {
        MunitMessageProcessorInterceptorFactory factory = new MunitMessageProcessorInterceptorFactory();

        factory.create(LoggerMessageProcessor.class, new MessageProcessorId("name", "namespace"), new HashMap<String, String>(), "fileName", "2");

    }
}
