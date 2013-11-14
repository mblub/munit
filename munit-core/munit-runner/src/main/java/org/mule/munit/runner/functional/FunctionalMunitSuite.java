/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.functional;

import org.junit.After;
import org.junit.Before;

import org.mule.DefaultMuleMessage;
import org.mule.MessageExchangePattern;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.construct.Flow;
import org.mule.modules.interceptor.matchers.*;
import org.mule.munit.common.MunitCore;
import org.mule.munit.common.mocking.EndpointMocker;
import org.mule.munit.common.mocking.MessageProcessorMocker;
import org.mule.munit.common.mocking.MunitSpy;
import org.mule.munit.common.mocking.MunitVerifier;
import org.mule.munit.runner.MuleContextManager;
import org.mule.munit.runner.mule.context.MockingConfiguration;
import org.mule.tck.MuleTestUtils;

import java.io.InputStream;
import java.util.*;

public abstract class FunctionalMunitSuite
{

    protected MuleContext muleContext;
    
    private MuleContextManager muleContextManager;

	public FunctionalMunitSuite()
    {
        muleContextManager = new MuleContextManager(createConfiguration());
        try
        {
            String resources = getConfigResources();

            muleContext = muleContextManager.startMule(resources);

        }
        catch (Exception e)
        {
            muleContextManager.killMule(muleContext);
            throw new RuntimeException(e);
        }
    }


    private MockingConfiguration createConfiguration()
    {
        return new MockingConfiguration(haveToDisableInboundEndpoints(), getFlowsExcludedOfInboundDisabling(), haveToMockMuleConnectors(), getStartUpProperties());
    }

    /**
     * <p>
     * By default all of the flows of the application will start with the inbound endpoints disabled. This method
     * allows users to define which flows are excluded from that option.
     * </p>
     *
     * @return <p>
     *         A list of flow names that have to be excluded of inbound Disabling
     *         </p>
     */
    protected List<String> getFlowsExcludedOfInboundDisabling()
    {
        return new ArrayList<String>();
    }

    /**
     * <p>
     * Determines if the inbound endpoints have to be present in the flows. An inbound endpoint is the entry point
     * of a flow. When testing you may want them to be disabled. For example, you don't want poll endpoint to be
     * polling when you are testing.
     * <p/>
     * This method tells Munit to remove the endpoints of your application.
     * </p>
     *
     * @return <p>
     *         TRUE/FALSE depending if the inbound endpoints have to be disabled or not.
     *         </p>
     */
    protected boolean haveToDisableInboundEndpoints()
    {
        return true;
    }

    /**
     * <p>
     * Determines if mule connectors (as JDBC connector for example) have to be mocked.
     * By default the values is true, if users want to create an integration test and don't want to mock these
     * connectors, then override the method to return false
     * </p>
     * <p>
     * We need to mark the difference between mule connectors and cloud connectors. A Mule connector is a HTTP
     * connector or a JDBC connector, a cloud connector is a DEVKIT module (or mule extension)
     * </p>
     *
     * @return <p>
     *         True/False depending if the mule connectors have to be mocked or not.
     *         </p>
     */
    protected boolean haveToMockMuleConnectors()
    {
        return true;
    }

    @Before
    public final void __setUpMunit()
    {
        MunitCore.registerManager(muleContext);
    }

    @After
    public final void __restartMunit()
    {
        MunitCore.reset(muleContext);
    }

    /**
     * @return <p>
     *         If mule-deploy.properties exists then return the  config.resources property value
     *         </p>
     *         <p>
     *         Else if mule-config.xml exists then return "mule-config.xml" as default value.
     *         </p>
     *         <p>
     *         Otherwise, throw exception and the test will fail. You need to override this method.
     *         </p>
     */
    protected String getConfigResources()
    {
        Properties props = this.loadProperties("/mule-deploy.properties");

        if (props != null && props.getProperty("config.resources") != null)
        {
            return props.getProperty("config.resources");
        }
        else
        {
            InputStream in = this.getClass().getResourceAsStream("/mule-config.xml");
            if (in != null)
            {
                return "mule-config.xml";
            }
            else
            {
                throw new IllegalStateException("Could not find mule-deploy.properties nor mule-config.xml file on classpath. Please add any of those files or override the getConfigResources() method to provide the resources by your own");
            }
        }
    }

