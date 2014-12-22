/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

import org.mule.api.annotations.param.OutboundHeaders;
import org.mule.api.annotations.param.Payload;

import java.util.Map;

/**
 * Created by damiansima on 12/19/14.
 */
public class AnnotatedComponent {

    public Object process(@Payload Object payload, @OutboundHeaders Map<String, Object> outHeaders) {
        System.out.println("the component Test: " + payload.toString());
        payload = "the new payload";
        return payload;
    }
}
