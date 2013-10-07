package org.mule.munit.common.mp;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import org.mule.modules.interceptor.processors.MessageProcessorId;
import org.mule.modules.interceptor.spring.BeanFactoryMethodBuilder;
import org.mule.modules.interceptor.spring.MethodInterceptorFactory;

import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

import java.lang.reflect.Method;
import java.util.Map;


/**
 * <p>
 * This is the Message processor interceptor factory.
 * </p>
 *
 * @author Federico, Fernando
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
     * Allow callbacks only for  {@link org.mule.api.processor.MessageProcessor#process(org.mule.api.MuleEvent)}
     * </p>
     */
    private static CallbackFilter CALLBACK_FILTER = new CallbackFilter()
    {
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

    public Object create(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber)
    {
        try
        {
            Enhancer e = createEnhancer(realMpClass, id, attributes, fileName, lineNumber);
            return e.create();

        }
        catch (Throwable e)
        {
            throw new Error("The message processor " + id.getFullName() + " could not be mocked", e);
        }
    }

    public Object create(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber, String mpName)
    {
        try
        {
            Enhancer e = createEnhancer(realMpClass, id, attributes, fileName, lineNumber);
            return e.create(new Class[]{String.class}, new Object[]{mpName});

        }
        catch (Throwable e)
        {
            throw new Error("The message processor " + id.getFullName() + " could not be mocked", e);
        }
    }

    private Enhancer createEnhancer(Class realMpClass, MessageProcessorId id, Map<String, String> attributes, String fileName, String lineNumber)
    {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Enhancer.class);

        Enhancer e = new Enhancer();
        e.setSuperclass(realMpClass);
        e.setUseFactory(true);
        e.setInterceptDuringConstruction(true);
        MunitMessageProcessorInterceptor callback = new MunitMessageProcessorInterceptor();
        callback.setId(id);
        callback.setAttributes(attributes);
        callback.setFileName(fileName);
        callback.setLineNumber(lineNumber);
        e.setCallbacks(new Callback[] {callback, NULL_METHOD_INTERCEPTOR});
        e.setCallbackFilter(CALLBACK_FILTER);
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
        return new MunitMessageProcessorInterceptor();
    }

}
