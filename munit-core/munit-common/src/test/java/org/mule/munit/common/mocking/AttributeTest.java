/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.mocking;

import static org.junit.Assert.assertEquals;
import static org.mule.munit.common.mocking.Attribute.attribute;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.4.0
 */
public class AttributeTest
{

    public static final String NAME = "theName";
    private static final String NAMESPACE = "theNamespace";

    @Test
    public void withoutNamespaceIdIsName()
    {
        assertEquals(NAME, attribute(NAME).getId());
    }

    @Test
    public void withNamespaceIdIsNamespacePlusName()
    {
        assertEquals(NAMESPACE +":"+NAME, attribute(NAME).ofNamespace(NAMESPACE).getId());
    }
}
