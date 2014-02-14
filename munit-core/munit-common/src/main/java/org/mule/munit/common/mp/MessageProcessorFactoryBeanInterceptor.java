/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mp;

import org.mule.api.processor.MessageProcessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;
import org.objenesis.ObjenesisStd;

/**
 * <p/>
 * This is the CGLIB callback that intercepts the getObject of the {@link org.springframework.beans.factory.FactoryBean}
 * interface. Instead of creating the real object we create a Wrapper which is also a CGLIB proxy of the real Object class
 * <p/>
 *
 * @author Mulesoft Inc.
 * @since 3.4.0
 */
public class MessageProcessorFactoryBeanInterceptor extends AbstractMunitMessageProcessorInterceptor
{

    private ObjenesisStd objenesis = new ObjenesisStd();

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable
    {
        Object o = proxy.invokeSuper(obj, args);

        try
        {
            if ( MessageProcessor.class.isAssignableFrom(o.getClass()) && !Enhancer.isEnhanced(o.getClass()))
            {
                MunitMessageProcessorInterceptor callback = new WrapperMunitMessageProcessorInterceptor((MessageProcessor) o);
                callback.setId(id);
                callback.setAttributes(attributes);
                callback.setFileName(fileName);
                callback.setLineNumber(lineNumber);


                Enhancer e = new Enhancer();
                e.setSuperclass(o.getClass());
                e.setInterceptDuringConstruction(true);
                e.setUseCache(false);
                e.setAttemptLoad(true);
                e.setNamingPolicy(new MunitNamingPolicy());
                e.setCallbackTypes(new Class[] {WrapperMunitMessageProcessorInterceptor.class});

                return createProxy(e.createClass(), callback);

            }
            else
            {
                return o;
            }
        }
        catch (Throwable e)
        {
            return o;
        }
    }

    @Override
    protected Object process(Object obj, Object[] args, MethodProxy proxy) throws Throwable
    {
        return null;
    }

    private Object createProxy(Class<?> proxyClass, Callback interceptor) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Object proxy = objenesis.newInstance(proxyClass);

        proxy.getClass().getDeclaredMethod("setCallbacks",
                                           new Callback[0].getClass()).invoke(proxy,
                                                                              new Object[] {
                                                                                      new Callback[] {interceptor}});
        return proxy;
    }

}
