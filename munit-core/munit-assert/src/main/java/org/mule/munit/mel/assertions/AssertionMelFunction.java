/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
