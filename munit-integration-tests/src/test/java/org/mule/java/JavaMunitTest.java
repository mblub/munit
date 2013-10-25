/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.java;

import org.mule.tck.junit4.FunctionalTestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.Timeout;


public class JavaMunitTest extends FunctionalTestCase
{

    @Rule
    public MethodRule globalTimeout= new Timeout(20000);

    @Override
    protected String getConfigResources()
    {
        return "mule-config.xml";
    }

    @Test
    public void test() throws Exception
    {
       System.out.println();
    }

    //@Test
    //public void testSetMuleAppHome() throws Exception
    //{
    //
    //    Object payload = runFlow("setMuleAppHomeFlow", testEvent("something")).getMessage().getPayload();
    //
    //    assertEquals(new File(getClass().getResource("/mule-config.xml").getPath()).getParentFile().getAbsolutePath(), payload);
    //}


}
