/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.munit.mel.utils;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * This is the class returned by the GetResourceFunction. It has a path that
 * points to the resource wanted, and it has a couple of methods that can
 * convert its content into String, InputStream or byte array
 * </p>
 *
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class MunitResource
{

    private String path;
    static Logger logger = Logger.getLogger(MunitResource.class);

    public MunitResource(String path)
    {
        this.path = path;
    }

    public InputStream asStream()
    {
        InputStream streamImput = getClass().getResourceAsStream(path);
        if (streamImput == null)
        {
            throw new IllegalArgumentException(
                    "The path provided to get the resource does not exist");
        }

        return streamImput;
    }

    public String asString()
    {
        try
        {
            return IOUtils.toString(this.asStream());
        }
        catch (IOException ioe)
        {
            logger.error("The file is corrupted");
            return null;
        }
    }

    public byte[] asByteArray()
    {
        byte[] byteArrayImput = null;
        try
        {
            byteArrayImput = IOUtils.toByteArray(getClass()
                                                         .getResourceAsStream(path));
        }
        catch (IOException ioe)
        {
            logger.error("The file is corrupted");
        }
        return byteArrayImput;
    }

}
