/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.mule.context;

import org.mule.munit.common.MunitCore;

import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XNIException;


/**
 * <p>
 * We override the {@link DOMParser} implementation to retrieve the line number of the XML
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.4
 */
public class MunitDomParser extends DOMParser
{

    public static final String NAMESPACE = "http://www.mule.org/munit";
    XMLLocator xmlLocator;

    @Override
    public void startDocument(XMLLocator locator, String encoding, NamespaceContext namespaceContext, Augmentations augs) throws XNIException
    {
        this.xmlLocator = locator;
        super.startDocument(locator, encoding, namespaceContext, augs);
    }

    @Override
    public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException
    {
        String location = String.valueOf(xmlLocator.getLineNumber());
        attributes.addAttribute(new QName(MunitCore.LINE_NUMBER_ELEMENT_ATTRIBUTE, null,
                                          MunitCore.LINE_NUMBER_ELEMENT_ATTRIBUTE, NAMESPACE), "CDATA", location);
        super.startElement(element, attributes, augs);
    }


}
