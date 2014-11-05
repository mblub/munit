/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;


import org.junit.Test;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.NestedProcessor;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class NestedMessageProcessorTest
{

    private NestedProcessor nestedProcessor = mock(NestedProcessor.class);
    private MuleEvent inMuleEvent = mock(MuleEvent.class);
    private MuleEvent outMuleEvent = mock(MuleEvent.class);


    @Test
    public void happyPath() throws Exception
    {

        MuleMessage muleMessage = mock(MuleMessage.class);
        when(muleMessage.getPayload()).thenReturn("");
        Set<String> invocationProperties = new HashSet<String>();
        invocationProperties.add("aVariableName");
        when(muleMessage.getInvocationPropertyNames()).thenReturn(invocationProperties);
        when(muleMessage.getInvocationProperty("aVariableName")).thenReturn(new Object());
        when(inMuleEvent.getMessage()).thenReturn(muleMessage);
        when(nestedProcessor.process(eq(""),anyMap())).thenReturn(outMuleEvent);

        assertEquals(inMuleEvent, mp().process(inMuleEvent));

        verify(nestedProcessor, times(1)).process(eq(""),anyMap());
    }

    @Test(expected = MuleException.class)
    public void exceptionHandling() throws Exception
    {
        when(nestedProcessor.process()).thenThrow(new Exception());
        mp().process(inMuleEvent);

        verify(nestedProcessor, times(1)).process();
    }

    private NestedMessageProcessor mp()
    {
        return new NestedMessageProcessor(nestedProcessor);
    }

}
