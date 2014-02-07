/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.mp;

import org.mule.modules.interceptor.processors.MessageProcessorId;
import org.mule.modules.interceptor.spring.BeanFactoryMethodBuilder;
import org.mule.modules.interceptor.spring.MethodInterceptorFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;


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

    protected transient Log logger = LogFactory.getLog(getClass());

    /**
     * <p>
     * For operations that are not {@link org.mule.api.processor.MessageProcessor#process(org.mule.api.MuleEvent)} just do
     * nothing
     * </p>
     */
    private static Callback NULL_METHOD_INTERCEPTOR = new NoOp()
    {
    };

    private static CallbackFilter FACTORY_BEAN_FILTER = new CallbackFilter(){

        @Override
        public int accept(Method method)
        {
            if ("getObject".equals(method.getName()))
            {
                return 0;
            }
            return 1;
        }
    };

    private static CallbackFilter MESSAGE_PROCESSOR_FILTER = new CallbackFilter(){

        @Override
        public int accept(Method method)
        {
            if ("process".equals(method.getName()))
            {
                return 0;
            }
            return 1;
        }
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


    // TODO: Find a cleaner way to make spring find a constructor with dynamic size parameters. Now Munit allows mocking MP with 2 or less constructors

    /**
     * <p>
     * Factory Method to create Message processors with a constructor with one parameter ( {@param constructorArgument} )
     * </p>
     */
    public Object create(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber,
                         Object constructorArgument)
    {
        return create(realMpClass, id, attributes, fileName, lineNumber, new Object[] {constructorArgument});
    }

    /**
     * <p>
     * Factory Method to create Message processors with a constructor with two parameters ( {@param constructorArgument} )
     * </p>
     */
    public Object create(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber,
                         Object constructorArgument1, Object constructorArgument2)
    {
        return create(realMpClass, id, attributes, fileName, lineNumber, new Object[] {constructorArgument1, constructorArgument2});
    }

    /**
     * <p>
     * Factory method used to create Message Processors without constructor parameters.
     * </p>
     *
     * @param realMpClass The class that we want to mock
     * @param id          The {@link MessageProcessorId} that identifies the message processor
     * @param attributes  The Message Processor attributes used to identify the mock
     * @param fileName    The name of the file where the message processor is written down
     * @param lineNumber  The line number where the message processor is written down
     * @return The Mocked object, if it fails mocking then the real object.
     */
    public Object create(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber)
    {
        try
        {
            Enhancer e = createEnhancer(realMpClass, id, attributes, fileName, lineNumber);
            return e.create();
        }
        catch (Throwable e)
        {
            logger.warn("The message processor " + id.getFullName() + " could not be mocked");
            try
            {
                return realMpClass.newInstance();
            }
            catch (Throwable e1)
            {
                throw new Error("The message processor " + id.getFullName() + " could not be created", e);
            }
        }
    }

    /**
     * <p>
     * Factory method used to create Message Processors with constructor parameters.
     * </p>
     *
     * @param realMpClass          The class that we want to mock
     * @param id                   The {@link MessageProcessorId} that identifies the message processor
     * @param attributes           The Message Processor attributes used to identify the mock
     * @param fileName             The name of the file where the message processor is written down
     * @param lineNumber           The line number where the message processor is written down
     * @param constructorArguments The Array of constructor arguments of the message processor
     * @return The Mocked object, if it fails mocking then the real object.
     */
    public Object create(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber,
                         Object[] constructorArguments)
    {
        try
        {
            Enhancer e = createEnhancer(realMpClass, id, attributes, fileName, lineNumber);
            if (constructorArguments != null && constructorArguments.length != 0)
            {
                Class[] classes = findConstructorArgumentTypes(realMpClass, constructorArguments);
                if (classes != null)
                {
                    return e.create(classes, constructorArguments);
                }
                else
                {
                    throw new Error("The message processor " + id.getFullName() + " could not be created, because " +
                                    "there is no matching constructor");
                }
            }
            else
            {
                return e.create();
            }
        }
        catch (Throwable e)
        {
            logger.warn("The message processor " + id.getFullName() + " could not be mocked");
            try
            {
                return realMpClass.newInstance();
            }
            catch (Throwable e1)
            {
                throw new Error("The message processor " + id.getFullName() + " could not be created", e1);
            }
        }
    }

    private Class[] findConstructorArgumentTypes(Class realMpClass, Object[] constructorArguments)
    {
        Constructor[] declaredConstructors = realMpClass.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors)
        {
            Class[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == constructorArguments.length)
            {
                boolean mapsCorrectly = true;
                for (int i = 0; i < parameterTypes.length; i++)
                {
                    mapsCorrectly &= parameterTypes[i].isAssignableFrom(constructorArguments[i].getClass());
                }
                if (mapsCorrectly)
                {
                    return parameterTypes;
                }
            }
        }
        return null;
    }

    protected Enhancer createEnhancer(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber)
    {

        Enhancer e = new Enhancer();
        e.setSuperclass(realMpClass);
        e.setUseCache(false);
        e.setAttemptLoad(true);
        e.setInterceptDuringConstruction(true);
        e.setNamingPolicy(new MunitNamingPolicy());

        if (FactoryBean.class.isAssignableFrom(realMpClass))
        {
            createFactoryBeanCallback(id, attributes, fileName, lineNumber, e);
        }
        else
        {
            createMessageProcessorCallback(id, attributes, fileName, lineNumber, e);
        }
        return e;
    }


    private void createMessageProcessorCallback(MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber, Enhancer e)
    {
        MunitMessageProcessorInterceptor callback = new MunitMessageProcessorInterceptor();
        callback.setId(id);
        callback.setAttributes(attributes);
        callback.setFileName(fileName);
        callback.setLineNumber(lineNumber);
        e.setCallbacks(new Callback[] {callback, NULL_METHOD_INTERCEPTOR});
        e.setCallbackFilter(MESSAGE_PROCESSOR_FILTER);
    }

    private void createFactoryBeanCallback(MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber, Enhancer e)
    {
        MessageProcessorFactoryBeanInterceptor callback = new MessageProcessorFactoryBeanInterceptor();
        callback.setId(id);
        callback.setAttributes(attributes);
        callback.setFileName(fileName);
        callback.setLineNumber(lineNumber);
        e.setCallbacks(new Callback[] {callback, NULL_METHOD_INTERCEPTOR});
        e.setCallbackFilter(FACTORY_BEAN_FILTER);
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
        return new MunitMessageProcessorInterceptor();
    }



}
