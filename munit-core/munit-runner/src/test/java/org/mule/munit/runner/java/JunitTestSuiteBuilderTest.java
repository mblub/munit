/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.java;


import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import org.mule.munit.config.MunitTestFlow;

import org.junit.Before;
import org.junit.Test;

public class JunitTestSuiteBuilderTest
{

    MunitTestFlow flow;

    @Before
    public void setUp()
    {
        flow = mock(MunitTestFlow.class);
    }


    @Test
    public void createTestMustNotBeNull()
    {
        JunitTestSuiteBuilder builder = new JunitTestSuiteBuilder(null);
        assertNotNull(builder.test(null, flow, null));
    }

    @Test
    public void createTSuiteMustNotBeNull()
    {
        JunitTestSuiteBuilder builder = new JunitTestSuiteBuilder(null);
        assertNotNull(builder.createSuite("test"));
    }
}
