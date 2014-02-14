/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

import org.mule.api.MuleEvent;

/**
 * <p>Interface that all the custom assertions must implement</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public interface MunitAssertion
{

    /**
     * <p>Method that asserts a mule event</p>
     *
     * @param muleEvent <p>Mule Event to be asserted</p></p>
     * @return <p>The original Mule Event</p>
     * @throws AssertionError <p>Case the assertion fails</p>
     */
    MuleEvent execute(MuleEvent muleEvent) throws AssertionError;
}
