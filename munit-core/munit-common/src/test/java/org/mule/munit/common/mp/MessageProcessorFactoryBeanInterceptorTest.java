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
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mule.api.processor.LoggerMessageProcessor;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.4.0
 */
public class MessageProcessorFactoryBeanInterceptorTest
{

    private MethodProxy proxied = mock(MethodProxy.class);

    @Test
    public void whenProxiedDoesNotReturnMessageProcessorThenReturnOriginal() throws Throwable
    {
        Object obj = new Object();
        Object[] args = new Object[0];
        Object realObject = new Object();
        when(proxied.invokeSuper(obj, args)).thenReturn(realObject);

        MessageProcessorFactoryBeanInterceptor interceptor = new MessageProcessorFactoryBeanInterceptor();
        assertEquals(realObject, interceptor.intercept(obj, Object.class.getDeclaredMethod("hashCode"), args, proxied));
    }

    @Test
    public void whenOriginalIsEnhancedThenReturnOriginal() throws Throwable
    {
        Object obj = new LoggerMessageProcessor();
        Object[] args = new Object[0];
        Object realObject = new LoggerMessageProcessor();
        when(proxied.invokeSuper(obj, args)).thenReturn(realObject);

        MessageProcessorFactoryBeanInterceptor interceptor = new MessageProcessorFactoryBeanInterceptor();
        Object enhancedOriginal = interceptor.intercept(obj, Object.class.getDeclaredMethod("hashCode"), args, proxied);

        assertTrue(Enhancer.isEnhanced(enhancedOriginal.getClass()));

        when(proxied.invokeSuper(obj, args)).thenReturn(enhancedOriginal);

        assertEquals(enhancedOriginal.toString(), interceptor.intercept(obj, Object.class.getDeclaredMethod("hashCode"), args, proxied).toString());
    }

    @Test
    public void processReturnsNull() throws Throwable
    {
        assertNull(new MessageProcessorFactoryBeanInterceptor().process(new Object(), null, null));
    }
}
