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
