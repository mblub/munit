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
