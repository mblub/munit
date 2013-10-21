/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mp;

import org.mule.api.processor.MessageProcessor;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * The Assertions the must be executed after and before a message processor call
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class SpyAssertion
{

    /**
     * <p>
     * The Message processors to be executed before the call
     * </p>
     */
    private List<MessageProcessor> beforeMessageProcessors = new ArrayList<MessageProcessor>();

    /**
     * <p>
     * The Message processors to be executed after the call
     * </p>
     */
    private List<MessageProcessor> afterMessageProcessors = new ArrayList<MessageProcessor>();

    public SpyAssertion(List<MessageProcessor> beforeMessageProcessors, List<MessageProcessor> afterMessageProcessors)
    {
        this.beforeMessageProcessors = beforeMessageProcessors;
        this.afterMessageProcessors = afterMessageProcessors;
    }

    public List<MessageProcessor> getBeforeMessageProcessors()
    {
        return beforeMessageProcessors;
    }

    public List<MessageProcessor> getAfterMessageProcessors()
    {
        return afterMessageProcessors;
    }

    public void setBeforeMessageProcessors(List<MessageProcessor> beforeMessageProcessors)
    {
        this.beforeMessageProcessors = beforeMessageProcessors;
    }

    public void setAfterMessageProcessors(List<MessageProcessor> afterMessageProcessors)
    {
        this.afterMessageProcessors = afterMessageProcessors;
    }
}
