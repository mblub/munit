/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
