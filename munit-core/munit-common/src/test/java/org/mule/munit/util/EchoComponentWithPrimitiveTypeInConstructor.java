/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.util;

import org.mule.api.MuleEventContext;
import org.mule.component.simple.EchoComponent;

/**
 * Created by damiansima on 11/19/14.
 */
public class EchoComponentWithPrimitiveTypeInConstructor extends EchoComponent {

    public EchoComponentWithPrimitiveTypeInConstructor(int parameter) {

    }

    @Override
    public Object onCall(MuleEventContext context) throws Exception
    {
        super.onCall(context);
        return context.getMessage();
    }

    public String echo(String echo)
    {
        return echo;
    }
}
