/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.java;

import junit.framework.TestSuite;
import org.junit.runner.RunWith;

/**
 * <p>Junit Runner to run Munit from Junit (Java)</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
@RunWith(MuleSuiteRunner.class)
public abstract class AbstractMuleSuite extends TestSuite
{

    /**
     * @return The path to the MUnit config.
     */
    public abstract String getConfigResources();

}
