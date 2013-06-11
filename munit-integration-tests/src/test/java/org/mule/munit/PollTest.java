package org.mule.munit;

import org.mule.munit.runner.java.AbstractMuleSuite;


public class PollTest extends AbstractMuleSuite
{

    @Override
    public String getConfigResources()
    {
        return "poll-config-test.xml";
    }
}
