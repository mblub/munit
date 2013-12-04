/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.munit.common.mocking;

/**
 * <p>
 * Helper class to create a message processor attribute
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4.0
 */
public class Attribute
{

    /**
     * <p>
     * The name of the attribute
     * </p>
     */
    private String name;

    /**
     * <p>
     * The namespace of the attribute, for example doc in doc:name, it is very useful for annotations
     * </p>
     */
    private String namespace;

    /**
     * <p>
     * The value of the attribute (evaluated)
     * </p>
     */
    private Object value;

    /**
     * <p>
     * Factory Method of the class
     * </p>
     *
     * @param name The name of the attribute
     * @return A new instance of attribute
     */
    public static Attribute attribute(String name)
    {
        return new Attribute(name);
    }

    private Attribute(String name)
    {
        this.name = name;
    }

    public Attribute ofNamespace(String namespace)
    {
        this.namespace = namespace;
        return this;
    }

    public Attribute withValue(Object value)
    {
        this.value = value;
        return this;
    }

    String getId()
    {
        return namespace != null ? namespace + ":" + name : name;
    }

    Object getValue()
    {
        return value;
    }
}
