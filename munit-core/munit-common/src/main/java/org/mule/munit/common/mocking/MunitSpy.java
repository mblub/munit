/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mocking;


import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;
import org.mule.modules.interceptor.processors.MessageProcessorId;
import org.mule.munit.common.mp.SpyAssertion;

import java.util.*;

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

    public MunitSpy withAttributes(Map<String, Object> attributes) {
        if(attributes != null)
        {
            this.messageProcessorAttributes = attributes;
        }
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

            MessageProcessorId messageProcessorId = new MessageProcessorId(messageProcessorName, messageProcessorNamespace, messageProcessorAttributes);
            SpyAssertion spyAssertion = getManager().getSpyAssertions().get(messageProcessorId);
            if (spyAssertion == null)
            {
                getManager().addSpyAssertion(messageProcessorId,
                                             createSpyAssertion(withSpies, Collections.<SpyProcess>emptyList()));
            }
            else
            {
                spyAssertion.setBeforeMessageProcessors(createMessageProcessors(withSpies));
            }
        }
        return this;
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
            MessageProcessorId messageProcessorId = new MessageProcessorId(messageProcessorName, messageProcessorNamespace, messageProcessorAttributes);
            SpyAssertion spyAssertion = getManager().getSpyAssertions().get(messageProcessorId);
            if (spyAssertion == null)
            {
                getManager().addSpyAssertion(new MessageProcessorId(messageProcessorName, messageProcessorNamespace),
                                             createSpyAssertion(Collections.<SpyProcess>emptyList(), withSpies));
            }
            else
            {
                spyAssertion.setAfterMessageProcessors(createMessageProcessors(withSpies));
            }
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

    protected SpyAssertion createSpyAssertion(List<SpyProcess> beforeCall, List<SpyProcess> afterCall)
    {
        return new SpyAssertion(createMessageProcessors(beforeCall), createMessageProcessors(afterCall));
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
