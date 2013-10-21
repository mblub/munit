/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.mule.result;

import org.mule.munit.runner.mule.result.notification.Notification;


public class MockTestFactory
{

    public static TestResult failingTest(String name)
    {
        TestResult testResult = new TestResult(name);
        testResult.setFailure(new Notification("fail", "Test fail"));
        return testResult;
    }

    public static TestResult succeedTest(String name)
    {
        TestResult testResult = new TestResult(name);
        return testResult;
    }

    public static TestResult errorTest(String name)
    {
        TestResult testResult = new TestResult(name);
        testResult.setError(new Notification("error", "Test error"));
        return testResult;
    }
}
