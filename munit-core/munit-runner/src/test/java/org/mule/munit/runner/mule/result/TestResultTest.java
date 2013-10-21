/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.mule.result;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mule.munit.runner.mule.result.MockTestFactory.errorTest;
import static org.mule.munit.runner.mule.result.MockTestFactory.failingTest;
import static org.mule.munit.runner.mule.result.MockTestFactory.succeedTest;

import org.junit.Test;

public class TestResultTest
{

    /**
     * If the test has an error ten do not succeed
     */
    @Test
    public void ifHasErrorThenDoNotSucceed()
    {
        assertFalse(errorTest("test").hasSucceeded());
    }

    /**
     * If the test has a failure ten do not succeed
     */
    @Test
    public void ifHasFailureThenDoNotSucceed()
    {
        assertFalse(failingTest("test").hasSucceeded());
    }

    /**
     * If the test doesn't have a failure or error then succeed
     */
    @Test
    public void ifNoFailureOrErrorSucceed()
    {
        assertTrue(succeedTest("test").hasSucceeded());
    }


    /**
     * if has error then return 1 error
     */
    @Test
    public void ifErrorThenReturnOneError()
    {
        assertEquals(1, errorTest("test").getNumberOfErrors());
    }

    /**
     * if has failure then return 1 failure
     */
    @Test
    public void ifFailureThenReturnOneError()
    {
        assertEquals(1, failingTest("test").getNumberOfFailures());
    }

}
