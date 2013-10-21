/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mocking;

import org.junit.Before;
import org.junit.Test;

import org.mule.api.MuleContext;
import org.mule.api.registry.MuleRegistry;
import org.mule.munit.common.mp.MockedMessageProcessorManager;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitMockingToolTest
{

    private MuleContext muleContext;
    private MuleRegistry muleRegistry;
    private MockedMessageProcessorManager manager;

    @Before
    public void setUp()
    {
        muleContext = mock(MuleContext.class);
        muleRegistry = mock(MuleRegistry.class);
        manager = mock(MockedMessageProcessorManager.class);

        when(muleContext.getRegistry()).thenReturn(muleRegistry);
        when(muleRegistry.lookupObject(MockedMessageProcessorManager.ID)).thenReturn(manager);

    }


    @Test
    public void getFullNameWithEmptyNamespace()
    {
        MunitMockingTool munitTool = new MunitMockingTool(muleContext);
        munitTool.messageProcessorName = "testName";

        assertEquals("mule:testName", munitTool.getFullName());
    }

    @Test
    public void getManager()
    {
        MunitMockingTool munitTool = new MunitMockingTool(muleContext);

        assertEquals(manager, munitTool.getManager());
    }


    @Test(expected = IllegalArgumentException.class)
    public void validNameWithNull()
    {
        MunitMockingTool munitTool = new MunitMockingTool(muleContext);

        munitTool.checkValidQuery();
    }


    @Test
    public void validName()
    {
        MunitMockingTool munitTool = new MunitMockingTool(muleContext);
        munitTool.messageProcessorName = "testName";

        munitTool.checkValidQuery();
    }

    @Test
    public void getFullNameWithNamespace()
    {
        MunitMockingTool munitTool = new MunitMockingTool(muleContext);
        munitTool.messageProcessorName = "testName";
        munitTool.messageProcessorNamespace = "testNamespace";

        assertEquals("testNamespace:testName", munitTool.getFullName());
    }
}
