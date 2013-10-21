/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.endpoint;

import org.junit.Before;
import org.junit.Test;

import org.mule.api.MuleMessage;
import org.mule.api.processor.MessageProcessor;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class OutboundBehaviorTest
{

    public static final ArrayList<MessageProcessor> ASSERTIONS = new ArrayList<MessageProcessor>();
    private MuleMessage message;

    @Before
    public void setUp()
    {
        message = mock(MuleMessage.class);
    }

    @Test
    public void testGetters()
    {
        OutboundBehavior behavior = new OutboundBehavior(message, ASSERTIONS);


        assertEquals(ASSERTIONS, behavior.getAssertions());
        assertEquals(message, behavior.getMessage());
    }
}
