/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.output;


/**
 * <p>Output printer interface</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public interface OutputPrinter
{

    /**
     * <p>Prints with an end of line the text passed as parameter</p>
     *
     * @param text <p>The text we want to print</p>
     */
    void print(String text);
}
