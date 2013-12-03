/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.munit.test;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;
import org.mule.api.DefaultMuleException;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.munit.common.mocking.SpyProcess;
import org.mule.munit.runner.functional.FunctionalMunitSuite;


public class AmqpTest extends FunctionalMunitSuite {

	private static final String AMQP_ADDRESS = "amqp://amqp-queue.dummyQueue";
	private static final String ORIGINAL_PAYLOAD = "originalPayload";

	@Test
	public void test() throws MuleException, Exception
	{
		ArrayList<SpyProcess> spy = new ArrayList<SpyProcess>();
		spy.add(new SpyProcess() {
			
			@Override
			public void spy(MuleEvent event) throws MuleException {
				try {
					Assert.assertEquals(ORIGINAL_PAYLOAD, event.getMessage().getPayloadAsString());
				} catch (Exception e) {
					throw new DefaultMuleException(e);
				}			
			}
		});
		
		whenEndpointWithAddress(AMQP_ADDRESS)
		.withIncomingMessageSatisfying(spy)
        .thenReturn(muleMessageWithPayload("OK"));
		
		MuleEvent resultEvent = runFlow("queueMessageFlow", testEvent(ORIGINAL_PAYLOAD));
		
		Assert.assertEquals("OK", resultEvent.getMessage().getPayloadAsString());
	}
}
