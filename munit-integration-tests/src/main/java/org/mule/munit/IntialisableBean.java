/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.construct.AbstractFlowConstruct;

/**
 * Created by damiansima on 11/7/14.
 */
public class IntialisableBean implements Initialisable, MuleContextAware {
    public static final Class CLASS_TO_LOOKUP = AbstractFlowConstruct.class;
    private MuleContext context;

    private int callsToInitialise = 0;

    @Override
    public void initialise() throws InitialisationException {
        System.out.print("initialise " + context.getRegistry().lookupObjects(CLASS_TO_LOOKUP).size());
        if (context.getRegistry().lookupObjects(CLASS_TO_LOOKUP).size() != 0) {
            callsToInitialise++;
        }
    }

    @Override
    public void setMuleContext(MuleContext context) {

        this.context = context;
    }

    public int getCallsToInitialise() {
        return callsToInitialise;
    }

    public void setCallsToInitialise(int callsToInitialise) {
        this.callsToInitialise = callsToInitialise;
    }
}
