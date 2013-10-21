/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.config;

import org.mule.api.MuleContext;
import org.mule.construct.Flow;


/**
 * <p>
 * Generic Munit Flow
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitFlow extends Flow
{

    /**
     * <p>The munit test description</p>
     */
    private String description;

    public MunitFlow(String name, MuleContext muleContext)
    {
        super(name, muleContext);
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
