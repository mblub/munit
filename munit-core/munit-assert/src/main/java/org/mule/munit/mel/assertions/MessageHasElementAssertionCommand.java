/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.mel.assertions;

import org.mule.api.MuleMessage;


/**
 * <p>
 * Command to be executed on any messageHas MEL expression, for example:
 * <p/>
 * When we do: #[messageHasInboundPropertyCalled('something') we end up calling this command that calls
 * Hamcrest module functionality.
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4
 */
public interface MessageHasElementAssertionCommand
{

    /**
     * <p>
     * Main method
     * </p>
     *
     * @param param       <p>
     *                    The name of the property/attachment (basically the name of the element of the {@link MuleMessage} that we want
     *                    to check existence of
     *                    </p>
     * @param muleMessage <p>
     *                    The {@link MuleMessage} used to retrieve the element with param name
     *                    </p>
     * @return <p>
     *         true/false if the message has or not the element with param name.
     *         </p>
     */
    boolean messageHas(String param, MuleMessage muleMessage);
}
