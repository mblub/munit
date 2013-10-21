/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.output;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Prints the output in a log file</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class LogPrinter implements OutputPrinter
{

    private Log log;

    public LogPrinter()
    {
        this.log = LogFactory.getLog(LogPrinter.class);

    }

    @Override
    public void print(String message)
    {
        log.info(message);
    }
}
