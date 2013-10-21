/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.mule.context;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.w3c.dom.Element;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitBeanDefinitionDocumentReaderTest
{

    private XmlReaderContext readerContext = mock(XmlReaderContext.class);
    private Element element = mock(Element.class);
    private BeanDefinitionParserDelegate parentDelegate = mock(BeanDefinitionParserDelegate.class);

    @Test
    public void testDelegateInstanceCreation()
    {
        assertTrue(new MunitBeanDefinitionDocumentReader().createHelper(readerContext, element, parentDelegate) instanceof MunitBeanDefinitionParserDelegate);
    }
}
