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


/**
 * <p>
 * MEL function to evaluate that an element of the message matches something
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4
 */
public class ElementMatchingAssertionMelFunction extends AssertionMelFunction
{

    @Override
    public Object call(Object[] params, ExpressionLanguageContext context)
    {
        if (params != null && params.length > 0)
        {
            MuleMessage messageFrom = getMuleMessageFrom(context);

            if (messageFrom == null)
            {
                throw new RuntimeException("Could not get message;");
            }

            return  new ElementMatcher(params[0]);
        }

        throw new IllegalArgumentException("Invalid parameter");
    }

}
