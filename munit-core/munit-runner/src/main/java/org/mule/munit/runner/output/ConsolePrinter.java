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
