/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mp;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import org.mule.modules.interceptor.processors.MessageProcessorId;
import org.mule.modules.interceptor.spring.BeanFactoryMethodBuilder;
import org.mule.modules.interceptor.spring.MethodInterceptorFactory;

import net.sf.cglib.proxy.NoOp;

import org.springframework.beans.factory.support.AbstractBeanDefinition;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * This is the Message processor interceptor factory.
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitMessageProcessorInterceptorFactory extends MethodInterceptorFactory
{


    /**
     * <p>
     * For operations that are not {@link org.mule.api.processor.MessageProcessor#process(org.mule.api.MuleEvent)} just do
     * nothing
     * </p>
     */
    private static Callback NULL_METHOD_INTERCEPTOR = new NoOp()
    {
    };

    /**
     * <p>
     * The Id in the spring registry of Mule
     * </p>
     */
    public static final String ID = "__messageProcessorEnhancerFactory";

    /**
     * <p>
     * Util method that creates a @see #BeanFactoryMethodBuilder based on an abstract bean definition
     * </p>
     * <p/>
     * <p>The usage:</p>
     * <p/>
     * <code>
     * addFactoryDefinitionTo(beanDefinition).withConstructorArguments(beanDefinition.getBeanClass());
     * </code>
     *
     * @param beanDefinition <p>
     *                       The bean definition that we want to modify
     *                       </p>
     * @return
     */
    public static BeanFactoryMethodBuilder addFactoryDefinitionTo(AbstractBeanDefinition beanDefinition)
    {
        return new BeanFactoryMethodBuilder(beanDefinition, "create", ID);
    }

    public Object create(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber)
    {
        try
        {
            Enhancer e = createEnhancer(realMpClass, id, attributes, fileName, lineNumber, "process");
            return e.create();

        }
        catch (Throwable e)
        {
            throw new Error("The message processor " + id.getFullName() + " could not be mocked", e);
        }
    }

    public Object create(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber, Object ... attrs)
    {
        try
        {
            // TODO: Fix on cascade, should be process not doProcess
            Enhancer e = createEnhancer(realMpClass, id, attributes, fileName, lineNumber, "doProcess");
            List<Class> classes = new ArrayList<Class>();
            for ( Object attr : attrs ){
                classes.add(attr.getClass());
            }
            return e.create(classes.toArray(new Class[]{}), attrs);

        }
        catch (Throwable e)
        {
            throw new Error("The message processor " + id.getFullName() + " could not be mocked", e);
        }
    }

    private Enhancer createEnhancer(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber, String methodName)
    {

        Enhancer e = new Enhancer();
        e.setSuperclass(realMpClass);
        e.setUseFactory(true);
        e.setInterceptDuringConstruction(true);

        MunitMessageProcessorInterceptor callback = new MunitMessageProcessorInterceptor(methodName);
        callback.setId(id);
        callback.setAttributes(attributes);
        callback.setFileName(fileName);
        callback.setLineNumber(lineNumber);
        e.setCallbacks(new Callback[] {callback, NULL_METHOD_INTERCEPTOR});
        e.setCallbackFilter(new MessageProcessorCallbackFilter(methodName));
        return e;
    }

    /**
     * <p>
     * Actual implementation of the interceptor creation
     * </p>
     *
     * @return <p>
     *         A {@link MunitMessageProcessorInterceptor} object
     *         </p>
     */
    @Override
    protected MethodInterceptor createInterceptor()
    {
        return new MunitMessageProcessorInterceptor("process");
    }


    private static class MessageProcessorCallbackFilter implements CallbackFilter{
        private String methodName;

        private MessageProcessorCallbackFilter(String methodName)
        {
            this.methodName = methodName;
        }


        @Override
        public int accept(Method method)
        {
            if (methodName.equals(method.getName()))
            {
                return 0;
            }
            return 1;
        }
    }
}
