/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.endpoint;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.mule.api.processor.MessageProcessor;
import org.mule.modules.interceptor.processors.MuleMessageTransformer;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class OutboundBehaviorTest
{

    public static final ArrayList<MessageProcessor> ASSERTIONS = new ArrayList<MessageProcessor>();
    private MuleMessageTransformer transformer;

    @Before
    public void setUp()
    {
        transformer = mock(MuleMessageTransformer.class);
    }

    @Test
    public void testGetters()
    {
        OutboundBehavior behavior = new OutboundBehavior(transformer, ASSERTIONS);


        assertEquals(ASSERTIONS, behavior.getAssertions());
        assertEquals(transformer, behavior.getMuleMessageTransformer());
    }
}
