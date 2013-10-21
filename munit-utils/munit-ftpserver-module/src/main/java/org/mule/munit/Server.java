/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit;

import java.io.File;
import java.io.FilenameFilter;

import static org.junit.Assert.assertTrue;

/**
 * <p>Implementation of the FTP server</p>
 *
 * @author Mulesoft Inc.
 */
public abstract class Server
{

    abstract void initialize(int port);

    abstract void start();

    abstract void stop();

    public void containsFiles(String file, String path)
    {
        assertTrue(containsFilteredFiles(file, path).length > 0);
    }

    private File[] containsFilteredFiles(final String fileName, String path)
    {
        return new File(path).listFiles(new FilenameFilter()
        {

            @Override
            public boolean accept(File file, String name)
            {
                return name.startsWith(fileName);
            }
        });
    }

    public void remove(String path)
    {
        new File(path).delete();
    }
}
