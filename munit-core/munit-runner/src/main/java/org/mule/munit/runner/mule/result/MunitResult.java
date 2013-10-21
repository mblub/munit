/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.mule.result;

/**
 * <p>The Representation of a Test Result.</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public interface MunitResult
{

    String getTestName();

    boolean hasSucceeded();

    int getNumberOfFailures();

    int getNumberOfErrors();

    int getNumberOfTests();

    float getTime();

    int getNumberOfSkipped();
}
