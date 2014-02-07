/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
