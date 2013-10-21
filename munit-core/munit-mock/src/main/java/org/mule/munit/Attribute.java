/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;


import org.mule.api.annotations.Configurable;

/**
 * <p>
 * Definition of the message processor attribute that wants to be mocked.
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class Attribute
{

    /**
     * <p>
     * factory method to simplify test coding
     * </p>
     *
     * @param name       <p>The Name of the attribute</p>
     * @param whereValue <p>Its value</p>
     * @return <p>A new instance of the Attribute</p>
     */
    public static Attribute create(String name, String whereValue)
    {
        Attribute attribute = new Attribute();
        attribute.setName(name);
        attribute.setWhereValue(whereValue);
        return attribute;
    }

    /**
     * <p>The name of the attribute of the message processor</p>
     */
    @Configurable
    private String name;

    /**
     * <p>The object that need to match (can be a matcher expression)</p>
     */
    @Configurable
    private Object whereValue;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Object getWhereValue()
    {
        return whereValue;
    }

    public void setWhereValue(Object whereValue)
    {
        this.whereValue = whereValue;
    }
}
