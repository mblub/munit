/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
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
