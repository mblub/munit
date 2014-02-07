/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.mp;

import org.mule.api.MuleContext;

import net.sf.cglib.proxy.MethodProxy;

/**
 * <p>
 * A class for test only. This just overrides protected methods to simplify class testing
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MockMunitMessageProcessorInterceptor extends MunitMessageProcessorInterceptor
{

    MockedMessageProcessorManager manager;
    boolean mockProcess;
    MuleContext context;

    public MockMunitMessageProcessorInterceptor(MockedMessageProcessorManager manager)
    {
        this.manager = manager;
    }

    @Override
    public Object process(Object obj, Object[] args, MethodProxy proxy) throws Throwable
    {
        if (mockProcess)
        {
            return obj;
        }
        return super.process(obj, args, proxy);
    }

    @Override
    protected MockedMessageProcessorManager getMockedMessageProcessorManager(MuleContext muleContext)
    {
        return manager;
    }


    public void setMockProcess(boolean mockProcess)
    {
        this.mockProcess = mockProcess;
    }

    public void setContext(MuleContext context)
    {
        this.context = context;
    }
}
