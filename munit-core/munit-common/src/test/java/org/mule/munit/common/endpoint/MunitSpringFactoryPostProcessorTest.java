/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.endpoint;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitSpringFactoryPostProcessorTest
{

    public static final ArrayList<String> MOCKING_EXCLUDED_FLOWS = new ArrayList<String>();


    @Test
    public void testPostProcessBeanFactoryWithoutMocking()
    {
        MunitSpringFactoryPostProcessor pp = new MunitSpringFactoryPostProcessor();
        pp.setMockInbounds(false);
        pp.setMockingExcludedFlows(MOCKING_EXCLUDED_FLOWS);
        pp.setMockConnectors(false);

        assertFalse(pp.mockConnectors);
        assertFalse(pp.mockInbounds);
        assertEquals(MOCKING_EXCLUDED_FLOWS, pp.mockingExcludedFlows);
    }


}
