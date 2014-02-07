/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.common.mocking;

import org.mule.api.MuleEvent;
import org.mule.api.MuleException;

/**
 * <p>
 * if you want to spy something this is the method that you need to implement
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public interface SpyProcess
{

    /**
     * <p>
     * Executes code in the spying process
     * </p>
     *
     * @param event <p>
     *              The {@link MuleEvent}
     *              </p>
     * @throws MuleException <p>
     *                       In case of spy failure
     *                       </p>
     */
    void spy(MuleEvent event) throws MuleException;
}
