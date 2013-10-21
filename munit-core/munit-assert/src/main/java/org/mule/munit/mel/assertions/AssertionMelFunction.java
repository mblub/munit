/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.mel.assertions;

import org.mule.api.MuleMessage;
import org.mule.api.el.ExpressionLanguageContext;
import org.mule.api.el.ExpressionLanguageFunction;
import org.mule.el.context.MessageContext;

import java.lang.reflect.Field;


/**
 * <p>
 * Abstract class that represents an MEL function for asserting
 * </p>
 * <p/>
 * <p>
 * This class just contains a method to get the mule message when the function is called.
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4
 */
public abstract class AssertionMelFunction implements ExpressionLanguageFunction
{

    protected MuleMessage getMuleMessageFrom(ExpressionLanguageContext context)
    {
        return context.getVariable("_muleMessage");
    }
}
