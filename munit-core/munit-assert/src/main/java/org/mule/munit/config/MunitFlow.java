/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
