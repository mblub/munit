/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.endpoint;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * <p>
 * This class changes the endpoint factory and inject the mock manager
 * </p>
 * <p/>
 * <p>
 * This is a piece part of the endpoint mocking. By overriding the endpoint factory we can mock all the outbound/inbound
 * endpoints of a mule application
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitSpringFactoryPostProcessor
{

    private static Logger logger = Logger.getLogger("Bean definition Processor");

    /**
     * <p>
     * Defines if the inbounds must be mocked or not. This is pure Munit configuration
     * </p>
     */
    protected boolean mockInbounds = true;

    /**
     * <p>
     * Defines if the app connectors for outbound/inbound endpoints have to be mocked. If they are then all
     * outbound endpoints/inbound endpoints must be mocked.
     * </p>
     */
    protected boolean mockConnectors = true;

    /**
     * <p>
     * List of flows which we don't want to mock the inbound message sources
     * </p>
     */
    protected List<String> mockingExcludedFlows = new ArrayList<String>();




    public void setMockInbounds(boolean mockInbounds)
    {
        this.mockInbounds = mockInbounds;
    }

    public void setMockingExcludedFlows(List<String> mockingExcludedFlows)
    {
        this.mockingExcludedFlows = mockingExcludedFlows;
    }

    public boolean isMockInbounds()
    {
        return mockInbounds;
    }

    public boolean isMockConnectors()
    {
        return mockConnectors;
    }

    public void setMockConnectors(boolean mockConnectors)
    {
        this.mockConnectors = mockConnectors;
    }
}