    /**
     * @param payload <p>
     *                The event payload for testing
     *                </p>
     * @return <p>
     *         A MuleEvent that contains a message with payload equals to the param payload
     *         </p>
     * @throws Exception <p>
     *                   If the event cloud not be created.
     *                   </p>
     */
    protected final MuleEvent testEvent(Object payload) throws Exception
    {
        return MuleTestUtils.getTestEvent(payload, MessageExchangePattern.REQUEST_RESPONSE, muleContext);
    }

    /**
     * @param payload <p>
     *                The payload of the MuleMessage to be created
     *                </p>
     * @return <p>
     *         A mule message without properties and with the specified payload
     *         </p>
     */
    protected final MuleMessage muleMessageWithPayload(Object payload)
    {
        return new DefaultMuleMessage(payload, muleContext);
    }

    protected final MessageProcessorMocker whenMessageProcessor(String name)
    {
        return new MessageProcessorMocker(muleContext).when(name);
    }

    protected final MunitVerifier verifyCallOfMessageProcessor(String name)
    {
        return new MunitVerifier(muleContext).verifyCallOfMessageProcessor(name);
    }

    protected final MunitSpy spyMessageProcessor(String name)
    {
        return new MunitSpy(muleContext).spyMessageProcessor(name);
    }

    protected final MuleEvent runFlow(String name, MuleEvent event) throws MuleException
    {
        Flow flow = (Flow) muleContext.getRegistry().lookupFlowConstruct(name);
        if (flow == null)
        {
            throw new IllegalArgumentException("Flow " + name + " does not exist");
        }
        return flow.process(event);
    }

    protected final EndpointMocker whenEndpointWithAddress(String address)
    {
        EndpointMocker endpointMocker = new EndpointMocker(muleContext);
        return endpointMocker.whenEndpointWithAddress(address);
    }

    protected final Matcher any()
    {
        return new AnyClassMatcher(Object.class);
    }

    protected final Matcher isNotNull()
    {
        return new NotNullMatcher();
    }

    protected final Matcher isNull()
    {
        return new NullMatcher();
    }

    protected final Matcher anyCollection()
    {
        return new AnyClassMatcher(Collection.class);
    }

    protected final Matcher anyMap()
    {
        return new AnyClassMatcher(Map.class);
    }

    protected final Matcher anySet()
    {
        return new AnyClassMatcher(Set.class);
    }

    protected final Matcher anyList()
    {
        return new AnyClassMatcher(List.class);
    }

    protected final Matcher anyString()
    {
        return new AnyClassMatcher(String.class);
    }

    protected final Matcher anyObject()
    {
        return new AnyClassMatcher(Object.class);
    }

    protected final Matcher anyShort()
    {
        return new AnyClassMatcher(Short.class);
    }

    protected final Matcher anyFloat()
    {
        return new AnyClassMatcher(Float.class);
    }

    protected Matcher anyDouble()
    {
        return new AnyClassMatcher(Double.class);
    }

    protected final Matcher eq(Object o)
    {
        return new EqMatcher(o);
    }

    protected final Matcher anyBoolean()
    {
        return new AnyClassMatcher(Boolean.class);
    }

    protected final Matcher anyByte()
    {
        return new AnyClassMatcher(Byte.class);
    }

    protected final Matcher anyInt()
    {
        return new AnyClassMatcher(Integer.class);
    }

    protected Properties getStartUpProperties()
    {
        return null;
    }

    private Properties loadProperties(String propertyFile)
    {
        try
        {
            Properties prop = new Properties();
            InputStream in = getClass().getResourceAsStream(propertyFile);
            prop.load(in);
            in.close();
            return prop;
        }
        catch (Throwable t)
        {
            return null;
        }
    }
    
    @Override
	protected void finalize() throws Throwable {
    	muleContextManager.killMule(muleContext);
		super.finalize();
	}

}
