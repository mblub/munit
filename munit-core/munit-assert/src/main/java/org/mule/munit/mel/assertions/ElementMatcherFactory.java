/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
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
