/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
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
