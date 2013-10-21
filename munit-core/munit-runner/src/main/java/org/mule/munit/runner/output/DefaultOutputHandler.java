/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.runner.output;


import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Prints the description using different printers</p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class DefaultOutputHandler implements TestOutputHandler
{

    public static String OUTPUT_FOLDER_PROPERTY = "munit.output.folder";

    private List<OutputPrinter> printers = new ArrayList<OutputPrinter>();

    public DefaultOutputHandler()
    {
        if (System.getProperty(OUTPUT_FOLDER_PROPERTY) != null)
        {

            printers.add(new LogPrinter());
        }

        printers.add(new ConsolePrinter());
    }

    @Override
    public void printDescription(String name, String description)
    {
        String text = "Running " + name;

        this.print(text);

    }

    @Override
    public void printTestName(String suiteName)
    {
        String title = StringUtils.repeat("=", 40 + suiteName.length());
        this.print(title);
        this.print("===========  Running  " + suiteName + "  test ===========");
        this.print(title);
    }

    private void print(String text)
    {
        for (OutputPrinter printer : printers)
        {
            printer.print(text);
        }
    }
}
