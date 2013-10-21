package org.mule.munit.common.mp;

import static junit.framework.Assert.assertEquals;
import org.mule.api.processor.MessageProcessor;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class SpyAssertionTest
{

    @Test
    public void gettersMustReturnTheOriginalValues()
    {
        ArrayList<MessageProcessor> beforeMessageProcessors = new ArrayList<MessageProcessor>();
        ArrayList<MessageProcessor> afterMessageProcessors = new ArrayList<MessageProcessor>();
        SpyAssertion spyAssertion = new SpyAssertion(beforeMessageProcessors, afterMessageProcessors);

        assertEquals(beforeMessageProcessors, spyAssertion.getBeforeMessageProcessors());
        assertEquals(afterMessageProcessors, spyAssertion.getAfterMessageProcessors());
    }

    @Test
    public void gettersMustReturnTheOriginalValuesAddedBySetters()
    {
        ArrayList<MessageProcessor> beforeMessageProcessors = new ArrayList<MessageProcessor>();
        ArrayList<MessageProcessor> afterMessageProcessors = new ArrayList<MessageProcessor>();
        SpyAssertion spyAssertion = new SpyAssertion(null, null);
        spyAssertion.setAfterMessageProcessors(afterMessageProcessors);
        spyAssertion.setBeforeMessageProcessors(beforeMessageProcessors);

        assertEquals(beforeMessageProcessors, spyAssertion.getBeforeMessageProcessors());
        assertEquals(afterMessageProcessors, spyAssertion.getAfterMessageProcessors());
    }
}
