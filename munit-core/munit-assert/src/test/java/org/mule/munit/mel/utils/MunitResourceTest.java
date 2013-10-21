/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.mel.utils;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class MunitResourceTest
{

    @Test
    public void testMunitResourceAsStream() throws IOException
    {
        String path = "/testFile.txt";
        MunitResource file = new MunitResource(path);

        InputStream is = file.asStream();
        assertEquals("Hello World!", IOUtils.toString(is, "UTF-8"));
    }

    @Test
    public void testMunitResourceAsString()
    {
        String path = "/testFile.txt";
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
        String path = "/testFile.txt";
        MunitResource file = new MunitResource(path);
        byte[] result = file.asByteArray();
        assertEquals("Hello World!", new String(result));
    }

}
