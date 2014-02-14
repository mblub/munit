/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.mocking;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class NotDefinedPayloadTest
{

    @Test
    public void testItCreatesANonDefinedPayload()
    {
        assertTrue(NotDefinedPayload.getInstance() != null);
    }


    @Test
    public void testIfNonDefinedPayloadThenNonDefined()
    {
        assertTrue(NotDefinedPayload.isNotDefined(NotDefinedPayload.getInstance()));
    }
}
