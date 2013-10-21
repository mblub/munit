/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.mel.assertions;

import org.junit.Test;

import org.mule.modules.interceptor.matchers.Matcher;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Mulesoft Inc.
 * @since 3.4
 */
public class ElementMatcherTest
{

    public static final Object ELEMENT = new Object();
    private Matcher matcher = mock(Matcher.class);

    @Test
    public void elementMatcherMustCallTheOriginalMatcher()
    {
        ElementMatcher elementMatcher = new ElementMatcher(ELEMENT);
        when(matcher.match(ELEMENT)).thenReturn(true);

        assertTrue(elementMatcher.is(matcher));

        verify(matcher).match(ELEMENT);

    }

}
