package org.mule.munit.mel.assertions;

import org.mule.api.MuleMessage;
import org.mule.api.el.ExpressionLanguageContext;


/**
 * <p>
 * MEL function to evaluate that an element of the message matches something
 * </p>
 *
 * @author Federico, Fernando
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
