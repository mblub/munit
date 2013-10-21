/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.mule;


import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import org.mule.api.MuleContext;
import org.mule.munit.runner.output.DefaultOutputHandler;

import org.junit.Before;
import org.junit.Test;

public class MunitSuiteBuilderTest
{

    MuleContext muleContext;

    @Before
    public void setUp()
    {
        muleContext = mock(MuleContext.class);

    }

    /**
     * Do not accept null handlers
     */
    @Test(expected = IllegalArgumentException.class)
    public void handlerMustNotBeNull()
    {
        new MunitSuiteBuilder(muleContext, null);
    }

    /**
     * Never return null on test
     */
    @Test
    public void createTestMustNotBeNull()
    {
        assertNotNull(new MunitSuiteBuilder(muleContext, new DefaultOutputHandler()).test(null, null, null));
    }


    /**
     * Never return null when creating a suite.
     */
    @Test
    public void createSuiteMustNotBeNull()
    {
        assertNotNull(new MunitSuiteBuilder(muleContext, new DefaultOutputHandler()).createSuite("test"));
    }

}
