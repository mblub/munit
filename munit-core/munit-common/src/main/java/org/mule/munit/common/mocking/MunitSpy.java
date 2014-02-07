/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.mocking;


import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;
import org.mule.modules.interceptor.processors.MessageProcessorCall;
import org.mule.modules.interceptor.processors.MessageProcessorId;
import org.mule.munit.common.mp.SpyAssertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * This class is a Munit Tool to create Message processor spiers
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitSpy extends MunitMockingTool
{

    public MunitSpy(MuleContext muleContext)
    {
        super(muleContext);
    }

    /**
     * <p>
     * Defines the name of the message processor to spy
     * </p>
     *
     * @param name <p>
     *             The name of the message processor to spy
     *             </p>
     * @return <p>
     *         Itself
     *         </p>
     */
    public MunitSpy spyMessageProcessor(String name)
    {
        this.messageProcessorName = name;
        return this;
    }

    /**
     * <p>
     * Defines the namespace of the message processor to spy
     * </p>
     *
     * @param namespace <p>
     *                  The namespace of the message processor to spy
     *                  </p>
     * @return <p>
     *         Itself
     *         </p>
     */
    public MunitSpy ofNamespace(String namespace)
    {
        this.messageProcessorNamespace = namespace;
        return this;
    }

    public MunitSpy withAttributes(Map<String, Object> attributes)
    {
        if (attributes != null)
        {
            this.messageProcessorAttributes = attributes;
        }
        return this;
    }
    
    public MunitSpy withAttributes(Attribute ... attributes)
    {
        Map<String, Object> mapOfAttributes = new HashMap<String, Object>();
        for ( Attribute attribute : attributes )
        {
            mapOfAttributes.put(attribute.getId(), attribute.getValue());
        }
        this.messageProcessorAttributes = mapOfAttributes;
        return this;
    }


    /**
     * The {@link SpyProcess} to run before the message processor
     *
     * @param withSpies Processes to run before the message processor call
     */
    public MunitSpy before(final List<SpyProcess> withSpies)
    {
        if (withSpies != null && !withSpies.isEmpty())
        {
            MessageProcessorCall messageProcessorCall = createMessageProcessorCall();
            getManager().addBeforeCallSpyAssertion(createSpyAssertion(messageProcessorCall, withSpies));
        }

        return this;
    }

    private MessageProcessorCall createMessageProcessorCall()
    {
        MessageProcessorCall messageProcessorCall = new MessageProcessorCall(new MessageProcessorId(messageProcessorName, messageProcessorNamespace));
        messageProcessorCall.setAttributes(messageProcessorAttributes);
        return messageProcessorCall;
    }

    /**
     * The {@link SpyProcess}es to run before the message processor
     *
     * @param withSpy Processeses to run before the message processor call
     */
    public MunitSpy before(final SpyProcess... withSpy)
    {
        return before(Arrays.asList(withSpy));
    }

    /**
     * The {@link SpyProcess} to run after the message processor
     *
     * @param withSpies Processes to run after the message processor call
     */
    public MunitSpy after(final List<SpyProcess> withSpies)
    {
        if (withSpies != null && !withSpies.isEmpty())
        {
            MessageProcessorCall messageProcessorCall = createMessageProcessorCall();
            getManager().addAfterCallSpyAssertion(createSpyAssertion(messageProcessorCall, withSpies));
        }
        return this;
    }

    /**
     * The {@link SpyProcess}es to run after the message processor
     *
     * @param withSpy Processeses to run after the message processor call
     */
    public MunitSpy after(final SpyProcess... withSpy)
    {
        return after(Arrays.asList(withSpy));
    }

    protected SpyAssertion createSpyAssertion(MessageProcessorCall call, List<SpyProcess> spyProcesses)
    {
        return new SpyAssertion(call, createMessageProcessors(spyProcesses));
    }

    private ArrayList<MessageProcessor> createMessageProcessors(List<SpyProcess> beforeCall)
    {
        ArrayList<MessageProcessor> beforeMessageProcessors = new ArrayList<MessageProcessor>();
        beforeMessageProcessors.add(createMessageProcessorFromSpy(beforeCall));
        return beforeMessageProcessors;
    }

    private MessageProcessor createMessageProcessorFromSpy(final List<SpyProcess> beforeCall)
    {
        return new MessageProcessor()
        {
            @Override
            public MuleEvent process(MuleEvent event) throws MuleException
            {
                if (beforeCall != null)
                {
                    for (SpyProcess process : beforeCall)
                    {
                        process.spy(event);
                    }
                }
                return event;
            }
        };
    }


}
