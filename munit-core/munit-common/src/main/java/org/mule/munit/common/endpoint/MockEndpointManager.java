/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.common.endpoint;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.endpoint.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This is a Wrapper of the Mule Endpoint factory. It creates Real Inbound endpoints and mocked Outbound Endpoints
 * </p>
 * <p/>
 * <p>
 * On the other hand the Endpoint manager maintains a list of expected behaviors for the outbound endpoints
 * </p>
 * <p/>
 * <p>
 * This class must be reset before any Munit test run.
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MockEndpointManager implements EndpointFactory
{

    /**
     * <p>
     * The Default Mule endpoint factory.
     * </p>
     */
    protected EndpointFactory defaultFactory;

    /**
     * <p>
     * The expected behaviors for the outbound endpoints, indexed by endpoint address.
     * </p>
     */
    protected Map<String, OutboundBehavior> behaviors = new HashMap<String, OutboundBehavior>();

    @Override
    public InboundEndpoint getInboundEndpoint(String uri) throws MuleException
    {
        return defaultFactory.getInboundEndpoint(uri);
    }

    @Override
    public OutboundEndpoint getOutboundEndpoint(String uri) throws MuleException
    {
        return new MockOutboundEndpoint(defaultFactory.getOutboundEndpoint(uri));
    }

    @Override
    public InboundEndpoint getInboundEndpoint(EndpointBuilder builder) throws MuleException
    {
        return defaultFactory.getInboundEndpoint(builder);
    }

    @Override
    public OutboundEndpoint getOutboundEndpoint(EndpointBuilder builder) throws MuleException
    {
        return new MockOutboundEndpoint(defaultFactory.getOutboundEndpoint(builder));
    }

    @Override
    public InboundEndpoint getInboundEndpoint(EndpointURI endpointUri) throws MuleException
    {
        return defaultFactory.getInboundEndpoint(endpointUri);
    }

    @Override
    public OutboundEndpoint getOutboundEndpoint(EndpointURI endpointUri) throws MuleException
    {
        return new MockOutboundEndpoint(defaultFactory.getOutboundEndpoint(endpointUri));
    }

    @Override
    public EndpointBuilder getEndpointBuilder(String uri) throws MuleException
    {
        return defaultFactory.getEndpointBuilder(uri);
    }

    @Override
    public void setMuleContext(MuleContext context)
    {
    }

    public void setDefaultFactory(EndpointFactory defaultFactory)
    {
        this.defaultFactory = defaultFactory;
    }


    /**
     * <p>
     * Adds a new expected behavior for an outbound endpoint's address
     * </p>
     *
     * @param address  <p>
     *                 The outbound endpoint address
     *                 </p>
     * @param behavior <p>
     *                 The expected behaviour which is a representation of a desired MuleMessage plus the before/after
     *                 assertions for the outbound endpoint call.
     *                 </p>
     */
    public void addBehavior(String address, OutboundBehavior behavior)
    {
        behaviors.put(address, behavior);
    }

    /**
     * <p>
     * Gets the behavior of an address
     * </p>
     */
    public OutboundBehavior getBehaviorFor(String address)
    {
        return this.behaviors.get(address);
    }

    /**
     * <p>
     * Resets all the expected behaviors for the outbound endpoints of the application.
     * </p>
     */
    public void resetBehaviors()
    {
        this.behaviors.clear();
    }
}
