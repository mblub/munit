/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
