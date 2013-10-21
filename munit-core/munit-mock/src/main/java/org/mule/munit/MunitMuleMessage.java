/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.param.Optional;

import java.util.Map;

public class MunitMuleMessage
{

    /**
     * <p>The Mule Payload</p>
     */
    @Configurable
    private Object payload;

    /**
     * <p>The Mule InvocationProperties</p>
     */
    @Configurable
    @Optional
    private Map<String, Object> invocationProperties;

    /**
     * <p>The Mule Inbund properties</p>
     */
    @Configurable
    @Optional
    private Map<String, Object> inboundProperties;

    /**
     * <p>The Mule Session Properties</p>
     */
    @Configurable
    @Optional
    private Map<String, Object> sessionProperties;

    /**
     * <p>The Mule outbound Properties</p>
     */
    @Configurable
    @Optional
    private Map<String, Object> outboundProperties;

    public Object getPayload()
    {
        return payload;
    }

    public void setPayload(Object payload)
    {
        this.payload = payload;
    }

    public Map<String, Object> getInvocationProperties()
    {
        return invocationProperties;
    }

    public void setInvocationProperties(Map<String, Object> invocationProperties)
    {
        this.invocationProperties = invocationProperties;
    }

    public Map<String, Object> getInboundProperties()
    {
        return inboundProperties;
    }

    public void setInboundProperties(Map<String, Object> inboundProperties)
    {
        this.inboundProperties = inboundProperties;
    }

    public Map<String, Object> getSessionProperties()
    {
        return sessionProperties;
    }

    public void setSessionProperties(Map<String, Object> sessionProperties)
    {
        this.sessionProperties = sessionProperties;
    }

    public Map<String, Object> getOutboundProperties()
    {
        return outboundProperties;
    }

    public void setOutboundProperties(Map<String, Object> outboundProperties)
    {
        this.outboundProperties = outboundProperties;
    }
}
