package org.mule.munit.runner.output;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.mockito.Mockito.*;

/**
 * Created by damiansima on 12/10/14.
 */
public class DefaultOutputHandlerTest {

    private ConsolePrinter mockPrinter = mock(ConsolePrinter.class);

    @Before
    public void setUp() {
        System.setProperty(DefaultOutputHandler.OUTPUT_FOLDER_PROPERTY, "something");
    }

    @Test
    public void testCreation() {
        DefaultOutputHandler outputHandler = new DefaultOutputHandler();
        Assert.assertEquals("The ammount of printers is wrong.", 2, outputHandler.getPrinters().size());
    }

    @Test
    public void testPrintDescription() {
        DefaultOutputHandler outputHandler = new DefaultOutputHandler();
        outputHandler.getPrinters().clear();
        outputHandler.getPrinters().add(mockPrinter);

        outputHandler.printDescription("", "");

        verify(mockPrinter, times(1)).print(Matchers.anyString());
    }

    @Test
    public void testPrintTestName() {
        DefaultOutputHandler outputHandler = new DefaultOutputHandler();
        outputHandler.getPrinters().clear();
        outputHandler.getPrinters().add(mockPrinter);

        outputHandler.printTestName("");

        verify(mockPrinter, times(3)).print(Matchers.anyString());
    }


}
