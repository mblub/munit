/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by damiansima on 12/10/14.
 */
public class MunitMuleMessageTest {
    private static final String PAYLOAD = "the_payload";
    private static final Map<String, Object> SESSION = new HashMap<String, Object>();
    private static final Map<String, Object> INBOUND = new HashMap<String, Object>();
    private static final Map<String, Object> OUTBOUND = new HashMap<String, Object>();
    private static final Map<String, Object> INVOCATION = new HashMap<String, Object>();

    private MunitMuleMessage message = new MunitMuleMessage();

    @Before
    public void setUp() {

        SESSION.put("a_session_property", "a_value");
        INBOUND.put("an_inbound_property", "a_value");
        OUTBOUND.put("an_outbound_property", "a_value");
        INVOCATION.put("an_invocation_property", "a_value");


        message = new MunitMuleMessage();
        message.setPayload(PAYLOAD);
        message.setSessionProperties(SESSION);
        message.setInboundProperties(INBOUND);
        message.setOutboundProperties(OUTBOUND);
        message.setInvocationProperties(INVOCATION);
    }

    @After
    public void tearDown() {
        SESSION.clear();
        INBOUND.clear();
        OUTBOUND.clear();
        INVOCATION.clear();
    }

    @Test
    public void testGetPayload() {
        Assert.assertEquals("The payload is wrong", PAYLOAD, message.getPayload());
    }

    @Test
    public void testGetSessionProperties() {
        Assert.assertEquals("The session properties are wrong", SESSION, message.getSessionProperties());
    }

    @Test
    public void testGetInboundProperties() {
        Assert.assertEquals("The inbound properties are wrong", INBOUND, message.getInboundProperties());
    }

    @Test
    public void testGetOutboundProperties() {
        Assert.assertEquals("The outbound properties are wrong", OUTBOUND, message.getOutboundProperties());
    }

    @Test
    public void testGetInvocationProperties() {
        Assert.assertEquals("The invocation properties are wrong", INVOCATION, message.getInvocationProperties());
    }
}
