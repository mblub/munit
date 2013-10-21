/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.output;


/**
 * <p>Defines how the stage notification of the test will be handled </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public interface TestOutputHandler
{

    /**
     * <p>Prints the MUnit flow information</p>
     *
     * @param name        The name of the Munit flow (before-test,after-test,before-suite,after-suite,test)
     * @param description The flow description
     */
    void printDescription(String name, String description);

    void printTestName(String suiteName);
}
