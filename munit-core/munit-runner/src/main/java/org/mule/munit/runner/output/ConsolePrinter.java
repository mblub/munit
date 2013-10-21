/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.output;


/**
 * <p>Prints the description in the system console</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class ConsolePrinter implements OutputPrinter
{

    @Override
    public void print(String text)
    {
        System.out.println(text);
    }
}
