/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.mel.utils;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class MunitResourceTest
{

    @Test
    public void testMunitResourceAsStream() throws IOException
    {
        String path = "testFile.txt";
        MunitResource file = new MunitResource(path);

        InputStream is = file.asStream();
        assertEquals("Hello World!", IOUtils.toString(is, "UTF-8"));
    }

    @Test
    public void testMunitResourceAsString()
    {
        String path = "testFile.txt";
        MunitResource file = new MunitResource(path);
        String result = file.asString();
        assertEquals("Hello World!", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCaseWhenFileDoesNotExist()
    {
        new MunitResource("anything").asStream();
    }

    @Test
    public void testMunitResourceAsByteArray() throws IOException
    {
        String path = "testFile.txt";
        MunitResource file = new MunitResource(path);
        byte[] result = file.asByteArray();
        assertEquals("Hello World!", new String(result));
    }

}
