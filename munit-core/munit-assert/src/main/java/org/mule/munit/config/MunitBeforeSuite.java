package org.mule.munit.config;

import org.mule.api.MuleContext;


/**
 * <p>
 * Before Suite flow
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitBeforeSuite extends MunitFlow
{

    public MunitBeforeSuite(String name, MuleContext muleContext)
    {
        super(name, muleContext);
    }
}
