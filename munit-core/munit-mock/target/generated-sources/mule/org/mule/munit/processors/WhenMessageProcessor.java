
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
import org.mule.munit.MunitMuleMessage;
import org.mule.munit.adapters.MockModuleProcessAdapter;
import org.mule.munit.process.ProcessAdapter;
import org.mule.munit.process.ProcessCallback;
import org.mule.munit.process.ProcessTemplate;


/**
 * WhenMessageProcessor invokes the {@link org.mule.munit.MockModule#when(java.lang.String, java.util.List, org.mule.munit.MunitMuleMessage)} method in {@link MockModule }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:12-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class WhenMessageProcessor
    extends AbstractMessageProcessor<Object>
    implements Disposable, Initialisable, Startable, Stoppable, MessageProcessor
{

    protected Object messageProcessor;
    protected String _messageProcessorType;
    protected Object withAttributes;
    protected List<Attribute> _withAttributesType;
    protected Object thenReturn;
    protected MunitMuleMessage _thenReturnType;

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
     * Sets withAttributes
     * 
     * @param value Value to set
     */
    public void setWithAttributes(Object value) {
        this.withAttributes = value;
    }

    /**
     * Sets thenReturn
     * 
     * @param value Value to set
     */
    public void setThenReturn(Object value) {
        this.thenReturn = value;
    }

    /**
     * Sets messageProcessor
     * 
     * @param value Value to set
     */
    public void setMessageProcessor(Object value) {
        this.messageProcessor = value;
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
            final String _transformedMessageProcessor = ((String) evaluateAndTransform(getMuleContext(), event, WhenMessageProcessor.class.getDeclaredField("_messageProcessorType").getGenericType(), null, messageProcessor));
            final List<Attribute> _transformedWithAttributes = ((List<Attribute> ) evaluateAndTransform(getMuleContext(), event, WhenMessageProcessor.class.getDeclaredField("_withAttributesType").getGenericType(), null, withAttributes));
            final MunitMuleMessage _transformedThenReturn = ((MunitMuleMessage) evaluateAndTransform(getMuleContext(), event, WhenMessageProcessor.class.getDeclaredField("_thenReturnType").getGenericType(), null, thenReturn));
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
                    ((MockModule) object).when(_transformedMessageProcessor, _transformedWithAttributes, _transformedThenReturn);
                    return null;
                }

            }
            , this, event);
            return event;
        } catch (MessagingException messagingException) {
            messagingException.setProcessedEvent(event);
            throw messagingException;
        } catch (Exception e) {
            throw new MessagingException(CoreMessages.failedToInvoke("when"), event, e);
        }
    }

}
