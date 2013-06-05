package org.mule.munit.common.mocking;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;

/**
 * Null Object implementation of the {@link SpyProcess} interface
 * Specially useful when you want to spy before OR after a process, but not both
 * 
 * @author Mariano Simone
 *
 */
public class NullProcessSpy implements SpyProcess {

    private static NullProcessSpy INSTANCE;
    private NullProcessSpy() {}

    public synchronized static NullProcessSpy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NullProcessSpy();
        }
        return INSTANCE;
        
    }

    @Override
    public void spy(MuleEvent event) throws MuleException {
        // Nothing to do here :D
    }
}
