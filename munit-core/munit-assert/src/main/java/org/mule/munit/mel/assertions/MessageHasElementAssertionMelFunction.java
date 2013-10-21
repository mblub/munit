/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.mel.assertions;

import org.mule.api.MuleMessage;
import org.mule.api.el.ExpressionLanguageContext;


/**
 * <p>
 * MEL function to evaluate the existence of an Element in a Message.
 * <p/>
 * All the #[messageHas*PropertyCalled('something') is implemented with this Object
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4
 */
public class MessageHasElementAssertionMelFunction extends AssertionMelFunction
{

    /**
     * <p>
     * The command that does the assertion
     * </p>
     */
    private MessageHasElementAssertionCommand command;

    public MessageHasElementAssertionMelFunction(MessageHasElementAssertionCommand command)
    {
        this.command = command;
    }


    @Override
    public Object call(Object[] params, ExpressionLanguageContext context)
    {
        if (params != null && params.length > 0 && params[0] instanceof String)
        {
            MuleMessage muleMessage = getMuleMessageFrom(context);

            if (muleMessage == null)
            {
                return false;
            }

            return command.messageHas((String) params[0], muleMessage);
        }
        return false;
    }

    public MessageHasElementAssertionCommand getCommand()
    {
        return command;
    }
}
