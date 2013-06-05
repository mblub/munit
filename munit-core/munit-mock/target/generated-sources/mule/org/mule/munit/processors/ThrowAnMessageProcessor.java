
package org.mule.munit.processors;

import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MessagingException;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.processor.MessageProcessor;
import org.mule.config.i18n.CoreMessages;
import org.mule.munit.Attribute;
import org.mule.munit.MockModule;
import org.mule.munit.adapters.MockModuleProcessAdapter;
import org.mule.munit.process.ProcessAdapter;
import org.mule.munit.process.ProcessCallback;
import org.mule.munit.process.ProcessTemplate;


/**
 * ThrowAnMessageProcessor invokes the {@link org.mule.munit.MockModule#throwAn(java.lang.Throwable, java.lang.String, java.util.List)} method in {@link MockModule }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:12-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class ThrowAnMessageProcessor
    extends AbstractMessageProcessor<Object>
    implements Disposable, Initialisable, Startable, Stoppable, MessageProcessor
{

    protected Object exception;
    protected Throwable _exceptionType;
    protected Object whenCalling;
    protected String _whenCallingType;
    protected Object withAttributes;
    protected List<Attribute> _withAttributesType;

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    public void start()
        throws MuleException
    {
    }

    public void stop()
        throws MuleException
    {
    }

    public void dispose() {
    }

    /**
     * Set the Mule context
     * 
     * @param context Mule context to set
     */
    public void setMuleContext(MuleContext context) {
        super.setMuleContext(context);
    }

    /**
     * Sets flow construct
     * 
     * @param flowConstruct Flow construct to set
     */
    public void setFlowConstruct(FlowConstruct flowConstruct) {
        super.setFlowConstruct(flowConstruct);
    }

    /**
     * Sets whenCalling
     * 
     * @param value Value to set
     */
    public void setWhenCalling(Object value) {
        this.whenCalling = value;
    }

    /**
     * Sets withAttributes
     * 
     * @param value Value to set
     */
    public void setWithAttributes(Object value) {
        this.withAttributes = value;
    }

    /**
     * Sets exception
     * 
     * @param value Value to set
     */
    public void setException(Object value) {
        this.exception = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws MuleException
     */
    public MuleEvent process(final MuleEvent event)
        throws MuleException
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(MockModuleProcessAdapter.class, true, event);
            final Throwable _transformedException = ((Throwable) evaluateAndTransform(getMuleContext(), event, ThrowAnMessageProcessor.class.getDeclaredField("_exceptionType").getGenericType(), null, exception));
            final String _transformedWhenCalling = ((String) evaluateAndTransform(getMuleContext(), event, ThrowAnMessageProcessor.class.getDeclaredField("_whenCallingType").getGenericType(), null, whenCalling));
            final List<Attribute> _transformedWithAttributes = ((List<Attribute> ) evaluateAndTransform(getMuleContext(), event, ThrowAnMessageProcessor.class.getDeclaredField("_withAttributesType").getGenericType(), null, withAttributes));
            ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    ((MockModule) object).throwAn(_transformedException, _transformedWhenCalling, _transformedWithAttributes);
                    return null;
                }

            }
            , this, event);
            return event;
        } catch (MessagingException messagingException) {
            messagingException.setProcessedEvent(event);
            throw messagingException;
        } catch (Exception e) {
            throw new MessagingException(CoreMessages.failedToInvoke("throwAn"), event, e);
        }
    }

}
