package org.mule.munit;

import org.mule.munit.common.mocking.EndpointMocker;
import org.mule.munit.common.mocking.MessageProcessorMocker;
import org.mule.munit.common.mocking.MunitSpy;
import org.mule.munit.common.mocking.MunitVerifier;

/**
 * <p>
 * This name might sound incorrect but it is a mock of the mock module.
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MockMockModule extends MockModule
{

    private MessageProcessorMocker mocker;
    private EndpointMocker endpointMocker;
    private MunitVerifier verifier;
    private MunitSpy spy;

    public MockMockModule(MessageProcessorMocker mocker, EndpointMocker endpointMocker, MunitSpy spy, MunitVerifier verifier)
    {
        this.mocker = mocker;
        this.endpointMocker = endpointMocker;
        this.spy = spy;
        this.verifier = verifier;
    }

    @Override
    protected MessageProcessorMocker mocker()
    {
        return mocker;
    }

    @Override
    protected EndpointMocker endpointMocker()
    {
        return endpointMocker;
    }

    @Override
    protected MunitVerifier verifier()
    {
        return verifier;
    }

    @Override
    protected MunitSpy spy()
    {
        return spy;
    }
}
