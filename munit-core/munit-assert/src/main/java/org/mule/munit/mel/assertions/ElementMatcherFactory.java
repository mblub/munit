/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.mel.assertions;


import org.mule.api.MuleMessage;

/**
 * <p>
 * Factory of {@link ElementMatcher}
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4
 */
public interface ElementMatcherFactory
{

    /**
     * @param elementName    <p>
     *                       The name of the element of the {@link MuleMessage}. It can be the name of a property or an attachment
     *                       </p>
     * @param messageContext <p>
     *                       Message context obtained from the MEL expression #[message]
     *                       </p>
     * @return <p>
     *         The {@link ElementMatcher}
     *         </p>
     */
    ElementMatcher build(String elementName, MuleMessage messageContext);
}
